package pw.xwy.Factions.commands.factions.subcommands;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/4/18 9:03 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

import pw.xwy.Factions.objects.SubCommand;
import pw.xwy.Factions.objects.faction.XPlayerFaction;
import pw.xwy.Factions.objects.faction.XPlayer;
import pw.xwy.Factions.utility.Configurations.Messages;
import pw.xwy.Factions.utility.managers.PlayerManager;

/**
 * Changed to using fly whenever they're in the chunk
 */
@Deprecated
public class Fly extends SubCommand {
	public Fly() {
		super("fly", "", "Allows you to fly inside faction claim.");
	}
	
	@Override
	public void run(XPlayer p, String[] args) {
		
		XPlayerFaction faction = PlayerManager.getOnlinePlayerFaction(p);
		if (faction != null) {
			if (faction.hasPermission(p, "fly")) {
				if (faction.claim.isInClaim(p.getLocation().getChunk())) {
					faction.toggleFlying(p);
				} else {
					p.sendMessage("you are not in your claim");
				}
			} else {
				p.sendMessages(Messages.getNoPermission());
			}
		} else {
			p.sendMessages(Messages.getWhoSender());
		}
	}
}
