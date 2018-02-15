package pw.xwy.Factions.commands.factions.subcommands;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/4/18 9:23 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

import pw.xwy.Factions.objects.CommandHandler;
import pw.xwy.Factions.objects.SubCommand;
import pw.xwy.Factions.objects.faction.XPlayer;
import pw.xwy.Factions.objects.faction.XPlayerFaction;
import pw.xwy.Factions.utility.Configurations.Messages;
import pw.xwy.Factions.utility.managers.PlayerManager;

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
