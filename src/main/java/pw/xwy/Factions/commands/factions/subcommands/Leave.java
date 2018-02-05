package pw.xwy.Factions.commands.factions.subcommands;

import org.bukkit.entity.Player;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.objects.XPlayer;
import pw.xwy.Factions.utility.managers.PlayerManager;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

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
