package pw.xwy.Factions.objects;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import pw.xwy.Factions.utility.Config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XFactionConfig {
	
	private File file;
	public FileConfiguration fileConfiguration;
	
	public XFactionConfig(String name) {
		File factionData = Config.factiondata;
		
		file = new File(factionData, File.separator + name + ".yml");
		fileConfiguration = YamlConfiguration.loadConfiguration(file);
		
		if (!file.exists()) {
			fileConfiguration.createSection("info");
			set("info.name", name);
			set("info.balance", 0.0);
			set("info.power", 0.0);
			set("info.leader", "noleader");
			set("info.home", "");
			set("info.systemFac", false);
			set("info.color", 'f');
			fileConfiguration.createSection("others");
			set("others.allies", new ArrayList<>());
			set("others.claim", new ArrayList<String>());
			set("others.home", "null");
			fileConfiguration.createSection("ranks");
			set("ranks.players", new ArrayList<String>());
			set("ranks.list", new ArrayList<String>());
			set("ranks.permissions", new ArrayList<String>());
			try {
				fileConfiguration.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public XFactionConfig(String name, String color) {
		File factionData = Config.factiondata;
		
		file = new File(factionData, File.separator + name + ".yml");
		fileConfiguration = YamlConfiguration.loadConfiguration(file);
		if (!file.exists()) {
			fileConfiguration.createSection("info");
			set("info.name", name);
			set("info.color", color);
			set("info.systemFac", true);
			fileConfiguration.createSection("others");
			set("others.claim", new ArrayList<String>());
			try {
				fileConfiguration.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public XFactionConfig(XFaction faction) {
		File factionData = Config.factiondata;
		
		file = new File(factionData, File.separator + faction.getName() + ".yml");
		fileConfiguration = YamlConfiguration.loadConfiguration(file);
		
		if (!file.exists()) {
			fileConfiguration.createSection("info");
			set("info.name", faction.getName());
			set("info.balance", 0.0);
			set("info.power", 0.0);
			set("info.leader", faction.getLeader().toString());
			set("info.home", "");
			set("info.systemFac", faction.isSystemFac());
			set("info.color", 'f');
			fileConfiguration.createSection("others");
			set("others.allies", new ArrayList<String>());
			set("others.claim", new ArrayList<String>());
			set("others.home", "null");
			
			
			fileConfiguration.createSection("permissions");
			
			
			
			try {
				fileConfiguration.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void save(XFaction xFaction) {
		if (!isSystem()) {
			List<String> allies = new ArrayList<>();
			for (XFaction faction : xFaction.getAllies()) {
				allies.add(faction.getName());
			}
			set("others.allies", allies);
			
			
			set("info.balance", xFaction.getBalance());
			set("info.power", xFaction.getPower());
			set("info.leader", xFaction.getLeader().toString());
			set("others.home", xFaction.getHomeString());
			
			set("ranks.players", xFaction.playerRanks);
			set("ranks.list",xFaction.ranks);
			
			
		}
		set("info.color", xFaction.getColor());
		set("others.claim", xFaction.getClaimStrings());
		
		
		try {
			fileConfiguration.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void set(String path, Object value) {
		fileConfiguration.set(path, value);
	}
	
	public String getName() {
		return getString("info.name");
	}
	
	public Double getPower() {
		return getDouble("info.power");
	}
	
	public String getLeader() {
		return getString("info.leader");
	}
	
	public boolean isSystem() {
		return fileConfiguration.getBoolean("info.systemFac");
	}
	
	public boolean hasLand() {
		return !getString("others.claim").equalsIgnoreCase("noclaim");
	}
	
	public boolean hasHome() {
		return !getString("others.home").equalsIgnoreCase("null");
	}
	
	public String getHome() {
		return getString("others.home");
	}
	
	public List<String> getClaim() {
		return getStringList("others.claim");
	}
	
	public String getColor() {
		return getString("info.color");
	}
	
	public String getString(String path) {
		return fileConfiguration.getString(path);
	}
	
	public Double getDouble(String path) {
		return fileConfiguration.getDouble(path);
	}
	
	public List<String> getStringList(String path) {
		return fileConfiguration.getStringList(path);
	}
	
	public List<String> getRankList() {
		return getStringList("ranks.list");
	}
	
	public List<String> getRankPermissions() {
		return getStringList("ranks.permissions");
	}
	
	public List<String> getPlayerRanks() {
		return getStringList("ranks.players");
	}
	
	public double getBalance() {
		return getDouble("info.balance");
	}
	
	public void remove() {
		file.deleteOnExit();
	}
}

