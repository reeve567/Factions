package pw.xwy.Factions.utility.tasks;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/4/18 9:35 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import pw.xwy.Factions.objects.XPlayer;
import pw.xwy.Factions.utility.Configurations.Config;
import pw.xwy.Factions.utility.StringUtility;
import pw.xwy.Factions.utility.managers.PlayerManager;

public class HomeWarmupTask extends BukkitRunnable {
	
	private XPlayer player;
	private int time = (int) Config.homeWarmupTime*10;
	
	public HomeWarmupTask(Player p) {
		this.player = PlayerManager.getXPlayer(p);
		
	}
	
	@Override
	public void run() {
		if (player.canceled) {
			time = 0;
			player.getPlayer().sendMessage(StringUtility.conv("&cCancelled"));
			cancel();
		}
		time--;
		player.spawnCooldown = time;
		if (time == 0) {
			player.getPlayer().sendMessage(StringUtility.conv("&aTeleported"));
			player.getPlayer().teleport(player.getFaction().getHome());
			cancel();
		}
	}
}