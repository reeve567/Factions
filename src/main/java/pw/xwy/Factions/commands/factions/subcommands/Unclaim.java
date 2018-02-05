package pw.xwy.Factions.commands.factions.subcommands;

import org.bukkit.entity.Player;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.utility.managers.PlayerManager;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class Unclaim extends SubCommand {
	public Unclaim() {
		super("Unclaim", "", "unclaim stuff");
	}
	
	private boolean hasPerm(XFaction faction, Player p) {
		return p.hasPermission("factions.unclaim.others") || faction != null && faction.getRole(p.getUniqueId()).hasPerm("unclaim", true);
	}
	
	@Override
	public void run(Player p, String[] args) {
		XFaction faction;
		if ((faction = PlayerManager.getOnlinePlayerFaction(p)) != null) {
			
			if (hasPerm(faction, p)) {
				faction.claim.remove(p.getLocation().getChunk(), faction);
			}
			
		}
	}
}
