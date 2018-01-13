package pw.xwy.Factions.objects;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import pw.xwy.Factions.XFactionsCore;
import pw.xwy.Factions.utility.StringUtility;
import pw.xwy.Factions.utility.managers.ClaimManager;
import pw.xwy.Factions.utility.managers.FactionManager;
import pw.xwy.Factions.utility.managers.PlayerManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class XFaction {
	
	public int onlinePlayers = 0;
	public String color = "f";
	public XFactionConfig factionConfig;
	public XClaim claim = new XClaim();
	public List<XRank> ranks = new ArrayList<>();
	private String name;
	private boolean systemFac = false;
	private double power = 0.0;
	private double balance = 0.0;
	private UUID leader;
	private Location home = null;
	public ArrayList<UUID> players = new ArrayList<>();
	private XRank recruit;
	private ArrayList<XFaction> allies = new ArrayList<>();
	
	
	public XFaction(String name, Player creator) {
		leader = creator.getUniqueId();
		this.name = name;
		onlinePlayers++;
		factionConfig = new XFactionConfig(this);
	}
	
	public XFaction(String name, String color) {
		this.name = name;
		systemFac = true;
		this.color = color;
		factionConfig = new XFactionConfig(name, color);
	}
	
	//load from config
	public XFaction(String facString) {
		System.out.println("Started loading " + facString + "...");
		factionConfig = new XFactionConfig(facString);
		this.name = facString;
		
		
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
				
				String world = facString.substring(0, facString.indexOf("-"));
				facString = facString.replaceFirst(world + facString.indexOf("-"), "");
				double x = Double.parseDouble(facString.substring(0, facString.indexOf("-")));
				facString = facString.replaceFirst(String.valueOf(x) + facString.indexOf("-"), "");
				double y = Double.parseDouble(facString.substring(0, facString.indexOf("-")));
				facString = facString.replaceFirst(String.valueOf(y) + facString.indexOf("-"), "");
				double z = Double.parseDouble(facString.substring(0, facString.indexOf("-")));
				facString = facString.replaceFirst(String.valueOf(z) + facString.indexOf("-"), "");
				float pitch = Float.parseFloat(facString.substring(0, facString.indexOf("-")));
				facString = facString.replaceFirst(String.valueOf(pitch) + facString.indexOf("-"), "");
				float yaw = Float.parseFloat(facString.substring(0, facString.indexOf("-")));
				home = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
			}
			
			power = factionConfig.getPower();
			balance = factionConfig.getBalance();
			
			List<String> rankList = factionConfig.getRankList();
			for (int i = 0; i < rankList.size(); i++) {
				ranks.add(new XRank(0, this));
			}
			
			String leader = factionConfig.getLeader();
			this.leader = UUID.fromString(leader);
			if (factionConfig.hasLand()) {
				loadClaim(factionConfig.getClaim());
			}
		}
		System.out.println("Done loading " + facString + ".");
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
			claim(c);
			System.out.println("found claim at " + c.getX() + c.getZ());
		}
		
	}
	
	public XRank getRole(UUID p) {
		for (XRank rank : ranks) {
			if (rank.isIn(p)) {
				return rank;
			}
		}
		return recruit;
	}
	
	public void addRecruit(UUID p) {
		onlinePlayers++;
		recruit.add(p);
		factionConfig.save(this);
	}
	
	public void updatePower() {
		double power = 0;
		for (UUID id : getEveryone()) {
			power += new XPlayerConfig(id).getPower();
		}
		this.power = ((int) (power * 10)) / 10.0;
	}
	
	public double getPower() {
		return power;
	}
	
	public void setPower(double power) {
		this.power = power;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Location getHome() {
		return home;
	}
	
	public void setHome(Location home) {
		this.home = home;
	}
	
	public boolean isSystemFac() {
		return systemFac;
	}
	
	public void setSystemFac(boolean systemFac) {
		this.systemFac = systemFac;
	}
	
	public int getOnlinePlayers() {
		return onlinePlayers;
	}
	
	public void setOnlinePlayers(int onlinePlayers) {
		this.onlinePlayers = onlinePlayers;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public UUID getLeader() {
		return leader;
	}
	
	public void setLeader(UUID leader) {
		this.leader = leader;
	}
	
	
	public ArrayList<UUID> getEveryone() {
		ArrayList<UUID> everyone = new ArrayList<>();
		everyone.add(leader);
		return everyone;
	}
	
	public ArrayList<XFaction> getAllies() {
		return allies;
	}
	
	public void setAllies(ArrayList<XFaction> allies) {
		this.allies = allies;
	}
	
	public void claim(Chunk c) {
		claim.add(c, this);
		factionConfig.save(this);
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void removeBalance(double amount) {
		if (amount < 0) {
			if (balance - amount >= 0) {
				balance -= amount;
			}
		}
		
	}
	
	public void addBalance(double amount) {
		balance += amount;
	}
	
	public void disband(XPlayer pl) {
		if (!isSystemFac()) {
			if (!leader.equals(pl.getID())) {
				for (UUID p : getEveryone()) {
					Player player = Bukkit.getPlayer(p);
					leave(PlayerManager.getXPlayer(player), false);
				}
				pl.setFaction(null);
				Player player = Bukkit.getPlayer(leader);
				XFactionsCore.getEcononomy().depositPlayer(player, balance);
				Bukkit.broadcastMessage(StringUtility.conv("&c" + name + " has been disbanded."));
				FactionManager.removeFaction(this);
				ClaimManager.removeChunks(this);
				factionConfig.remove();
				
			}
		} else {
			System.out.println(pl.getPlayer().getName() + " has tried to disband a system faction though the normal method!");
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
	
	List<String> getClaimStrings() {
		List<String> strings = new ArrayList<>();
		for (Chunk c : claim.get()) {
			strings.add(c.getWorld().getName() + " " + c.getX() + " " + c.getZ());
		}
		return strings;
	}
	
	@Override
	public String toString() {
		if (systemFac) {
			return name + ";SYSTEMFACTION;" + color;
		} else {
			return name + ";" + getHomeString() + ";" + getPower() + ";" + getBalance() + ";" + leader;
		}
		
	}
	
	String getHomeString() {
		return StringUtility.toString(home);
	}
}
