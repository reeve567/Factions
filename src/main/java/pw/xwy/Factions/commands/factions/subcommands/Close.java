package pw.xwy.factions.commands.factions.subcommands;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/4/18 11:20 AM                             /
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
public class Close extends SubCommand {
	public Close() {
		super("close", "", "Closes your faction so people can't join");
	}
	
	@Override
	public void run(XPlayer p, String[] args) {
		XPlayerFaction faction = PlayerManager.getOnlinePlayerFaction(p);
		
		if (faction == null) {
			p.sendMessages(Messages.getWhoSender());
			return;
		}
		
		if (!faction.hasPermission(p, "toggleOpen")) {
			p.sendMessages(Messages.getNoPermission());
			return;
		}
		
		faction.open = false;
		
		p.sendMessage("Faction is now closed");
	}
}
