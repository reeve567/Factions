package pw.xwy.factions.commands.factions.subcommands;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/4/18 9:23 AM                              /
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

@CommandHandler
public class Sethome extends SubCommand {
	public Sethome() {
		super("sethome", "", "Set your faction's home");
	}
	
	@Override
	public void run(XPlayer p, String[] args) {
		XPlayerFaction faction = PlayerManager.getOnlinePlayerFaction(p);
		if (faction != null) {
			if (faction.hasPermission(p, "sethome")) {
				if (faction.claim.isInClaim(p.getLocation().getChunk())) {
					faction.setHome(p.getLocation());
					p.sendMessage("home set");
				} else {
					p.sendMessage("not in claim");
				}
			} else {
				p.sendMessages(Messages.getNoPermission());
			}
		} else {
			p.sendMessages(Messages.getWhoSender());
		}
		
	}
}
