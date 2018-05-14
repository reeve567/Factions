package pw.xwy.factions.commands.factions.subcommands;

import pw.xwy.factions.objects.CommandHandler;
import pw.xwy.factions.objects.SubCommand;
import pw.xwy.factions.objects.faction.XPlayer;
import pw.xwy.factions.utility.managers.PlayerManager;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

@CommandHandler
public class Leave extends SubCommand {
	public Leave() {
		super("leave", "", "Leaves your current faction.");
	}
	
	@Override
	public void run(XPlayer p, String[] args) {
		if (PlayerManager.getOnlinePlayerFaction(p) != null) {
			if (!PlayerManager.getOnlinePlayerFaction(p).getLeader().equals(p.getUniqueId())) {
				PlayerManager.getOnlinePlayerFaction(p).leave(PlayerManager.getPlayer(p), true);
			}
		}
	}
}
