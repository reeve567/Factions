package pw.xwy.Factions;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.commands.factions.Faction;
import pw.xwy.Factions.objects.Glow;
import pw.xwy.Factions.objects.XPlayer;
import pw.xwy.Factions.utility.Config;
import pw.xwy.Factions.utility.Messages;
import pw.xwy.Factions.utility.handlers.ClaimHandler;
import pw.xwy.Factions.utility.handlers.JoinHandler;
import pw.xwy.Factions.utility.handlers.LeaveHandler;
import pw.xwy.Factions.utility.handlers.MoveHandler;
import pw.xwy.Factions.utility.managers.ChatManager;
import pw.xwy.Factions.utility.managers.PlayerManager;
import pw.xwy.Factions.utility.tasks.PowerIncreaseTask;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.logging.Logger;

public class XFactionsCore extends JavaPlugin {
	
	private static final Logger log = Logger.getLogger("Minecraft");
	private static XFactionsCore xFactionsCore;
	private static Economy econ = null;
	
	private Faction faction;
	
	
	public static Economy getEcononomy() {
		return econ;
	}
	
	public static XFactionsCore getXFactionsCore() {
		return xFactionsCore;
	}
	
	@Override
	public void onEnable() {
		XFactionsCore.xFactionsCore = this;
		System.out.println("Loading config 1/5...");
		Config.loadConfig();
		System.out.println("Loading XFactionsCore 2/5...");
		Config.loadFactions();
		System.out.println("Loading Commands 3/5...");
		loadCommands();
		System.out.println("Loading Economy 4/5...");
		if (!setupEconomy()) {
			log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
			setEnabled(false);
		}
		System.out.println("Loading Glow 5/5");
		registerGlow();
		if (isEnabled()) {
			getServer().getPluginManager().registerEvents(new JoinHandler(), this);
			getServer().getPluginManager().registerEvents(new LeaveHandler(), this);
			getServer().getPluginManager().registerEvents(new ChatManager(), this);
			getServer().getPluginManager().registerEvents(new ClaimHandler(), this);
			getServer().getPluginManager().registerEvents(new MoveHandler(), this);
			//getServer().getPluginManager().registerEvents(new UnknownCommandHandler(),this);
			//getServer().getPluginManager().registerEvents(new CitizensHandler(),this);
			//getServer().getPluginManager().registerEvents(new InventoryHandler(econ),this);
			
			for (Player p : Bukkit.getOnlinePlayers()) {
				XPlayer xPlayer = new XPlayer(p.getUniqueId(), Config.getPlayer(String.valueOf(p.getUniqueId())));
				PlayerManager.addXPlayer(xPlayer);
				if (xPlayer.getFaction() != null) {
					xPlayer.getFaction().setOnlinePlayers(xPlayer.getFaction().getOnlinePlayers() + 1);
				}
				
				xPlayer.playerConfig.save();
			}
			
			new PowerIncreaseTask().runTaskTimerAsynchronously(this, 0, 1200);
			
			System.out.println("factions v" + getDescription().getVersion() + " loaded.");
			
		}
	}
	
	private void loadCommands() {
		faction = new Faction();
		//new Shop();
		//new Sell();
		
	}
	
	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			System.out.println(1);
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			System.out.println(2);
			return false;
		}
		econ = rsp.getProvider();
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
	public void onDisable() {
	
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
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getLabel().equalsIgnoreCase("f") || command.getLabel().equalsIgnoreCase("faction") || command.getLabel().equalsIgnoreCase("XFactionsCore")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				ArrayList<SubCommand> subCommands = faction.subCommands;
				
				if (args.length < 1) {
					Messages.sendMessages(p, Messages.getHeader());
					Messages.sendMessages(p, Messages.getHelpMenuExtra());
					
					for (SubCommand subCommand : subCommands) {
						if (Config.usePermissions || subCommand.adminCommand) {
							if (p.hasPermission(subCommand.permission)) {
								Messages.sendMessages(p, Messages.getCommandHelpFormat(subCommand));
							}
						} else {
							Messages.sendMessages(p, Messages.getCommandHelpFormat(subCommand));
						}
					}
					
					Messages.sendMessages(p, Messages.getFooter());
					
				} else {
					for (SubCommand subCommand : subCommands) {
						if (subCommand.command.equalsIgnoreCase(args[0])) {
							if (p.hasPermission(subCommand.permission)) {
								subCommand.run(p, args);
							}
						}
					}
				}
				
				
			}
		} /*else if (command.getLabel().equalsIgnoreCase("spawn") && Config.spawnEnabled) {
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
	
	
}
