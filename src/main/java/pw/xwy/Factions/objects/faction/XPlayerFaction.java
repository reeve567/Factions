package pw.xwy.Factions.objects.faction;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import pw.xwy.Factions.utility.Configurations.Config;
import pw.xwy.Factions.utility.Configurations.Messages;
import pw.xwy.Factions.utility.Configurations.Spawners;
import pw.xwy.Factions.utility.StringUtility;
import pw.xwy.Factions.utility.managers.ClaimManager;
import pw.xwy.Factions.utility.managers.FactionManager;
import pw.xwy.Factions.utility.managers.PlayerManager;

import java.util.*;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/13/18 6:24 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class XPlayerFaction extends XFaction {
	
	public List<XRank> ranks = new ArrayList<>();
	public ArrayList<UUID> players = new ArrayList<>();
	public XRank recruit;
	public XRank leaderRank;
	public boolean open = false;
	public ArrayList<XPlayerFaction> allyRequests = new ArrayList<>();
	public HashMap<EntityType, Integer> spawners = new HashMap<>();
	public ArrayList<UUID> flying = new ArrayList<>();
	private int onlinePlayers = 0;
	private double power = 0.0;
	private double balance = 0.0;
	private UUID leader;
	private Location home = null;
	private ArrayList<XPlayerFaction> allies = new ArrayList<>();
	private ArrayList<XPlayerFaction> enemies = new ArrayList<>();
	private HashSet<XWarp> warps = new HashSet<>();
	
	public XPlayerFaction(String name, Player creator) {
		super(name, "f");
		spawnersInit();
		leader = creator.getUniqueId();
		id = FactionManager.getAvailableUUID();
		this.name = name;
		onlinePlayers++;
		factionConfig = new XFactionConfig(this);
		recruit = new XRank(this, false, false);
		leaderRank = new XRank(this, true, false);
		players.add(leader);
		PlayerManager.sendMessages(Messages.getFactionCreated(XPlayer.getXPlayer(creator), this));
	}
	
	//load from config
	public XPlayerFaction(XFactionConfig config) {
		super(config);
		spawnersInit();
		recruit = new XRank(this, false, true);
		leaderRank = new XRank(this, true, true);
		if (factionConfig.hasHome()) {
			String homeString = factionConfig.getHome();
			
			String world = homeString.substring(0, homeString.indexOf(" "));
			homeString = homeString.replaceFirst(world + " ", "");
			double x = Double.parseDouble(homeString.substring(0, homeString.indexOf(" ")));
			homeString = homeString.replaceFirst(String.valueOf(x) + " ", "");
			double y = Double.parseDouble(homeString.substring(0, homeString.indexOf(" ")));
			homeString = homeString.replaceFirst(String.valueOf(y) + " ", "");
			double z = Double.parseDouble(homeString.substring(0, homeString.indexOf(" ")));
			homeString = homeString.replaceFirst(String.valueOf(z) + " ", "");
			float pitch = Float.parseFloat(homeString.substring(0, homeString.indexOf(" ")));
			homeString = homeString.replaceFirst(String.valueOf(pitch), "");
			float yaw = Float.parseFloat(homeString);
			home = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
			
		}
		power = factionConfig.getPower();
		balance = factionConfig.getBalance();
		
		List<String> rankList = factionConfig.getRankList();
		for (int i = 0; i < rankList.size(); i++) {
			ranks.add(new XRank(rankList.get(i), i, this, true));
		}
		
		String leader = factionConfig.getLeader();
		this.leader = UUID.fromString(leader);
		players.add(this.leader);
		if (factionConfig.hasLand()) {
			loadClaim(factionConfig.getClaim());
		}
		
		for (String s : factionConfig.getWarpList()) {
			warps.add(XWarp.getXWarp(s));
		}
		
	}
	
	static public boolean validateName(String factionName) {
		List<Character> chars = new ArrayList<>();
		for (int i = 97; i < 123; i++) {
			chars.add((char) i);
		}
		for (int i = 65; i < 133; i++) {
			chars.add((char) i);
		}
		
		for (int i = 48; i < 58; i++) {
			chars.add((char) i);
		}
		for (char c : factionName.toCharArray()) {
			if (!chars.contains(c)) {
				return false;
			}
		}
		for (XFaction faction : FactionManager.getFactions()) {
			if (faction.getName().equalsIgnoreCase(factionName)) {
				return false;
			}
		}
		return true;
	}
	
	String getHomeString() {
		return StringUtility.toString(home);
	}
	
	private boolean hasEnoughPower(Chunk c, int radius) {
		int total = 1;
		for (int i = -radius; i <= radius; i++)
			for (int j = -radius; j <= radius; j++) {
				Chunk ch = c.getWorld().getChunkAt(c.getX() + i, c.getZ() + j);
				if (ClaimManager.getChunk(ch) == null)
					if (claim.get().size() + total <= getMaxPower()) {
						total++;
					} else {
						//not enough power
						return false;
					}
			}
		return true;
	}
	
	private void spawnersInit() {
		for (EntityType et : Spawners.getInstance().spawnerTypes) {
			spawners.put(et, 0);
		}
	}
	
	public void addAlly(XPlayerFaction faction) {
		allyRequests.remove(faction);
		allies.add(faction);
		sendMessages(Messages.getAllyRequestAccepted(faction));
	}
	
	public void addBalance(double amount) {
		balance += amount;
	}
	
	public void addEnemy(XPlayerFaction faction) {
		if (!enemies.contains(faction)) {
			enemies.add(faction);
		}
	}
	
	public void addRank(XRank xRank) {
		ranks.add(xRank);
	}
	
	public void addRecruit(UUID p) {
		onlinePlayers++;
		recruit.add(p);
		factionConfig.save(this);
	}
	
	public void claim(Chunk c, int radius, XPlayer p) {
		boolean success = false;
		if (!ClaimManager.isClaimed(c)) {
			if (hasEnoughPower(c, radius)) {
				System.out.println(radius);
				for (int i = -radius; i <= radius; i++)
					for (int j = -radius; j <= radius; j++) {
						Chunk ch = c.getWorld().getChunkAt(c.getX() + i, c.getZ() + j);
						if (ClaimManager.getChunk(ch) == null) {
							success = true;
							claim.add(ch, this);
						}
					}
				factionConfig.save(this);
				if (success) {
					p.sendMessages(Messages.getClaimed());
				}
			} else {
				p.sendMessages(Messages.getNotEnoughPower());
			}
		} else {
			//already claimed
			p.sendMessages(Messages.getAlreadyClaimed());
		}
	}
	
	public void disband(XPlayer p, boolean b) {
		if (hasPermission(p, "disband") || b) {
			XFactionPlayer leader = PlayerManager.getOfflinePlayer(this.leader);
			leader.setFaction(null);
			leader.save();
			for (UUID id : players) {
				XFactionPlayer pl = PlayerManager.getOfflinePlayer(id);
				leave(pl, false);
				pl.save();
			}
			PlayerManager.sendMessages(Messages.getFactionDisbanded(p, this));
			ClaimManager.removeChunks(this);
			FactionManager.removeFaction(this);
			factionConfig.remove();
		}
	}
	
	public ArrayList<XPlayerFaction> getAllies() {
		return allies;
	}
	
	public void setAllies(ArrayList<XPlayerFaction> allies) {
		this.allies = allies;
	}
	
	public void getAllyRequest(XPlayerFaction faction) {
		allyRequests.add(faction);
		sendMessages(Messages.getAllyRequest(faction));
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public ArrayList<XPlayerFaction> getEnemies() {
		return enemies;
	}
	
	public ArrayList<UUID> getEveryone() {
		ArrayList<UUID> everyone = new ArrayList<>();
		everyone.add(leader);
		return everyone;
	}
	
	public Location getHome() {
		return home;
	}
	
	public void setHome(Location home) {
		this.home = home;
	}
	
	public UUID getLeader() {
		return leader;
	}
	
	public void setLeader(UUID leader) {
		this.leader = leader;
	}
	
	public double getMaxPower() {
		return players.size() * Config.maxPower;
	}
	
	public int getOnlinePlayers() {
		return onlinePlayers;
	}
	
	public void setOnlinePlayers(int onlinePlayers) {
		this.onlinePlayers = onlinePlayers;
	}
	
	public List<String> getPlayersList() {
		List<String> players = new ArrayList<>();
		
		for (UUID uuid : this.players) {
			players.add(uuid.toString());
		}
		
		return players;
	}
	
	public double getPower() {
		return power;
	}
	
	public void setPower(double power) {
		this.power = power;
	}
	
	public List<String> getRanksStringList() {
		List<String> stings = new ArrayList<>();
		
		for (XRank rank : ranks) {
			stings.add(rank.name);
		}
		
		return stings;
	}
	
	public XRank getRole(UUID p) {
		for (XRank rank : ranks) {
			if (rank.isIn(p)) {
				return rank;
			}
		}
		return recruit;
	}
	
	public XRank getRole(String s) {
		for (XRank rank : ranks) {
			if (rank.name.equalsIgnoreCase(s)) {
				return rank;
			}
		}
		return null;
	}
	
	public int getValue() {
		int total = 0;
		for (XChunk chunk : claim.chunks) {
			total += chunk.value;
		}
		return total;
		
	}
	
	public HashSet<XWarp> getWarps() {
		return warps;
	}
	
	public ArrayList<String> getWarpsStrings() {
		ArrayList<String> strings = new ArrayList<>();
		for (XWarp warp : warps) {
			strings.add(warp.toString());
		}
		return strings;
	}
	
	public boolean hasPermission(Player p, String s) {
		return getRole(p.getUniqueId()).hasPerm(s, true) || isLeader(p);
	}
	
	public boolean hasPermission(Player p, String s, String st) {
		return hasPermission(p, s) || p.hasPermission(st);
	}
	
	public boolean hasPermission(UUID p, String s) {
		return getRole(p).hasPerm(s, true) || isLeader(p);
	}
	
	public boolean isLeader(Player p) {
		return leader.equals(p.getUniqueId());
	}
	
	public boolean isLeader(UUID uuid) {
		return leader.equals(uuid);
	}
	
	public void leave(XFactionPlayer player, boolean announce) {
		if (!player.getUniqueId().equals(leader)) {
			player.setFaction(null);
			if (announce) {
				sendMessages(Messages.getMemberLeft(player));
			}
			onlinePlayers--;
			factionConfig.save(this);
		}
	}
	
	@Override
	public boolean powerCheck() {
		return claim.chunks.size() + 1 < power;
	}
	
	public void removeBalance(double amount) {
		if (amount < 0) {
			if (balance - amount >= 0) {
				balance -= amount;
			}
		}
		
	}
	
	public void removeEnemy(XPlayerFaction faction) {
		if (!enemies.contains(faction)) {
			enemies.add(faction);
		}
	}
	
	public boolean removeRank(String arg) {
		for (XRank rank : ranks) {
			if (rank.name.equalsIgnoreCase(arg)) {
				ranks.remove(rank);
				return true;
			}
		}
		return false;
	}
	
	public void sendAllyRequest(XPlayerFaction faction) {
		faction.getAllyRequest(this);
	}
	
	public void sendMessage(String s) {
		for (UUID id : players) {
			if (PlayerManager.isOnline(id)) {
				PlayerManager.getPlayer(Bukkit.getPlayer(id)).sendMessage(s);
			}
		}
	}
	
	public void sendMessages(List<String> strings) {
		for (String s : strings) {
			sendMessage(s);
		}
	}
	
	@Override
	public String toString() {
		return "Name: " + name + "; Leader: " + PlayerManager.getOfflinePlayer(leader).getName() + "; Power: " + power + "; Land: " + claim.get().size();
		
	}
	
	public void toggleFlying(UUID p) {
		if (!flying.contains(p)) {
			flying.add(p);
			Bukkit.getPlayer(p).setAllowFlight(true);
		} else {
			flying.remove(p);
			Bukkit.getPlayer(p).setAllowFlight(false);
		}
	}
	
	public void toggleFlying(Player p) {
		if (!flying.contains(p.getUniqueId())) {
			flying.add(p.getUniqueId());
			p.setAllowFlight(true);
		} else {
			flying.remove(p.getUniqueId());
			p.setAllowFlight(false);
			PlayerManager.getPlayer(p).setNoFallDamage(true);
		}
	}
	
	public void updatePower() {
		double power = 0;
		for (UUID id : getEveryone()) {
			power += new XPlayerConfig(id).getPower();
		}
		this.power = ((int) (power * 10)) / 10.0;
	}
}
