package pw.xwy.Factions.commands.factions.subcommands;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/14/18 8:44 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

import pw.xwy.Factions.objects.CommandHandler;
import pw.xwy.Factions.objects.SubCommand;
import pw.xwy.Factions.objects.faction.XPlayer;
import pw.xwy.Factions.objects.faction.XWarp;
import pw.xwy.Factions.utility.Configurations.Messages;

@CommandHandler
public class Setwarp extends SubCommand {
	public Setwarp() {
		super("setwarp", "<name> [password]", "Sets a warp, with a (optional) password.");
	}
	
	@Override
	public void run(XPlayer p, String[] args) {
		if (p.facCheck()) {
			if (p.hasPermission("setwarps")) {
				if (args.length == 2) {
					XWarp warp = new XWarp(args[1], p.getLocation());
					p.getFaction().getWarps().add(warp);
					p.sendMessage("warp set");
				} else if (args.length == 3) {
					XWarp warp = new XWarp(args[1], p.getLocation(), args[2]);
					p.getFaction().getWarps().add(warp);
					p.sendMessage("warp set");
				} else {
					sendHelpMessage(p);
				}
			} else {
				p.sendMessages(Messages.getNoPermission());
			}
		}
	}
}
