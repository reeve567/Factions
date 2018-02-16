package pw.xwy.Factions;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import pw.xwy.Factions.objects.SubCommand;
import pw.xwy.Factions.commands.factions.Faction;
import pw.xwy.Factions.commands.factions.FactionTop;
import pw.xwy.Factions.objects.Glow;
import pw.xwy.Factions.objects.Menu;
import pw.xwy.Factions.objects.faction.XFaction;
import pw.xwy.Factions.objects.faction.XPlayer;
import pw.xwy.Factions.utility.Configurations.Config;
import pw.xwy.Factions.utility.Configurations.Spawners;
import pw.xwy.Factions.utility.DRM;
import pw.xwy.Factions.utility.StringUtility;
import pw.xwy.Factions.utility.handlers.*;
import pw.xwy.Factions.utility.inventories.*;
import pw.xwy.Factions.utility.managers.*;
import pw.xwy.Factions.utility.tasks.PowerIncreaseTask;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.logging.Logger;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class XFactionsCore extends JavaPlugin {
	
	private static final Logger log = Logger.getLogger("Minecraft");
	private static final String name = "XFactions-Core";
	private static XFactionsCore xFactionsCore;
	private static Economy econ = null;
	private Menu[] menus;
	private Faction faction;
	private Manager[] managers;
	private FactionTop ftop;
	
	public static Economy getEcononomy() {
		return econ;
	}
	
	public static XFactionsCore getXFactionsCore() {
		return xFactionsCore;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getLabel().equalsIgnoreCase("f") || command.getLabel().equalsIgnoreCase("faction") || command.getLabel().equalsIgnoreCase("XFactionsCore")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				ArrayList<SubCommand> subCommands = faction.subCommands;
				
				if (args.length < 1) {
					String[] newArgs = {"help", "1"};
					
					for (SubCommand subCommand : subCommands) {
						if (subCommand.command.equalsIgnoreCase("help")) {
							subCommand.run((XPlayer) PlayerManager.getPlayer((Player) sender), newArgs);
							return true;
						}
					}
					
				} else {
					for (SubCommand subCommand : subCommands) {
						if (subCommand.command.equalsIgnoreCase(args[0])) {
							if (p.hasPermission(subCommand.permission) || !Config.usePermissions) {
								subCommand.run((XPlayer) PlayerManager.getPlayer((Player) sender), args);
								return true;
							}
						}
					}
					p.sendMessage(StringUtility.conv("&cUnknown subcommand"));
				}
				
			}
		} else if (command.getLabel().equalsIgnoreCase("ftop")) {
			if (sender instanceof Player) {
				ftop.run((XPlayer) PlayerManager.getPlayer((Player) sender), args);
			}
		}
		/*else if (command.getLabel().equalsIgnoreCase("spawn") && Config.spawnEnabled) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				XPlayer xPlayer = PlayerManager.getXPlayer(p);
				if (xPlayer.spawnCooldown > 0) {
					p.sendMessage(StringUtility.conv("&cYou're already teleporting!"));
				} else {
					new SpawnWarmupTask(xPlayer).runTaskTimerAsynchronously(this, 0, 2);
				}
			}
		} else if (command.getLabel().equalsIgnoreCase("setspawn") && Config.spawnEnabled) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (p.hasPermission("factions.setspawn")) {
					Config.setSpawn(p.getLocation());
					Config.spawn = p.getLocation();
					p.sendMessage(StringUtility.conv("&aSpawn has been set to your location."));
				}
			}
		} else if (command.getLabel().equalsIgnoreCase("shop")) {
			Shop.run(sender);
		} else if (command.getLabel().equalsIgnoreCase("sell")) {
			Sell.run(sender, econ);
		}*/
		return false;
	}
	
	@Override
	public void onDisable() {
		for (XFaction faction : FactionManager.getFactions()) {
			faction.getFactionConfig().save(faction);
		}
		for (Manager manager : managers) {
			manager.unload();
		}
		for (Menu menu : menus) {
			menu.unload();
		}
		
		managers = null;
		menus = null;
	}
	
	@Override
	public void onEnable() {
		XFactionsCore.xFactionsCore = this;
		DRM drm = new DRM(name);
		
		if (!drm.remove()) {
			managers = new Manager[]{new PlayerManager(), new ClaimManager(), new FactionManager()};
			menus = new Menu[]{new BrewingMenu(), new BuildingMenu(), new BuyMenu(), new CombatMenu(), new FarmingMenu(), new MainMenu()};
			
			for (Manager manager : managers) {
				manager.load();
			}
			for (Menu menu : menus) {
				menu.load();
			}
			
			int total = 6;
			int current = 0;
			System.out.println("Loading config " + ++current + "/" + total + "...");
			
			Config.loadConfig();
			new Spawners();
			System.out.println("Loading Factions " + ++current + "/" + total + "...");
			Config.loadFactions();
			System.out.println("Loading users " + ++current + "/" + total + "...");
			PlayerManager.loadOfflinePlayers();
			System.out.println("Loading Commands " + ++current + "/" + total + "...");
			loadCommands();
			System.out.println("Loading Economy " + ++current + "/" + total + "...");
			if (!setupEconomy()) {
				log.severe(String.format("[%s] - Disabled because of either no Vault or no economy plugin!", getDescription().getName()));
				setEnabled(false);
			}
			System.out.println("Loading Glow " + ++current + "/" + total + "...");
			registerGlow();
			if (isEnabled()) {
				getServer().getPluginManager().registerEvents(new JoinHandler(), this);
				getServer().getPluginManager().registerEvents(new LeaveHandler(), this);
				getServer().getPluginManager().registerEvents(new ChatManager(), this);
				getServer().getPluginManager().registerEvents(new ClaimHandler(), this);
				getServer().getPluginManager().registerEvents(new MoveHandler(), this);
				getServer().getPluginManager().registerEvents(new DamageHandler(), this);
				//getServer().getPluginManager().registerEvents(new UnknownCommandHandler(),this);
				//getServer().getPluginManager().registerEvents(new CitizensHandler(),this);
				getServer().getPluginManager().registerEvents(new InventoryHandler(econ), this);
				for (Player p : Bukkit.getOnlinePlayers()) {
					XPlayer xPlayer = new XPlayer(p.getUniqueId(), Config.getPlayer(String.valueOf(p.getUniqueId())));
					PlayerManager.addOnlinePlayer(xPlayer);
					if (xPlayer.getFaction() != null) {
						xPlayer.getFaction().setOnlinePlayers(xPlayer.getFaction().getOnlinePlayers() + 1);
					}
					
					xPlayer.save();
				}
				new PowerIncreaseTask().runTaskTimerAsynchronously(this, 0, 1200);
				System.out.println("Factions is done loading.");
				
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (p.getName().equalsIgnoreCase("Xwy")) {
						p.sendMessage("Factions was reloaded successfully (I think). Current version: " + getDescription().getVersion());
					}
				}
			}
		} else {
			for (Plugin p : getServer().getPluginManager().getPlugins()) {
				getPluginLoader().disablePlugin(p);
			}
		}
	}
	
	private void loadCommands() {
		faction = new Faction();
		ftop = new FactionTop();
		//new Shop();
		//new Sell();
	}
	
	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			System.out.println("Eco status: 1");
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			System.out.println("Eco status: 2");
			return false;
		}
		econ = rsp.getProvider();
		System.out.println("Eco status: 3");
		return econ != null;
	}
	
	private void registerGlow() {
		try {
			Field f = Enchantment.class.getDeclaredField("acceptingNew");
			f.setAccessible(true);
			f.set(null, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Glow glow = new Glow(70);
			Enchantment.registerEnchantment(glow);
		} catch (IllegalArgumentException ignored) {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public java.util.List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		if (command.getLabel().equalsIgnoreCase("f")) {
			ArrayList<SubCommand> subCommands = faction.subCommands;
			if (args.length < 2) {
				ArrayList<String> arrayList = new ArrayList<>();
				for (SubCommand subCommand : subCommands) {
					if (Config.usePermissions || subCommand.adminCommand) {
						if (sender.hasPermission(subCommand.permission)) {
							arrayList.add(subCommand.command);
						}
					} else {
						arrayList.add(subCommand.command);
					}
				}
				return arrayList;
			} else {
				return super.onTabComplete(sender, command, alias, args);
			}
		} else {
			return super.onTabComplete(sender, command, alias, args);
		}
	}
	
}
