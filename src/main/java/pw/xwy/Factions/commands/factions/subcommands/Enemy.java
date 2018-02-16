package pw.xwy.Factions.commands.factions.subcommands;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/14/18 8:25 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

import pw.xwy.Factions.objects.CommandHandler;
import pw.xwy.Factions.objects.SubCommand;
import pw.xwy.Factions.objects.faction.XFaction;
import pw.xwy.Factions.objects.faction.XPlayer;
import pw.xwy.Factions.objects.faction.XPlayerFaction;
import pw.xwy.Factions.utility.Configurations.Messages;
import pw.xwy.Factions.utility.managers.FactionManager;

@CommandHandler
public class Enemy extends SubCommand {
	public Enemy() {
		super("enemy", "<faction>", "Toggle enemy relation with the specified faction");
	}
	
	@Override
	public void run(XPlayer p, String[] args) {
		if (p.facCheck()) {
			if (p.hasPermission("enemy")) {
				if (args.length != 2) {
					sendHelpMessage(p);
					return;
				}
				if (FactionManager.getFactionByName(args[1]) != null) {
					XFaction faction = FactionManager.getFactionByName(args[1]);
					if (!(faction instanceof XPlayerFaction)) {
						p.sendMessage("you cannot enemy this faction");
						return;
					}
					
					if (p.getFaction().getEnemies().contains(faction)) {
						p.getFaction().getEnemies().remove(faction);
						p.getFaction().sendMessage("you are no longer enemies with " + faction.getName());
					} else {
						if (p.getFaction().getAllies().contains(faction)) {
							p.getFaction().getAllies().remove(faction);
						}
						p.getFaction().getEnemies().add((XPlayerFaction) faction);
					}
				} else {
					p.sendMessage("Faction not found");
				}
			} else {
				p.sendMessages(Messages.getNoPermission());
			}
		}
	}
}
