package pw.xwy.Factions.utility.handlers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import pw.xwy.Factions.objects.XPlayer;
import pw.xwy.Factions.utility.managers.PlayerManager;

public class LeaveHandler implements Listener {
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		XPlayer xPlayer = PlayerManager.getXPlayer(e.getPlayer());
		xPlayer.playerConfig.save();
		if (xPlayer.getFaction() != null) {
			xPlayer.getFaction().setOnlinePlayers(xPlayer.getFaction().getOnlinePlayers() - 1);
		}
		
		PlayerManager.removeXPlayer(xPlayer);
	}
	
}
