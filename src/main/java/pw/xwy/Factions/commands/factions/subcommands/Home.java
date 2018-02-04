package pw.xwy.Factions.commands.factions.subcommands;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/4/18 9:34 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

import org.bukkit.entity.Player;
import pw.xwy.Factions.XFactionsCore;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.utility.Configurations.Messages;
import pw.xwy.Factions.utility.managers.PlayerManager;
import pw.xwy.Factions.utility.tasks.HomeWarmupTask;

public class Home extends SubCommand {
	public Home() {
		super("home", "", "Teleports you to your faction home");
	}
	
	@Override
	public void run(Player p, String[] args) {
		XFaction faction = PlayerManager.getPlayerFaction(p);
		if (faction != null) {
			if (faction.hasPermission(p, "home")) {
				
				if (faction.getHome() != null) {
					p.sendMessage("asdg");
					new HomeWarmupTask(p).runTaskTimer(XFactionsCore.getXFactionsCore(), 0, 2);
				} else {
					p.sendMessage("null");
				}
				
			} else {
				p.sendMessage("no permission");
			}
		} else {
			Messages.sendMessages(p, Messages.getWhoSender());
		}
	}
}
