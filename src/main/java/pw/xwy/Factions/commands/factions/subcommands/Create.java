package pw.xwy.Factions.commands.factions.subcommands;

import pw.xwy.Factions.XFactionsCore;
import pw.xwy.Factions.objects.CommandHandler;
import pw.xwy.Factions.objects.SubCommand;
import pw.xwy.Factions.objects.faction.XFaction;
import pw.xwy.Factions.objects.faction.XPlayer;
import pw.xwy.Factions.objects.faction.XPlayerFaction;
import pw.xwy.Factions.utility.Configurations.Config;
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

@CommandHandler
public class Create extends SubCommand {
	public Create() {
		super("create", "<name>", "Creates a faction with the name chosen.");
	}
	
	private void makeFaction(XPlayer p, String args[]) {
		List<Character> chars = new ArrayList<>();
		for (int i = 97; i < 123; i++) chars.add((char) i);
		for (int i = 65; i < 133; i++) chars.add((char) i);
		for (int i = 48; i < 58; i++) chars.add((char) i);
		boolean found = false;
		for (char c : args[1].toCharArray()) if (!chars.contains(c)) found = true;
		for (XFaction faction : FactionManager.getFactions()) if (faction.getName().equalsIgnoreCase(args[1])) found = true;
		if (!PlayerManager.isInFaction(p)) if (!found) {
			if (args.length == 2) {
				if (args[1].length() > 3) {
					if (Config.isChargeToMakeFaction()) {
						if (XFactionsCore.getEcononomy().getBalance(p) >= Config.getFactionCreationPrice()) {
							XFactionsCore.getEcononomy().withdrawPlayer(p, Config.getFactionCreationPrice());
							XPlayerFaction faction = new XPlayerFaction(args[1], p);
							FactionManager.addFaction(faction);
							PlayerManager.getPlayer(p).setFaction(faction);
							Config.saveFactions();
							PlayerManager.getPlayer(p).save();
						} else {
							p.sendMessage("not enough money");
						}
					} else {
						XPlayerFaction faction = new XPlayerFaction(args[1], p);
						FactionManager.addFaction(faction);
						PlayerManager.getPlayer(p).setFaction(faction);
						Config.saveFactions();
						PlayerManager.getPlayer(p).save();
					}
				} else {
					p.sendMessage(StringUtility.conv("&cThis faction name is too short."));
				}
			} else if (args.length == 3) {
				if (args[2].equalsIgnoreCase("sys") || args[2].equalsIgnoreCase("system")) {
					if (args[1].equalsIgnoreCase("warzone")) {
						p.sendMessage("warzone faction : " + args[1] + " created");
						XFaction faction = new XFaction("Warzone", "c");
						FactionManager.addFaction(faction);
						Config.saveFactions();
					} else {
						p.sendMessage("system faction : " + args[1] + " created");
						XFaction faction = new XFaction(args[1], "f");
						FactionManager.addFaction(faction);
						Config.saveFactions();
					}
				} else p.sendMessage("wrong args");
			}
		} else p.sendMessage("invalid name");
		else p.sendMessage(StringUtility.conv("&cYou are already in a faction!"));
	}
	
	@Override
	public void run(XPlayer p, String[] args) {
		if (p.hasPermission("f.create.system")) {
			if (args.length < 2) sendHelpMessage(p);
			else if (args.length == 2) makeFaction(p, args);
			else if (args.length == 3) makeFaction(p, args);
		} else if (args.length != 2) {
			sendHelpMessage(p);
			if (Config.isChargeToMakeFaction()) {
				p.sendMessage(StringUtility.conv("&7In order to create a faction, you must pay &2$&a" + Config.getFactionCreationPrice() + "&7."));
			}
		} else makeFaction(p, args);
	}
	
}
