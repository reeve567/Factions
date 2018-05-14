package pw.xwy.factions.commands.factions.subcommands;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/4/18 9:03 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

import pw.xwy.factions.objects.CommandHandler;
import pw.xwy.factions.objects.SubCommand;
import pw.xwy.factions.objects.faction.XPlayer;
import pw.xwy.factions.objects.faction.XPlayerFaction;
import pw.xwy.factions.utility.Configurations.Messages;
import pw.xwy.factions.utility.managers.PlayerManager;

/**
 * Changed to using fly whenever they're in the chunk
 */
@Deprecated
@CommandHandler
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
