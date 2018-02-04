package pw.xwy.Factions.commands.factions.subcommands;

import org.bukkit.entity.Player;
import pw.xwy.Factions.XFactionsCore;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.utility.Configurations.Config;
import pw.xwy.Factions.utility.Configurations.Messages;
import pw.xwy.Factions.utility.StringUtil;
import pw.xwy.Factions.utility.StringUtility;
import pw.xwy.Factions.utility.managers.FactionManager;
import pw.xwy.Factions.utility.managers.PlayerManager;

import java.util.ArrayList;
import java.util.List;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class Create extends SubCommand {
	public Create() {
		super("create", "<name>", "Creates a faction with the name chosen.");
		
	}
	
	private void makeFaction(Player p, String args[]) {
		List<Character> chars = new ArrayList<>();
		for (int i = 97; i < 123; i++) {
			chars.add((char) i);
		}
		for (int i = 65; i < 133; i++) {
			chars.add((char) i);
		}
		for (int i = 48; i < 58; i++) {
			chars.add((char) i);
		}
		boolean found = false;
		for (char c : args[1].toCharArray()) {
			if (!chars.contains(c)) found = true;
		}

		for (XFaction faction : FactionManager.getFactions()) {
			if (faction.getName().equalsIgnoreCase(args[1])) found = true;
		}

		if (!PlayerManager.isInFaction(p)) {
			if (!found) {
				if (args[1].length() > 3) {
					
					if (Config.isChargeToMakeFaction()) {
						if (XFactionsCore.getEcononomy().getBalance(p) >= Config.getFactionCreationPrice()) {
							p.sendMessage("made faction for " + Config.getFactionCreationPrice());
							XFactionsCore.getEcononomy().withdrawPlayer(p, Config.getFactionCreationPrice());
							XFaction faction = new XFaction(args[1], p);
							FactionManager.addFaction(faction);
							PlayerManager.setFaction(PlayerManager.getXPlayer(p), faction);
							Config.saveFactions();
							PlayerManager.getXPlayer(p).playerConfig.save();
						} else {
							p.sendMessage("not enough money");
						}
					} else {
						p.sendMessage("made faction");
						XFaction faction = new XFaction(args[1], p);
						FactionManager.addFaction(faction);
						PlayerManager.setFaction(PlayerManager.getXPlayer(p), faction);
						Config.saveFactions();
						PlayerManager.getXPlayer(p).playerConfig.save();
					}
				} else {
					p.sendMessage(StringUtil.chatColorConv("&cThis faction name is too short."));
				}
			} else {
				p.sendMessage(StringUtility.conv("&cThis faction already exists, or you have invalid characters in the name."));
			}
		} else {
			p.sendMessage(StringUtility.conv("&cYou are already in a faction!"));
		}
	}
	
	@Override
	public void run(Player p, String[] args) {
		if (p.hasPermission("factions.create.system")) {
			if (args.length < 2) {
				Messages.sendMessages(p, Messages.getCommandHelpFormat(this));
			} else if (args.length == 2) {
				makeFaction(p, args);
			} else if (args.length == 3) {
				boolean isSystem = args[2].equalsIgnoreCase("system") || args[2].equalsIgnoreCase("sys");
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
					XFaction faction = new XFaction(args[1], "6");
					FactionManager.addFaction(faction);
					Config.saveFactions();
				}
				
			}
		} else {
			if (args.length != 2) {
				Messages.sendMessages(p, Messages.getCommandHelpFormat(this));
				if (Config.isChargeToMakeFaction()) {
					p.sendMessage(StringUtility.conv("&7In order to create a faction, you must pay &2$&a" + Config.getFactionCreationPrice() + "&7."));
				}
			} else makeFaction(p, args);
		}
	}
	
}
