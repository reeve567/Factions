package pw.xwy.factions.utility.handlers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pw.xwy.factions.objects.faction.XPlayer;
import pw.xwy.factions.utility.Configurations.Config;
import pw.xwy.factions.utility.managers.PlayerManager;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class JoinHandler implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent e) {
		XPlayer xPlayer = new XPlayer(e.getPlayer().getUniqueId(), Config.getPlayer(e.getPlayer()));
		PlayerManager.addOnlinePlayer(xPlayer);
		
		if (xPlayer.getFaction() != null) {
			xPlayer.getFaction().setOnlinePlayers(xPlayer.getFaction().getOnlinePlayers() + 1);
		}
		
		xPlayer.save();
	}
	
}
