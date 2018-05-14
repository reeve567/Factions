package pw.xwy.factions.commands.factions.subcommands;

import pw.xwy.factions.objects.CommandHandler;
import pw.xwy.factions.objects.SubCommand;
import pw.xwy.factions.objects.faction.XFaction;
import pw.xwy.factions.objects.faction.XPlayer;
import pw.xwy.factions.objects.faction.XPlayerFaction;
import pw.xwy.factions.utility.managers.FactionManager;
import pw.xwy.factions.utility.managers.PlayerManager;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

@CommandHandler
public class Disband extends SubCommand {
	public Disband() {
		super("disband", "", "Disband your faction.");
	}
	
	@Override
	public void run(XPlayer p, String[] args) {
		if (p.hasPermission("f.disband.others")) {
			if (args.length < 2) {
				if (p.facCheck()) {
					PlayerManager.getOnlinePlayerFaction(p).disband(p, true);
				}
			} else {
				XFaction xPlayerFaction = FactionManager.getFactionByName(args[1]);
				if (xPlayerFaction != null) {
					if (xPlayerFaction instanceof XPlayerFaction) {
						((XPlayerFaction) xPlayerFaction).disband(p, true);
					} else {
						xPlayerFaction.disband();
					}
				}
			}
		} else {
			if (p.facCheck()) {
				p.getFaction().disband(p, false);
			}
		}
	}
}
