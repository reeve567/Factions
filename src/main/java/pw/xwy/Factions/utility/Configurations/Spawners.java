package pw.xwy.Factions.utility.Configurations;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import pw.xwy.Factions.XFactionsCore;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Spawners {
	
	private static Spawners spawners;
	private FileConfiguration fileConfiguration;
	private File file;
	public ArrayList<EntityType> arrayList = new ArrayList<>();
	public EntityType[] spawnerTypes = {EntityType.SILVERFISH,EntityType.VILLAGER,EntityType.IRON_GOLEM,EntityType.CREEPER,EntityType.PIG_ZOMBIE,EntityType.BLAZE,EntityType.ENDERMAN,EntityType.SKELETON,EntityType.ZOMBIE,EntityType.SPIDER,EntityType.CAVE_SPIDER,EntityType.COW,EntityType.PIG,EntityType.SHEEP,EntityType.CHICKEN};
	public HashMap<EntityType, Integer> prices = new HashMap<>();
	
	public Spawners() {
		spawners = this;
		arrayList.addAll(Arrays.asList(spawnerTypes));
		file = new File(XFactionsCore.getXFactionsCore().getDataFolder(), "spawners.yml");
		fileConfiguration = new YamlConfiguration();
		
		if (!file.exists()) {
			fileConfiguration.createSection("values");
			
			for (EntityType entityType : spawnerTypes) {
				fileConfiguration.addDefault("values." + entityType.toString().toLowerCase(), 1000.0);
				prices.put(entityType, 1000);
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
	
	public int getPrice(EntityType e) {
		return fileConfiguration.getInt("values." + e.toString().toLowerCase());
	}
}
