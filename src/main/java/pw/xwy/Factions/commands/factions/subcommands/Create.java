package pw.xwy.Factions.commands.factions.subcommands;

import org.bukkit.entity.Player;
import pw.xwy.Factions.XFactionsCore;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.enums.Messages;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.utility.Config;
import pw.xwy.Factions.utility.StringUtility;
import pw.xwy.Factions.utility.managers.FactionManager;
import pw.xwy.Factions.utility.managers.PlayerManager;

public class Create extends SubCommand {
	public Create() {
		super("create", "&6<name>", "&7Creates a faction with the name chosen.");
		
	}
	
	
	@Override
	public void run(Player p, String[] args) {
		if (p.hasPermission("factions.create.system")) {
			if (args.length < 2) {
				String prefix = Messages.PREFIX.get();
				p.sendMessage(prefix + StringUtility.conv("&7" + command + " " + help + "[system]"));
				p.sendMessage(help1);
			}
			else if (args.length == 2) {
				makeFaction(p,args);
			}
			else if (args.length == 3) {
				boolean isSystem = args[2].equalsIgnoreCase("system") || args[1].equalsIgnoreCase("sys");
				String[] notAllowedCharacters = {";", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", ",", "\\", "/", ".", "|", ":", "\"", "\'", "`", "~"};
				boolean found = false;
				for (String s : notAllowedCharacters) {
					if (args[1].contains(s)) {
						found = true;
					}
				}
				for (XFaction faction : FactionManager.getFactions()) {
					if (faction.getName().equalsIgnoreCase(args[1])) found = true;
				}
				if (!found && isSystem) {
					XFaction faction = new XFaction(args[1],"6");
					FactionManager.addFaction(faction);
					Config.saveFactions();
				}
				
			}
		}
		else {
			if (args.length != 2) {
				String prefix = Messages.PREFIX.get();
				p.sendMessage(prefix + StringUtility.conv("&7" + command + " " + help));
				p.sendMessage(help1);
				if (Config.isChargeToMakeFaction()) {
					p.sendMessage(StringUtility.conv("&7In order to create a faction, you must pay &2$&a" + Config.getFactionCreationPrice() + "&7."));
				}
			} else makeFaction(p,args);
		}
	}
	
	private void makeFaction(Player p, String args[]) {
		String[] notAllowedCharacters = {";", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", ",", "\\", "/", ".", "|", ":", "\"", "\'", "`", "~"};
		boolean found = false;
		for (String s : notAllowedCharacters) {
			if (args[1].contains(s)) {
				found = true;
			}
		}
		for (XFaction faction : FactionManager.getFactions()) {
			if (faction.getName().equalsIgnoreCase(args[1])) found = true;
		}
		if (!PlayerManager.isInFaction(p)) {
			if (!found) {
				if (Config.isChargeToMakeFaction()) {
					if (XFactionsCore.getEcononomy().getBalance(p) >= Config.getFactionCreationPrice()) {
						XFactionsCore.getEcononomy().withdrawPlayer(p, Config.getFactionCreationPrice());
						XFaction faction = new XFaction(args[1], p);
						FactionManager.addFaction(faction);
						PlayerManager.setFaction(PlayerManager.getXPlayer(p), faction);
						Config.saveFactions();
						PlayerManager.getXPlayer(p).playerConfig.save();
					}
				} else {
					XFaction faction = new XFaction(args[1], p);
					FactionManager.addFaction(faction);
					PlayerManager.setFaction(PlayerManager.getXPlayer(p), faction);
					Config.saveFactions();
					PlayerManager.getXPlayer(p).playerConfig.save();
				}
				
			} else {
				p.sendMessage(StringUtility.conv("&cThis faction already exists, or you have invalid characters in the name."));
			}
		} else {
			p.sendMessage(StringUtility.conv("&cYou are already in a faction!"));
		}
	}
	
}
