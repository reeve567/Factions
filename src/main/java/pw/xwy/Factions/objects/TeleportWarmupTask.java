package pw.xwy.Factions.objects;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/4/18 4:29 PM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import pw.xwy.Factions.utility.managers.PlayerManager;

public abstract class TeleportWarmupTask extends BukkitRunnable {
	
	public int time;
	protected XPlayer player;
	
	protected TeleportWarmupTask(Player p, int time) {
		this.player = (XPlayer) PlayerManager.getPlayer(p);
		this.time = time;
		if (player.isTeleporting()) {
			p.sendMessage("Already teleporting");
			cancel();
		} else {
			player.setCurrentTeleportTask(this);
			player.setCancelled(false);
			p.sendMessage("teleporting...");
		}
	}
	
}
