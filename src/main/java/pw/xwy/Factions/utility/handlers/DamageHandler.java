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
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import pw.xwy.Factions.objects.faction.XPlayer;
import pw.xwy.Factions.objects.faction.XPlayerFaction;
import pw.xwy.Factions.utility.managers.PlayerManager;

public class DamageHandler implements Listener {
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
				if (PlayerManager.getPlayer(p).isNoFallDamage()) {
					e.setCancelled(true);
					PlayerManager.getPlayer(p).setNoFallDamage(false);
				}
			}
		}
	}
	
	@EventHandler
	public void onHit(EntityDamageByEntityEvent e) {
		if (!e.isCancelled()) {
			if (e.getEntity() instanceof Player) {
				Player p = (Player) e.getEntity();
				XPlayerFaction faction = PlayerManager.getOnlinePlayerFaction(p);
				if (e.getDamager() instanceof Player) {
					Player damager = (Player) e.getDamager();
					
					XPlayerFaction faction1 = PlayerManager.getOnlinePlayerFaction(damager);
					if (faction != null) {
						if (faction == faction1) {
							e.setCancelled(true);
							damager.sendMessage("You cannot hurt faction members!");
						} else if (faction.getAllies().contains(faction1)) {
							e.setCancelled(true);
							damager.sendMessage("You cannot hurt allies!");
						}
					}
				} else if (e.getDamager() instanceof Projectile) {
					Projectile damager = (Projectile) e.getDamager();
					if (damager.getShooter() instanceof Player) {
						XPlayer shooter = XPlayer.getXPlayer((Player) damager.getShooter());
						
						XPlayerFaction faction1 = shooter.getFaction();
						if (faction != null) {
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
		}
		
	}
	
}
