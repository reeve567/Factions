package pw.xwy.Factions.utility.handlers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
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

public class LeaveHandler implements Listener {
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		XPlayer xPlayer = (XPlayer) PlayerManager.getPlayer(e.getPlayer());
		xPlayer.save();
		if (xPlayer.getFaction() != null) {
			xPlayer.getFaction().setOnlinePlayers(xPlayer.getFaction().getOnlinePlayers() - 1);
		}
		
		PlayerManager.removePlayer(xPlayer);
	}
	
}
