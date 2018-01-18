package pw.xwy.Factions.utility.handlers;

import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.utility.Configurations.Spawners;
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
			} else if (!FactionManager.getPlayerUUIDFaction(pl.getUniqueId()).getRole(pl.getUniqueId()).hasPerm("break", true)) {
				e.setCancelled(true);
			}
		}
		if (!e.isCancelled()) {
			XFaction faction = ClaimManager.getChunk(e.getBlock().getChunk());
			if (faction != null) {
				if (e.getBlock().getType() == Material.MOB_SPAWNER) {
					CreatureSpawner creature = (CreatureSpawner) e.getBlock();
					faction.setValue(faction.getValue() - Spawners.getInstance().getPrice(creature.getSpawnedType()));
				}
				
			}
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Player pl = e.getPlayer();
		if (ClaimManager.isClaimed(e.getBlock().getChunk())) {
			if (!PlayerManager.getPlayerFaction(pl).equals(ClaimManager.getChunk(e.getBlock().getChunk()))) {
				e.setCancelled(true);
			} else if (!FactionManager.getPlayerUUIDFaction(pl.getUniqueId()).getRole(pl.getUniqueId()).hasPerm("place", true)) {
				e.setCancelled(true);
			}
		}
		if (!e.isCancelled()) {
			XFaction faction = ClaimManager.getChunk(e.getBlock().getChunk());
			if (faction != null) {
				if (e.getBlockPlaced().getType() == Material.MOB_SPAWNER) {
					CreatureSpawner creature = (CreatureSpawner) e.getBlockPlaced();
					faction.setValue(faction.getValue() + Spawners.getInstance().getPrice(creature.getSpawnedType()));
				}
				
			}
		}
	}
}