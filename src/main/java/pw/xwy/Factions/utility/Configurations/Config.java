package pw.xwy.factions.utility.Configurations;

import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import pw.xwy.factions.XFactionsCore;
import pw.xwy.factions.objects.faction.XFaction;
import pw.xwy.factions.objects.faction.XPlayerConfig;
import pw.xwy.factions.utility.StringUtility;
import pw.xwy.factions.utility.managers.FactionManager;

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

public class Config {
	
	public static double maxPower;
	public static double minPower;
	public static double homeWarmupTime;
	public static boolean usePermissions;
	public static int commandsPerPage;
	private static File configF;
	private static FileConfiguration config;
	private static XFactionsCore xFactionsCore = XFactionsCore.getXFactionsCore();
	public static File userdata = new File(xFactionsCore.getDataFolder(), File.separator + "PlayerData");
	public static File factiondata = new File(xFactionsCore.getDataFolder(), File.separator + "FactionData");
	private static boolean chargeToMakeFaction;
	private static double factionCreationPrice;
	
	private static void createConfig() {
		
		configF = new File(xFactionsCore.getDataFolder(), "config.yml");
		
		if (!configF.exists()) {
			configF.getParentFile().mkdirs();
			xFactionsCore.saveResource("config.yml", false);
		}
		
		config = new YamlConfiguration();
		try {
			config.load(configF);
		} catch (InvalidConfigurationException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static double getFactionCreationPrice() {
		return factionCreationPrice;
	}
	
	public static XPlayerConfig getPlayer(UUID uuid) {
		return new XPlayerConfig(uuid);
	}
	
	public static XPlayerConfig getPlayer(String uuid) {
		return new XPlayerConfig(UUID.fromString(uuid));
	}
	
	public static XPlayerConfig getPlayer(Player player) {
		return new XPlayerConfig(player.getUniqueId());
	}
	
	public static boolean isChargeToMakeFaction() {
		return chargeToMakeFaction;
	}
	
	public static void loadConfig() {
		Messages.loadConfig();
		createConfig();
		saveConfig();
		
		factionCreationPrice = config.getDouble("creation-price");
		chargeToMakeFaction = config.getBoolean("charge-for-faction-creation");
		homeWarmupTime = config.getDouble("home-warmup-time");
		maxPower = config.getDouble("max-power");
		minPower = config.getDouble("min-power");
		usePermissions = config.getBoolean("use-permissions");
		commandsPerPage = config.getInt("commands-per-help-page");
	}
	
	public static void loadFactions() {
		if (factiondata != null) {
			File[] files = factiondata.listFiles();
			int index = 0;
			if (files != null) {
				int total = files.length;
				for (File f : files) {
					index++;
					System.out.println("Loading faction " + f.getName().substring(0, f.getName().indexOf(".")) + " (" + index + "/" + total + ")");
					FactionManager.addFaction(XFaction.loadFaction(f.getName().substring(0,f.getName().indexOf("."))));
				}
			}
		}
	}
	
	private static void saveConfig() {
		try {
			config.save(configF);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveFactions() {
		for (XFaction xPlayerFaction : FactionManager.getFactions()) {
			xPlayerFaction.getFactionConfig().save(xPlayerFaction);
		}
	}
	
	public static void setSpawn(Location loc) {
		config.set("spawn-location", StringUtility.toString(loc));
		saveConfig();
	}
}
