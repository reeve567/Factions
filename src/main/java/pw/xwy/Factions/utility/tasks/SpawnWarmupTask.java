package pw.xwy.factions.utility.tasks;

import org.bukkit.entity.Player;
import pw.xwy.factions.objects.TeleportWarmupTask;
import pw.xwy.factions.objects.faction.XPlayer;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class SpawnWarmupTask extends TeleportWarmupTask {
	
	private XPlayer player;
	//private int time = (int) (Config.spawnTeleportWarmupTime*10);
	
	public SpawnWarmupTask(Player player) {
		super(player, 0);
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
