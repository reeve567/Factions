package pw.xwy.Factions.commands.factions.subcommands;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/4/18 9:34 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

import pw.xwy.Factions.XFactionsCore;
import pw.xwy.Factions.objects.CommandHandler;
import pw.xwy.Factions.objects.SubCommand;
import pw.xwy.Factions.objects.faction.XPlayer;
import pw.xwy.Factions.objects.faction.XPlayerFaction;
import pw.xwy.Factions.utility.Configurations.Messages;
import pw.xwy.Factions.utility.managers.PlayerManager;
import pw.xwy.Factions.utility.tasks.HomeWarmupTask;

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
