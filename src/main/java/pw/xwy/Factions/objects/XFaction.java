package pw.xwy.Factions.objects;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import pw.xwy.Factions.commands.factions.subcommands.List;
import pw.xwy.Factions.utility.Configurations.Config;
import pw.xwy.Factions.utility.Configurations.Messages;
import pw.xwy.Factions.utility.Configurations.Spawners;
import pw.xwy.Factions.utility.StringUtility;
import pw.xwy.Factions.utility.managers.ClaimManager;
import pw.xwy.Factions.utility.managers.FactionManager;
import pw.xwy.Factions.utility.managers.PlayerManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class XFaction {
	
	public String color = "f";
	public XFactionConfig factionConfig;
	public XClaim claim = new XClaim();
	public List<XRank> ranks = new ArrayList<>();
	public ArrayList<UUID> players = new ArrayList<>();
	public XRank recruit;
	public boolean open = false;
	public String desc = "Default faction description D:";
	public ArrayList<XFaction> allyRequests = new ArrayList<>();
	public UUID id;
	public HashMap<EntityType, Integer> spawners = new HashMap<>();
	public ArrayList<UUID> flying = new ArrayList<>();
	private int onlinePlayers = 0;
	private String name;
	private boolean systemFac = false;
	private double power = 0.0;
	private double balance = 0.0;
	private UUID leader;
	private Location home = null;
	private ArrayList<XFaction> allies = new ArrayList<>();
	
	public XFaction(String name, Player creator) {
		spawnersInit();
		leader = creator.getUniqueId();
		id = FactionManager.getAvailableUUID();
		this.name = name;
		onlinePlayers++;
		factionConfig = new XFactionConfig(this);
		recruit = new XRank(this);
		players.add(leader);
	}
	
	public XFaction(String name, String color) {
		spawnersInit();
		this.name = name;
		id = FactionManager.getAvailableUUID();
		systemFac = true;
		this.color = color;
		factionConfig = new XFactionConfig(name, color, id.toString());
	}
	
	//load from config
	public XFaction(String facString) {
		spawnersInit();
		System.out.println("Started loading " + facString + "...");
		factionConfig = new XFactionConfig(facString);
		name = factionConfig.getName();
		id = factionConfig.getUUID();
		
		if (factionConfig.isSystem()) {
			systemFac = true;
			color = factionConfig.getColor();
			if (factionConfig.hasLand()) {
				loadClaim(factionConfig.getClaim());
			}
		} else {
			recruit = new XRank(this);
			if (factionConfig.hasHome()) {
				facString = factionConfig.getHome();
				
				String world = facString.substring(0, facString.indexOf(" "));
				facString = facString.replaceFirst(world + " ", "");
				double x = Double.parseDouble(facString.substring(0, facString.indexOf(" ")));
				facString = facString.replaceFirst(String.valueOf(x) + " ", "");
				double y = Double.parseDouble(facString.substring(0, facString.indexOf(" ")));
				facString = facString.replaceFirst(String.valueOf(y) + " ", "");
				double z = Double.parseDouble(facString.substring(0, facString.indexOf(" ")));
				facString = facString.replaceFirst(String.valueOf(z) + " ", "");
				float pitch = Float.parseFloat(facString.substring(0, facString.indexOf(" ")));
				facString = facString.replaceFirst(String.valueOf(pitch), "");
				float yaw = Float.parseFloat(facString);
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
		}
		System.out.println("Done loading " + facString + ".");
	}
	
	List<String> getClaimStrings() {
		List<String> strings = new ArrayList<>();
		for (Chunk c : claim.get()) {
			strings.add(c.getWorld().getName() + " " + c.getX() + " " + c.getZ());
		}
		return strings;
	}
	
	String getHomeString() {
		return StringUtility.toString(home);
	}
	
	private boolean hasEnoughPower(Chunk c, int radius) {
		int total = 0;
		if (systemFac) return true;
		for (int i = -radius; i <= radius; i++)
			for (int j = -radius; j <= radius; j++) {
				Chunk ch = c.getWorld().getChunkAt(c.getX() + i, c.getZ() + i);
				if (ClaimManager.getChunk(ch) != null)
					if (claim.get().size() <= getMaxPower() + total + 1 || systemFac) {
						total++;
					} else {
						//not enough power
						return false;
					}
			}
		return true;
	}
	
	private void loadClaim(List<String> s) {
		for (String st : s) {
			String temp = st;
			String world = temp.substring(0, temp.indexOf(' '));
			temp = temp.replaceFirst(world + " ", "");
			int x = Integer.parseInt(temp.substring(0, temp.indexOf(' ')));
			temp = temp.replaceFirst(String.valueOf(x) + ' ', "");
			int z = Integer.parseInt(temp);
			Chunk c = Bukkit.getWorld(world).getChunkAt(x, z);
			claim(c, 1, true);
		}
		
	}
	
	private void spawnersInit() {
		for (EntityType et : Spawners.getInstance().spawnerTypes) {
			spawners.put(et, 0);
		}
	}
	
	public void addAlly(XFaction faction) {
		allyRequests.remove(faction);
		allies.add(faction);
		for (UUID ID : players) {
			if (Bukkit.getPlayer(ID).isOnline()) {
				Messages.sendMessages(Bukkit.getPlayer(ID).getPlayer(), Messages.getAllyRequestRecieved(faction));
			}
		}
		if (Bukkit.getPlayer(leader).isOnline()) {
			Messages.sendMessages(Bukkit.getPlayer(leader), Messages.getAllyRequestAccepted(faction));
		}
	}
	
	public void addBalance(double amount) {
		balance += amount;
	}
	
	public void addRank(XRank xRank) {
		ranks.add(xRank);
	}
	
	public void addRecruit(UUID p) {
		onlinePlayers++;
		recruit.add(p);
		factionConfig.save(this);
	}
	
	public void claim(Chunk c, int radius, boolean force) {
		if (!ClaimManager.isClaimed(c)) if (claim.get().size() <= getMaxPower() + 1 || systemFac || force) {
			claim.add(c, this);
			if (radius != 1) for (int i = -radius / 2; i <= radius / 2; i++)
				for (int j = -radius / 2; j <= radius / 2; j++)
					if (i != 0 && j != 0) {
						Chunk ch = c.getWorld().getChunkAt(c.getX() + i, c.getZ() + i);
						if (ClaimManager.isClaimed(ch) && claim.get().size() <= getMaxPower() + 1 || systemFac || force)
							claim.add(c, this);
					}
		}
	}
	
	public void claim(Chunk c, int radius, Player p) {
		if (!ClaimManager.isClaimed(c)) {
			if (hasEnoughPower(c, radius)) {
				for (int i = -radius; i <= radius; i++)
					for (int j = -radius; j <= radius; j++) {
						Chunk ch = c.getWorld().getChunkAt(c.getX() + i, c.getZ() + i);
						if (ClaimManager.getChunk(ch) == null) {
							
							claim.add(c, this);
						}
					}
				factionConfig.save(this);
			} else {
				p.sendMessage("Not enough power!");
			}
		} else {
			//already claimed
			p.sendMessage("&cAlready claimed.");
		}
	}
	
	public void disband(Player p, boolean b) {
		if (!isSystemFac()) {
			if (getRole(p.getUniqueId()).hasPerm("disband", true) || b) {
				factionConfig.remove();
				Bukkit.broadcastMessage(StringUtility.conv("&c" + name + " has been disbanded by &e" + p.getName() + "&c."));
				FactionManager.removeFaction(this);
				ClaimManager.removeChunks(this);
			}
		} else {
			System.out.println(p.getName() + " has tried to disband a system faction though the normal method!");
		}
	}
	
	public void disband() {
		if (isSystemFac()) {
			Bukkit.broadcastMessage(StringUtility.conv("&c" + name + " has been disbanded."));
			FactionManager.removeFaction(this);
			ClaimManager.removeChunks(this);
			factionConfig.remove();
		}
	}
	
	public ArrayList<XFaction> getAllies() {
		return allies;
	}
	
	public void setAllies(ArrayList<XFaction> allies) {
		this.allies = allies;
	}
	
	public void getAllyRequest(XFaction faction) {
		allyRequests.add(faction);
		for (UUID id : players) {
			if (Bukkit.getPlayer(id).isOnline()) {
				Player p = Bukkit.getPlayer(id);
				Messages.sendMessages(p, Messages.getAllyRequest(faction));
			}
		}
		if (Bukkit.getPlayer(leader).isOnline()) {
			Messages.sendMessages(Bukkit.getPlayer(leader), Messages.getAllyRequest(faction));
		}
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
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
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
	
	public XRank getRole(String s) {
		for (XRank rank : ranks) {
			if (rank.name.equalsIgnoreCase(s)) {
				return rank;
			}
		}
		return null;
	}
	
	public XRank getRole(UUID p) {
		for (XRank rank : ranks) {
			if (rank.isIn(p)) {
				return rank;
			}
		}
		return recruit;
	}
	
	public int getValue() {
		int total = 0;
		for (XChunk chunk : claim.chunks) {
			total += chunk.value;
		}
		return total;
		
	}
	
	public boolean hasPermission(Player p, String s) {
		return getRole(p.getUniqueId()).hasPerm(s, true) || isLeader(p);
	}
	
	public boolean hasPermission(UUID p, String s) {
		return getRole(p).hasPerm(s, true) || isLeader(p);
	}
	
	public boolean isLeader(UUID uuid) {
		return leader.equals(uuid);
	}
	
	public boolean isLeader(Player p) {
		return leader.equals(p.getUniqueId());
	}
	
	public boolean isSystemFac() {
		return systemFac;
	}
	
	public void setSystemFac(boolean systemFac) {
		this.systemFac = systemFac;
	}
	
	public void leave(XPlayer player, boolean announce) {
		if (!player.getPlayer().getUniqueId().equals(leader)) {
			player.setFaction(null);
			if (announce) {
				for (UUID id : getEveryone()) {
					Bukkit.getPlayer(id).sendMessage(StringUtility.conv("&c" + player.getPlayer().getName() + " has left the faction!"));
				}
			}
			onlinePlayers--;
			factionConfig.save(this);
		}
	}
	
	public void removeBalance(double amount) {
		if (amount < 0) {
			if (balance - amount >= 0) {
				balance -= amount;
			}
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
	
	public void sendAllyRequest(XFaction faction) {
		faction.getAllyRequest(this);
	}
	
	@Override
	public String toString() {
		return "Name: " + name + "; Leader: " + PlayerManager.getOfflinePlayer(leader).getName() + "; Power: " + power + "; Land: " + claim.get().size();
		
	}
	
	public void toggleFlying(UUID p) {
		if (!flying.contains(p)) {
			flying.add(p);
			Bukkit.getPlayer(p).setAllowFlight(true);
			Bukkit.getPlayer(p).sendMessage("flying enabled");
		} else {
			flying.remove(p);
			Bukkit.getPlayer(p).setAllowFlight(false);
			Bukkit.getPlayer(p).sendMessage("flying disabled");
		}
		
		
	}
	
	public void toggleFlying(Player p) {
		if (!flying.contains(p.getUniqueId())) {
			flying.add(p.getUniqueId());
			p.setAllowFlight(true);
			p.sendMessage("flying enabled");
		} else {
			flying.remove(p.getUniqueId());
			p.setAllowFlight(false);
			p.sendMessage("flying disabled");
		}
	}
	
	public void updatePower() {
		double power = 0;
		for (UUID id : getEveryone()) {
			power += new XPlayerConfig(id).getPower();
		}
		this.power = ((int) (power * 10)) / 10.0;
	}

	static public boolean validateName(String factionName){

		java.util.List<Character> chars = new ArrayList<>();
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
}
