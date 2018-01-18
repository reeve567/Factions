package pw.xwy.Factions.utility.handlers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pw.xwy.Factions.objects.XPlayer;
import pw.xwy.Factions.utility.Configurations.Config;
import pw.xwy.Factions.utility.managers.PlayerManager;

public class JoinHandler implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		XPlayer xPlayer = new XPlayer(e.getPlayer().getUniqueId(), Config.getPlayer(e.getPlayer()));
		PlayerManager.addXPlayer(xPlayer);
		
		
		if (xPlayer.getFaction() != null) {
			xPlayer.getFaction().setOnlinePlayers(xPlayer.getFaction().getOnlinePlayers() + 1);
		}
		
		xPlayer.playerConfig.save();
	}
	
}
