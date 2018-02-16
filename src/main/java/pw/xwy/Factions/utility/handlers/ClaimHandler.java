package pw.xwy.Factions.utility.handlers;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import pw.xwy.Factions.objects.faction.XFaction;
import pw.xwy.Factions.objects.faction.XPlayer;
import pw.xwy.Factions.utility.managers.ClaimManager;
import pw.xwy.Factions.utility.managers.PlayerManager;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class ClaimHandler implements Listener {
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		XPlayer pl = XPlayer.getXPlayer(e.getPlayer());
		if (ClaimManager.isClaimed(e.getBlock().getChunk())) {
			if (!pl.isAdminMode()) {
				if (!PlayerManager.getOnlinePlayerFaction(pl).equals(ClaimManager.getChunk(e.getBlock().getChunk()))) {
					e.setCancelled(true);
				} else if (!pl.hasPermission("break")) {
					e.setCancelled(true);
				}
			}
		}
		if (!e.isCancelled()) {
			XFaction faction = ClaimManager.getChunk(e.getBlock().getChunk());
			if (faction != null) {
				if (e.getBlock().getType() == Material.MOB_SPAWNER) {
					faction.claim.getChunk(e.getBlock().getChunk()).calculateValue();
				}
			}
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		XPlayer pl = XPlayer.getXPlayer(e.getPlayer());
		if (ClaimManager.isClaimed(e.getBlock().getChunk())) {
			if (!pl.isAdminMode()) {
				if (!PlayerManager.getOnlinePlayerFaction(pl).equals(ClaimManager.getChunk(e.getBlock().getChunk()))) {
					e.setCancelled(true);
				} else if (!pl.hasPermission("place")) {
					e.setCancelled(true);
				}
			}
		}
		if (!e.isCancelled()) {
			XFaction faction = ClaimManager.getChunk(e.getBlock().getChunk());
			if (faction != null) {
				if (e.getBlockPlaced().getType() == Material.MOB_SPAWNER) {
					faction.claim.getChunk(e.getBlock().getChunk()).calculateValue();
				}
			}
		}
	}
}