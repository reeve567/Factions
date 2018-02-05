package pw.xwy.Factions.objects;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pw.xwy.Factions.enums.ChatType;
import pw.xwy.Factions.utility.Configurations.Config;
import pw.xwy.Factions.utility.managers.FactionManager;

import java.util.ArrayList;
import java.util.UUID;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

/**
 * For the most part this isn't used, however it is still updated to work with things
 */

public class XPlayer implements XFactionPlayer {
	
	public int homeCooldown = 0;
	private boolean cancelled = false;
	private boolean stopNextFallDamage = false;
	private XPlayerConfig config;
	private String name;
	private Player player;
	private double power = 0.0;
	private XFaction faction = null;
	private ChatType chatType = ChatType.PUBLIC;
	private TeleportWarmupTask teleportWarmupTask = null;
	private ArrayList<XFaction> invites = new ArrayList<>();
	
	public XPlayer(Player player) {
		this.player = player;
		config = new XPlayerConfig(this);
	}
	
	public XPlayer(UUID id, XPlayerConfig s) {
		try {
			config = s;
			player = Bukkit.getPlayer(id);
			this.name = config.getName();
			power = s.getPower();
			String st = s.getFactionUUID();
			
			if (!st.equalsIgnoreCase("") && !st.equalsIgnoreCase("no-faction")) {
				faction = FactionManager.getFactionFromUUID(UUID.fromString(st));
			} else {
				faction = null;
			}
		} catch (NullPointerException ignored) {
			System.out.println("Player not found in config, creating file now...");
			player = Bukkit.getPlayer(id);
			power = 0;
			faction = null;
		}
	}
	
	@Override
	public void addPower() {
		int po = (int) (power * 10);
		po += 1;
		power = po / 10.0;
		if (po >= Config.maxPower) {
			power = Config.maxPower;
		}
		config.save(this);
		
	}
	
	@Override
	public ChatType getChatType() {
		return chatType;
	}
	
	@Override
	public void setChatType(ChatType chatType) {
		this.chatType = chatType;
	}
	
	@Override
	public TeleportWarmupTask getCurrentTeleportTask() {
		return teleportWarmupTask;
	}
	
	@Override
	public void setCurrentTeleportTask(TeleportWarmupTask task) {
		teleportWarmupTask = task;
	}
	
	@Override
	public XFaction getFaction() {
		return faction;
	}
	
	@Override
	public void setFaction(XFaction xFaction) {
		faction = xFaction;
		if (xFaction != null) {
			config.set("info.faction", xFaction.id.toString());
		} else {
			config.set("info.faction", "no-faction");
		}
		
		config.save();
	}
	
	public UUID getID() {
		return player.getUniqueId();
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public Player getPlayer() {
		return player;
	}
	
	@Override
	public double getPower() {
		return power;
	}
	
	@Override
	public boolean hasInvite(XFaction faction) {
		return invites.contains(faction);
	}
	
	@Override
	public boolean invite(XFaction faction) {
		if (!invites.contains(faction)) {
			invites.add(faction);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isCancelled() {
		return cancelled;
	}
	
	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	
	@Override
	public boolean isNoFallDamage() {
		return stopNextFallDamage;
	}
	
	@Override
	public void setNoFallDamage(boolean fallDamage) {
		stopNextFallDamage = fallDamage;
	}
	
	@Override
	public boolean isTeleporting() {
		return teleportWarmupTask != null;
	}
	
	@Override
	public boolean revokeInvite(XFaction faction) {
		if (invites.contains(faction)) {
			invites.remove(faction);
			return true;
		}
		return false;
	}
	
	@Override
	public void save() {
		config.save();
	}
	
	@Override
	public void stopTeleporting() {
		teleportWarmupTask = null;
	}
	
	@Override
	public void toggleCancelled() {
		cancelled = !cancelled;
	}
}
