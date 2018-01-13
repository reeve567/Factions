package pw.xwy.Factions.utility.tasks;

import org.bukkit.scheduler.BukkitRunnable;
import pw.xwy.Factions.objects.XPlayer;
import pw.xwy.Factions.utility.Config;
import pw.xwy.Factions.utility.StringUtility;

public class SpawnWarmupTask extends BukkitRunnable {
	
	private XPlayer xPlayer;
	private int time = (int) (Config.spawnTeleportWarmupTime*10);
	
	public SpawnWarmupTask(XPlayer xPlayer) {
		this.xPlayer = xPlayer;
	}
	
	@Override
	public void run() {
		if (xPlayer.canceled) {
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
		}
	}
}
