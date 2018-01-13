package pw.xwy.Factions.utility;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import pw.xwy.Factions.XFactionsCore;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.objects.XPlayerConfig;
import pw.xwy.Factions.utility.managers.FactionManager;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class Config {
	
	public static double maxPower;
	public static double minPower;
	public static boolean usePermissions;
	public static boolean spawnEnabled;
	public static double spawnTeleportWarmupTime;
	public static Location spawn;
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
	
	public static void loadConfig() {
		createConfig();
		saveConfig();
		
		factionCreationPrice = config.getDouble("creation-price");
		chargeToMakeFaction = config.getBoolean("charge-for-faction-creation");
		maxPower = config.getDouble("max-power");
		minPower = config.getDouble("min-power");
		usePermissions = config.getBoolean("use-permissions");
		spawnTeleportWarmupTime = config.getDouble("spawn-teleport-warmup-time");
		if (!config.getString("spawn-location").equalsIgnoreCase("nospawn")) {
			spawn = StringUtility.fromString(config.getString("spawn-location"));
		}
		spawnEnabled = config.getBoolean("spawn-enabled");
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
					FactionManager.addFaction(new XFaction(f.getName().substring(0, f.getName().indexOf("."))));
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
		for (XFaction xFaction : FactionManager.getFactions()) {
			xFaction.factionConfig.save(xFaction);
		}
	}
	
	public static void setSpawn(Location loc) {
		config.set("spawn-location",StringUtility.toString(loc));
		saveConfig();
	}
	
	public static XPlayerConfig getPlayer(UUID uuid) {
		return new XPlayerConfig(Bukkit.getPlayer(uuid));
	}
	
	public static XPlayerConfig getPlayer(String uuid) {
		return new XPlayerConfig(Bukkit.getPlayer(UUID.fromString(uuid)));
	}
	
	public static XPlayerConfig getPlayer(Player player) {
		return new XPlayerConfig(player);
	}
	
	public static double getFactionCreationPrice() {
		return factionCreationPrice;
	}
	
	public static boolean isChargeToMakeFaction() {
		return chargeToMakeFaction;
	}
}
