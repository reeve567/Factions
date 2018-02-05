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
import pw.xwy.Factions.objects.TeleportWarmupTask;
import pw.xwy.Factions.objects.XFactionPlayer;
import pw.xwy.Factions.objects.XPlayer;
import pw.xwy.Factions.utility.Configurations.Config;
import pw.xwy.Factions.utility.StringUtility;
import pw.xwy.Factions.utility.managers.PlayerManager;

public class HomeWarmupTask extends TeleportWarmupTask {
	
	public HomeWarmupTask(Player p) {
		super(p, (int) (Config.homeWarmupTime*10));
	}
	
	@Override
	public void run() {
		if (player.isCancelled()) {
			time = 0;
			player.getPlayer().sendMessage(StringUtility.conv("&cCancelled"));
			player.setCurrentTeleportTask(null);
			cancel();
		}
		time--;
		player.homeCooldown = time;
		if (time == 0) {
			player.getPlayer().sendMessage(StringUtility.conv("&aTeleported"));
			player.getPlayer().teleport(player.getFaction().getHome());
			cancel();
		}
	}
}
