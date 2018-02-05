package pw.xwy.Factions.commands.factions.subcommands;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/4/18 11:20 AM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.objects.XPlayer;
import pw.xwy.Factions.utility.Configurations.Messages;
import pw.xwy.Factions.utility.managers.PlayerManager;

public class Open extends SubCommand {
	public Open() {
		super("open", "", "Opens your faction for people to join");
	}
	
	@Override
	public void run(XPlayer p, String[] args) {
		XFaction faction = PlayerManager.getOnlinePlayerFaction(p);
		
		if (faction == null) {
			p.sendMessages(Messages.getWhoSender());
			return;
		}
		
		if (!faction.hasPermission(p, "toggleOpen")) {
			p.sendMessage("no permission");
			return;
		}
		
		faction.open = true;
		
		p.sendMessage("Faction is now open");
	}
}