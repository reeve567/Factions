package pw.xwy.Factions.utility.Configurations;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import pw.xwy.Factions.XFactionsCore;

import java.io.File;
import java.util.HashMap;

public class Spawners {
	
	private static Spawners spawners;
	private FileConfiguration fileConfiguration;
	private File file;
	private EntityType[] spawnerTypes = {EntityType.CHICKEN, EntityType.CAVE_SPIDER, EntityType.SPIDER, EntityType.SHEEP, EntityType.PIG, EntityType.COW, EntityType.ZOMBIE, EntityType.SKELETON, EntityType.PIG_ZOMBIE, EntityType.BLAZE, EntityType.CREEPER, EntityType.ENDERMAN, EntityType.VILLAGER, EntityType.SILVERFISH, EntityType.IRON_GOLEM};
	public HashMap<EntityType, Double> prices = new HashMap<>();
	
	public Spawners() {
		spawners = this;
		file = new File(XFactionsCore.getXFactionsCore().getDataFolder(), "spawners.yml");
		fileConfiguration = new YamlConfiguration();
		
		if (!file.exists()) {
			fileConfiguration.createSection("values");
			
			for (EntityType entityType : spawnerTypes) {
				fileConfiguration.addDefault("values." + entityType.toString().toLowerCase(), 1000.0);
				prices.put(entityType, 1000.0);
			}
			
		} else {
			for (EntityType type : spawnerTypes) {
				prices.put(type, getPrice(type));
			}
		}
	}
	
	public static Spawners getInstance() {
		return spawners;
	}
	
	public double getPrice(EntityType e) {
		return fileConfiguration.getDouble("values." + e.toString().toLowerCase());
	}
}
