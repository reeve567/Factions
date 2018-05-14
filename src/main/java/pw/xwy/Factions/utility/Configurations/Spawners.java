package pw.xwy.factions.utility.Configurations;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import pw.xwy.factions.XFactionsCore;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class Spawners {
	
	private static Spawners spawners;
	public ArrayList<EntityType> arrayList = new ArrayList<>();
	public EntityType[] spawnerTypes = {EntityType.SILVERFISH, EntityType.VILLAGER, EntityType.IRON_GOLEM, EntityType.CREEPER, EntityType.PIG_ZOMBIE, EntityType.BLAZE, EntityType.ENDERMAN, EntityType.SKELETON, EntityType.ZOMBIE, EntityType.SPIDER, EntityType.CAVE_SPIDER, EntityType.COW, EntityType.PIG, EntityType.SHEEP, EntityType.CHICKEN};
	public HashMap<EntityType, Integer> prices = new HashMap<>();
	private FileConfiguration fileConfiguration;
	
	public Spawners() {
		spawners = this;
		arrayList.addAll(Arrays.asList(spawnerTypes));
		File file = new File(XFactionsCore.getXFactionsCore().getDataFolder(), "spawners.yml");
		fileConfiguration = new YamlConfiguration();
		if (!file.exists()) {
			fileConfiguration.createSection("values");
			for (EntityType entityType : spawnerTypes) {
				fileConfiguration.addDefault("values." + entityType.toString().toLowerCase(), 1000);
				prices.put(entityType, 1000);
			}
			fileConfiguration.options().copyDefaults(true);
			try {
				fileConfiguration.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				fileConfiguration.load(file);
			} catch (IOException | InvalidConfigurationException e) {
				e.printStackTrace();
			}
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
