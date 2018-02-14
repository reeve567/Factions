package pw.xwy.Factions.objects.faction;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.utility.Configurations.Config;
import pw.xwy.Factions.utility.managers.FactionManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/13/18 6:24 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class XFactionConfig {
	
	private FileConfiguration fileConfiguration;
	private File file;
	
	public XFactionConfig(String uuid) {
		File factionData = Config.factiondata;
		
		file = new File(factionData, File.separator + uuid + ".yml");
		fileConfiguration = YamlConfiguration.loadConfiguration(file);
		
		if (!file.exists()) {
			fileConfiguration.createSection("info");
			set("info.name", "");
			set("info.uuid", uuid);
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
	
	public XFactionConfig(XFaction faction) {
		File factionData = Config.factiondata;
		
		file = new File(factionData, File.separator + faction.getId().toString() + ".yml");
		fileConfiguration = YamlConfiguration.loadConfiguration(file);
		if (!file.exists()) {
			fileConfiguration.createSection("info");
			set("info.name", faction.getName());
			set("info.uuid", faction.getId().toString());
			set("info.color", faction.getColor());
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
	
	XFactionConfig(XPlayerFaction faction) {
		File factionData = Config.factiondata;
		
		file = new File(factionData, File.separator + faction.getId() + ".yml");
		fileConfiguration = YamlConfiguration.loadConfiguration(file);
		
		if (!file.exists()) {
			fileConfiguration.createSection("info");
			set("info.name", faction.getName());
			set("info.uuid", faction.getId().toString());
			set("info.balance", 0.0);
			set("info.power", 0.0);
			set("info.leader", faction.getLeader().toString());
			set("info.home", "");
			set("info.systemFac", false);
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
	
	private void saveRanks(XPlayerFaction faction) {
		for (XRank rank : faction.ranks) {
			rank.save();
		}
		faction.leaderRank.save();
		faction.recruit.save();
	}
	
	public double getBalance() {
		return getDouble("info.balance");
	}
	
	public List<String> getClaim() {
		return getStringList("others.claim");
	}
	
	public String getColor() {
		return getString("info.color");
	}
	
	public Double getDouble(String path) {
		return fileConfiguration.getDouble(path);
	}
	
	public String getHome() {
		return getString("others.home");
	}
	
	public String getLeader() {
		return getString("info.leader");
	}
	
	public String getName() {
		return getString("info.name");
	}
	
	public List<String> getPlayerRanks() {
		return getStringList("ranks.players");
	}
	
	public Double getPower() {
		return getDouble("info.power");
	}
	
	public List<String> getRankList() {
		return getStringList("ranks.list");
	}
	
	public List<String> getRankPermissions() {
		return getStringList("ranks.permissions");
	}
	
	public String getString(String path) {
		return fileConfiguration.getString(path);
	}
	
	public List<String> getStringList(String path) {
		return fileConfiguration.getStringList(path);
	}
	
	public UUID getUUID() {
		return UUID.fromString(getString("info.uuid"));
	}
	
	public boolean hasHome() {
		return !getString("others.home").equalsIgnoreCase("null");
	}
	
	public boolean hasLand() {
		return !getString("others.claim").equalsIgnoreCase("noclaim");
	}
	
	public boolean isSystem() {
		return fileConfiguration.getBoolean("info.systemFac");
	}
	
	public void remove() {
		file.delete();
	}
	
	public void save(XFaction xFaction) {
		set("info.name", xFaction.getName());
		set("info.color", xFaction.getColor());
		set("others.claim", xFaction.getClaimStrings());
		
		try {
			fileConfiguration.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void save(XPlayerFaction xPlayerFaction) {
		saveRanks(xPlayerFaction);
		List<String> allies = new ArrayList<>();
		for (XPlayerFaction faction : xPlayerFaction.getAllies()) {
			allies.add(faction.getId().toString());
		}
		set("others.allies", allies);
		
		set("info.balance", xPlayerFaction.getBalance());
		set("info.power", xPlayerFaction.getPower());
		set("info.leader", xPlayerFaction.getLeader().toString());
		set("others.home", xPlayerFaction.getHomeString());
		
		set("ranks.players", xPlayerFaction.getPlayersList());
		set("ranks.list", xPlayerFaction.getRanksStringList());
		save(xPlayerFaction);
	}
	
	public void set(String path, Object value) {
		fileConfiguration.set(path, value);
	}
}

