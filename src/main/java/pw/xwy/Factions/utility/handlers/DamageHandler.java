package pw.xwy.Factions.utility.handlers;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/4/18 10:57 AM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.utility.managers.PlayerManager;

public class DamageHandler implements Listener {
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
				if (PlayerManager.getXPlayer(p).stopNextFallDamage) {
					e.setCancelled(true);
				}
			}
		}
	}
	
	
	@EventHandler
	public void onHit(EntityDamageByEntityEvent e) {
		if (!e.isCancelled()) {
			if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
				Player p = (Player) e.getEntity();
				Player damager = (Player) e.getDamager();
				
				XFaction faction = PlayerManager.getPlayerFaction(p);
				XFaction faction1 = PlayerManager.getPlayerFaction(damager);
				if (faction == faction1) {
					e.setCancelled(true);
					damager.sendMessage("You cannot hurt faction members!");
				} else if (faction.getAllies().contains(faction1)) {
					e.setCancelled(true);
					damager.sendMessage("You cannot hurt allies!");
				}
				
			}
		}
		
	}
	
}
