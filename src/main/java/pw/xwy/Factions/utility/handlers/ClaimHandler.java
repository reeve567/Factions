package pw.xwy.Factions.utility.handlers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import pw.xwy.Factions.utility.managers.ClaimManager;
import pw.xwy.Factions.utility.managers.FactionManager;
import pw.xwy.Factions.utility.managers.PlayerManager;

public class ClaimHandler implements Listener {
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Player pl = e.getPlayer();
		if (ClaimManager.isClaimed(e.getBlock().getChunk())) {
			if (!PlayerManager.getPlayerFaction(pl).equals(ClaimManager.getChunk(e.getBlock().getChunk()))) {
				e.setCancelled(true);
			}
			else if (!FactionManager.getUUIDFaction(pl.getUniqueId()).getRole(pl.getUniqueId()).hasPerm("break",true)) {
				e.setCancelled(true);
			}
		}
		
	}
	
	
	
}