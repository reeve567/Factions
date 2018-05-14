package pw.xwy.factions.commands.factions.subcommands;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/14/18 9:17 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

import pw.xwy.factions.objects.SubCommand;
import pw.xwy.factions.objects.faction.XPlayer;
import pw.xwy.factions.utility.Configurations.Messages;

public class Delwarp extends SubCommand {
	public Delwarp() {
		super("delwarp", "<name>", "Deletes the specified warp");
	}
	
	@Override
	public void run(XPlayer p, String[] args) {
		if (p.facCheck()) {
			if (p.hasPermission("delwarps")) {
			
			} else {
				p.sendMessages(Messages.getNoPermission());
			}
		}
	}
}
