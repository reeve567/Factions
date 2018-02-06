package pw.xwy.Factions.commands.factions.subcommands;

import org.bukkit.entity.Player;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.objects.XPlayer;
import pw.xwy.Factions.utility.Configurations.Messages;
import pw.xwy.Factions.utility.managers.ClaimManager;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class Unclaim extends SubCommand {
	public Unclaim() {
		super("Unclaim", "", "unclaim stuff");
	}
	
	private boolean hasPerm(XFaction faction, Player p) {
		return faction.hasPermission(p, "unclaim", "factions.unclaim.others");
	}
	
	@Override
	public void run(XPlayer p, String[] args) {
		if (p.facCheck()) {
			XFaction faction = p.getFaction();
			if (p.permCheck("unclaim", "factions.unclaim.others")) {
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
