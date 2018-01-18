package pw.xwy.Factions.utility.Configurations;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import pw.xwy.Factions.XFactionsCore;

import java.io.File;

public class Spawners {
	
	private static Spawners spawners;
	private FileConfiguration fileConfiguration;
	private File file;
	
	
	public Spawners() {
		spawners = this;
		file = new File(XFactionsCore.getXFactionsCore().getDataFolder(), "spawners.yml");
		fileConfiguration = new YamlConfiguration();
		
		if (!file.exists()) {
			fileConfiguration.createSection("values");
			
			for (EntityType entityType : EntityType.values()) {
				if (entityType.isAlive()) {
					if (!entityType.toString().equalsIgnoreCase("player")) {
						fileConfiguration.addDefault("values." + entityType.toString().toLowerCase(), 1000.0);
					}
				}
			}
		}
	}
	
	public double getPrice(EntityType e) {
		return fileConfiguration.getDouble("values." + e.toString().toLowerCase());
	}
	
	public static Spawners getInstance() {
		return spawners;
	}
}
