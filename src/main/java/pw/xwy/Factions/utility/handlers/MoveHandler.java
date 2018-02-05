package pw.xwy.Factions.utility.handlers;

import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.objects.XPlayer;
import pw.xwy.Factions.utility.managers.ClaimManager;
import pw.xwy.Factions.utility.managers.PlayerManager;

import java.util.HashMap;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class MoveHandler implements Listener {
	
	private HashMap<Player, Chunk> lastChunk = new HashMap<>();
	private HashMap<Player, XFaction> lastFaction = new HashMap<>();
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if (!lastFaction.containsKey(e.getPlayer())) {
			lastFaction.put(e.getPlayer(), ClaimManager.getChunk(e.getTo().getChunk()));
		}
		if (!lastChunk.containsKey(e.getPlayer())) {
			lastChunk.put(e.getPlayer(), e.getTo().getChunk());
		} else {
			if (lastChunk.get(e.getPlayer()) != e.getTo().getChunk()) {
				if (lastFaction.get(e.getPlayer()) != ClaimManager.getChunk(e.getTo().getChunk()) || ClaimManager.getChunk(e.getFrom().getChunk()) != ClaimManager.getChunk(e.getTo().getChunk())) {
					lastFaction.put(e.getPlayer(), ClaimManager.getChunk(e.getTo().getChunk()));
					XFaction faction = lastFaction.get(e.getPlayer());
					if (faction != PlayerManager.getPlayerFaction(e.getPlayer())) {
						XFaction faction1 = PlayerManager.getPlayerFaction(e.getPlayer());
						
						if (faction1.flying.contains(e.getPlayer().getUniqueId())) {
							faction1.toggleFlying(e.getPlayer());
						}
						
					}
					e.getPlayer().sendMessage(ClaimManager.getMessage(e.getTo().getChunk(), e.getPlayer()));
				}
				lastChunk.put(e.getPlayer(), e.getTo().getChunk());
			}
		}
		/*if (PlayerManager.getXPlayer(e.getPlayer()).spawnCooldown > 0) {
			PlayerManager.getXPlayer(e.getPlayer()).canceled = true;
		} else */
		if (((XPlayer) PlayerManager.getPlayer(e.getPlayer())).homeCooldown > 0) {
			PlayerManager.getPlayer(e.getPlayer()).setCancelled(true);
		}
	}
}
