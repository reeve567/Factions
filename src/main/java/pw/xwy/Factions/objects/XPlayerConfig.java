package pw.xwy.Factions.objects;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import pw.xwy.Factions.utility.Configurations.Config;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class XPlayerConfig {
	
	private File file;
	private FileConfiguration fileConfiguration;
	
	XPlayerConfig(XPlayer player) {
		UUID p = player.getUniqueId();
		File userdata = Config.userdata;
		file = new File(userdata, File.separator + p + ".yml");
		fileConfiguration = YamlConfiguration.loadConfiguration(file);
		if (!file.exists()) {
			fileConfiguration.createSection("info");
			set("info.name", Bukkit.getPlayer(p).getName());
			set("info.faction", "");
			set("info.power", 0.0);
			set("info.rank", "");
			save();
		}
	}
	
	public XPlayerConfig(UUID uuid) {
		File userdata = Config.userdata;
		file = new File(userdata, File.separator + uuid + ".yml");
		fileConfiguration = YamlConfiguration.loadConfiguration(file);
		if (!file.exists()) {
			fileConfiguration.createSection("info");
			set("info.name", Bukkit.getPlayer(uuid).getName());
			set("info.faction", "");
			set("info.power", 0.0);
			save();
		}
	}
	
	public Double getDouble(String path) {
		return fileConfiguration.getDouble(path);
	}
	
	public String getFactionUUID() {
		return getString("info.faction");
	}
	
	public String getName() {
		return getString("info.name");
	}
	
	public Double getPower() {
		return getDouble("info.power");
	}
	
	public String getString(String path) {
		return fileConfiguration.getString(path);
	}
	
	public void save(XFactionPlayer xPlayer) {
		set("info.power", xPlayer.getPower());
		if (xPlayer.getFaction() != null) {
			set("info.faction", xPlayer.getFaction().id.toString());
		} else {
			set("info.faction", "no-faction");
		}
		try {
			fileConfiguration.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void save() {
		try {
			fileConfiguration.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void set(String path, Object value) {
		fileConfiguration.set(path, value);
	}
}
