package pw.xwy.factions.commands.factions.subcommands;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/9/18 11:07 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

import pw.xwy.factions.objects.CommandHandler;
import pw.xwy.factions.objects.SubCommand;
import pw.xwy.factions.objects.faction.XPlayer;
import pw.xwy.factions.utility.managers.PlayerManager;

@CommandHandler
public class Chatspy extends SubCommand {
	public Chatspy() {
		super("chatspy", "", "Toggle chatspy on/off", true);
	}
	
	@Override
	public void run(XPlayer p, String[] args) {
		
		if (PlayerManager.chatspies.contains(p)) {
			PlayerManager.chatspies.remove(p);
			p.sendMessage("disabled");
		} else {
			PlayerManager.chatspies.add(p);
			p.sendMessage("enabled");
		}
	}
}
