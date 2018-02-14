package pw.xwy.Factions.commands.factions.subcommands;

import pw.xwy.Factions.objects.SubCommand;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.objects.faction.XPlayerFaction;
import pw.xwy.Factions.objects.faction.XPlayer;
import pw.xwy.Factions.utility.managers.FactionManager;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class Ally extends SubCommand {
	public Ally() {
		super("ally", "<faction>", "Allows you to send another faction an ally request.");
	}
	
	@Override
	public void run(XPlayer p, String[] args) {
		if (p.facCheck()) {
			if (p.permCheck("ally")) {
				if (args.length < 2) {
					sendHelpMessage(p);
				} else {
					XPlayerFaction faction = p.getFaction();
					XFaction faction1 = FactionManager.getFactionByName(args[1]);
					
					if (faction1 != null && faction1 instanceof XPlayerFaction) {
						if (!faction.getAllies().contains(faction1)) {
							if (faction.allyRequests.contains(faction1)) {
								//accept ally request from faction1
								faction.addAlly((XPlayerFaction) faction1);
							} else {
								//send ally request
								faction.sendAllyRequest((XPlayerFaction) faction1);
							}
							
						} else {
							p.sendMessage("already ally");
							
						}
					} else {
						//not a faction
						
						p.sendMessage("faction not found");
					}
				}
			}
		}
	}
}
