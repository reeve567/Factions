package pw.xwy.factions.commands.factions.subcommands;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/4/18 9:34 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

import pw.xwy.factions.XFactionsCore;
import pw.xwy.factions.objects.CommandHandler;
import pw.xwy.factions.objects.SubCommand;
import pw.xwy.factions.objects.faction.XPlayer;
import pw.xwy.factions.objects.faction.XPlayerFaction;
import pw.xwy.factions.utility.Configurations.Messages;
import pw.xwy.factions.utility.managers.PlayerManager;
import pw.xwy.factions.utility.tasks.HomeWarmupTask;

@CommandHandler
public class Home extends SubCommand {
	public Home() {
		super("home", "", "Teleports you to your faction home");
	}
	
	@Override
	public void run(XPlayer p, String[] args) {
		XPlayerFaction faction = PlayerManager.getOnlinePlayerFaction(p);
		if (faction != null) {
			if (faction.hasPermission(p, "home")) {
				if (faction.getHome() != null) {
					if (faction.claim.isInClaim(faction.getHome().getChunk())) {
						new HomeWarmupTask(p).runTaskTimer(XFactionsCore.getXFactionsCore(), 0, 2);
					} else {
						p.sendMessage("your home is in an area you no longer have a claim at.");
						
					}
				} else {
					p.sendMessage("no home set");
				}
			} else {
				p.sendMessages(Messages.getNoPermission());
			}
		} else {
			p.sendMessages(Messages.getWhoSender());
		}
	}
}
