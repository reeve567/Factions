package pw.xwy.Factions.utility.tasks;

import org.bukkit.scheduler.BukkitRunnable;
import pw.xwy.Factions.objects.XPlayer;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class SpawnWarmupTask extends BukkitRunnable {
	
	private XPlayer xPlayer;
	//private int time = (int) (Config.spawnTeleportWarmupTime*10);
	
	public SpawnWarmupTask(XPlayer xPlayer) {
		this.xPlayer = xPlayer;
	}
	
	@Override
	public void run() {
		/*if (xPlayer.canceled) {
			time = 0;
			xPlayer.getPlayer().sendMessage(StringUtility.conv("&cCancelled"));
			cancel();
		}
		time--;
		xPlayer.spawnCooldown = time;
		if (time == 0) {
			xPlayer.getPlayer().sendMessage(StringUtility.conv("&aTeleported"));
			xPlayer.getPlayer().teleport(Config.spawn);
			cancel();
		}*/
	}
}
