package pw.xwy.Factions.commands.factions.subcommands;

import org.bukkit.entity.Player;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.utility.Configurations.Messages;
import pw.xwy.Factions.utility.managers.FactionManager;
import pw.xwy.Factions.utility.managers.PlayerManager;

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
	public void run(Player p, String[] args) {
		XFaction faction = PlayerManager.getPlayerFaction(p);
		if (faction.getRole(p.getUniqueId()).hasPerm("ally", true)) {
			if (args.length < 2) {
				Messages.sendMessages(p, Messages.getCommandHelpFormat(this));
			} else {
				XFaction faction1 = FactionManager.getFactionByName(args[1]);
				
				if (faction1 != null) {
					if (!faction.getAllies().contains(faction1)) {
						if (faction.allyRequests.contains(faction1)) {
							//accept ally request from faction1
							faction.addAlly(faction1);
						} else {
							//send ally request
							faction.sendAllyRequest(faction1);
							Messages.sendMessages(p, Messages.getAllyRequestRecieved(faction1));
						}
						
					} else {
						//already ally
						
					}
				} else {
					//not a faction
					
				}
			}
		}
	}
}
