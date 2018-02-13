package pw.xwy.Factions.commands.factions.subcommands;

import pw.xwy.Factions.objects.SubCommand;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.objects.XPlayer;
import pw.xwy.Factions.utility.managers.FactionManager;
import pw.xwy.Factions.utility.managers.PlayerManager;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

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
				XFaction xFaction = FactionManager.getFactionByName(args[1]);
				if (xFaction != null) {
					if (!xFaction.isSystemFac()) {
						xFaction.disband(p, true);
					} else {
						xFaction.disband();
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
