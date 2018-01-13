package pw.xwy.Factions.objects;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import pw.xwy.Factions.utility.Config;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class XPlayerConfig {
	
	private File file;
	private FileConfiguration fileConfiguration;
	
	public XPlayerConfig(XPlayer player) {
		UUID p = player.getPlayer().getUniqueId();
		File userdata = Config.userdata;
		
		file = new File(userdata, File.separator + p + ".yml");
		fileConfiguration = YamlConfiguration.loadConfiguration(file);
		
		if (!file.exists()) {
			fileConfiguration.createSection("info");
			set("info.name", Bukkit.getPlayer(p).getName());
			set("info.faction", "");
			set("info.power", 0.0);
			set("info.rank","");
			save();
		}
	}
	
	public XPlayerConfig(UUID p) {
		File userdata = Config.userdata;
		
		file = new File(userdata, File.separator + p + ".yml");
		fileConfiguration = YamlConfiguration.loadConfiguration(file);
		
		if (!file.exists()) {
			fileConfiguration.createSection("info");
			set("info.name", Bukkit.getPlayer(p).getName());
			set("info.faction", "");
			set("info.power", 0.0);
			save();
		}
	}
	
	
	public void save() {
		try {
			fileConfiguration.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void save(XPlayer xPlayer) {
		set("info.power", xPlayer.getPower());
		if (xPlayer.getFaction() != null) {
			set("info.faction",xPlayer.getFaction().getName());
		}
		else {
			set("info.faction","no-faction");
		}
		
		
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
	
	public String getFactionName() {
		return getString("info.faction");
	}
	
	public Double getPower() {
		return getDouble("info.power");
	}
	
	public String getString(String path) {
		return fileConfiguration.getString(path);
	}
	
	public Double getDouble(String path) {
		return fileConfiguration.getDouble(path);
	}
}
