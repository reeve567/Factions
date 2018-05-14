package pw.xwy.factions.commands.factions.subcommands;

import pw.xwy.factions.objects.CommandHandler;
import pw.xwy.factions.objects.SubCommand;
import pw.xwy.factions.objects.faction.XPlayer;
import pw.xwy.factions.objects.faction.XPlayerFaction;
import pw.xwy.factions.utility.Configurations.Messages;
import pw.xwy.factions.utility.managers.ClaimManager;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

@CommandHandler
public class Unclaim extends SubCommand {
	public Unclaim() {
		super("unclaim", "", "unclaim stuff");
	}
	
	@Override
	public void run(XPlayer p, String[] args) {
		if (p.facCheck()) {
			XPlayerFaction faction = p.getFaction();
			if (p.permCheck("unclaim", "f.unclaim.others")) {
				if (args.length == 2) {
					if (args[1].equalsIgnoreCase("all")) {
						faction.claim.removeAll(faction);
						p.sendMessages(Messages.getUnclaimed());
						return;
					}
				}
				if (ClaimManager.getChunk(p.getLocation().getChunk()) == faction) {
					if (faction.claim.remove(p.getLocation().getChunk(), faction)) {
						p.sendMessages(Messages.getUnclaimed());
					} else {
						p.sendMessage("something went wrong, error id: 2302");
					}
				} else {
					p.sendMessage("not your claim");
				}
			}
		}
	}
}
