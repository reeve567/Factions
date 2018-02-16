package pw.xwy.Factions.objects.faction;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import pw.xwy.Factions.enums.ChatType;
import pw.xwy.Factions.objects.TeleportWarmupTask;
import pw.xwy.Factions.utility.Configurations.Config;
import pw.xwy.Factions.utility.Configurations.Messages;
import pw.xwy.Factions.utility.StringUtility;
import pw.xwy.Factions.utility.managers.FactionManager;
import pw.xwy.Factions.utility.managers.PlayerManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/13/18 6:24 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class XPlayer extends CraftPlayer implements XFactionOnlinePlayer {
	
	public int homeCooldown = 0;
	private boolean cancelled = false;
	private boolean stopNextFallDamage = false;
	private boolean adminMode = false;
	private XPlayerConfig config;
	private double power;
	private XPlayerFaction faction;
	private ChatType chatType = ChatType.PUBLIC;
	private TeleportWarmupTask teleportWarmupTask = null;
	private ArrayList<XPlayerFaction> invites = new ArrayList<>();
	
	public XPlayer(Player player) {
		super((CraftServer) Bukkit.getServer(), ((CraftPlayer) player).getHandle());
		config = new XPlayerConfig(this);
		try {
			faction = FactionManager.getFactionFromUUID(UUID.fromString(config.getFactionUUID()));
		} catch (IllegalArgumentException e) {
			faction = null;
		}
		power = config.getPower();
		initChatspy();
	}
	
	public XPlayer(UUID id, XPlayerConfig s) {
		super((CraftServer) Bukkit.getServer(), ((CraftPlayer) Bukkit.getPlayer(id)).getHandle());
		config = s;
		power = s.getPower();
		String st = s.getFactionUUID();
		try {
			faction = FactionManager.getFactionFromUUID(UUID.fromString(st));
		} catch (IllegalArgumentException e) {
			faction = null;
		}
		initChatspy();
	}
	
	public static XPlayer getXPlayer(Player p) {
		return (XPlayer) PlayerManager.getPlayer(p);
	}
	
	public static XPlayer getXPlayer(UUID uuid) {
		return (XPlayer) PlayerManager.getPlayer(Bukkit.getPlayer(uuid));
	}
	
	private void initChatspy() {
		PlayerManager.chatspies.add(this);
	}
	
	@Override
	public void _INVALID_damage(int i) {
	
	}
	
	@Override
	public void _INVALID_damage(int i, Entity entity) {
	
	}
	
	@Override
	public int _INVALID_getHealth() {
		return 0;
	}
	
	@Override
	public int _INVALID_getLastDamage() {
		return 0;
	}
	
	@Override
	public int _INVALID_getMaxHealth() {
		return 0;
	}
	
	@Override
	public void _INVALID_setHealth(int i) {
	
	}
	
	@Override
	public void _INVALID_setLastDamage(int i) {
	
	}
	
	@Override
	public void _INVALID_setMaxHealth(int i) {
	
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
	
	public boolean facCheck() {
		if (faction != null) {
			return true;
		}
		sendMessages(Messages.getNotInFaction());
		return false;
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
	public XPlayerFaction getFaction() {
		return faction;
	}
	
	@Override
	public void setFaction(XPlayerFaction xPlayerFaction) {
		faction = xPlayerFaction;
		if (xPlayerFaction != null) {
			config.set("info.faction", xPlayerFaction.getId().toString());
		} else {
			config.set("info.faction", "no-faction");
		}
		
		config.save();
	}
	
	@Override
	public double getPower() {
		return power;
	}
	
	@Override
	public boolean hasInvite(XPlayerFaction faction) {
		return invites.contains(faction);
	}
	
	@Override
	public boolean hasPermission(String s) {
		return adminMode || faction != null && faction.hasPermission(this, s) || super.hasPermission(s);
	}
	
	@Override
	public boolean invite(XPlayerFaction faction) {
		if (!invites.contains(faction)) {
			invites.add(faction);
			return true;
		}
		return false;
	}
	
	public boolean isAdminMode() {
		return adminMode;
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
	
	public boolean permCheck(String s, String perm) {
		if (facCheck()) {
			return faction.hasPermission(this, s) || this.hasPermission(perm);
		}
		sendMessages(Messages.getNoPermission());
		return false;
	}
	
	public boolean permCheck(String s) {
		if (facCheck()) {
			return faction.hasPermission(this, s);
		}
		sendMessages(Messages.getNoPermission());
		return false;
	}
	
	@Override
	public boolean revokeInvite(XPlayerFaction faction) {
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
	
	public void sendFooter() {
		sendMessages(Messages.getFooter());
	}
	
	public void sendHeader() {
		sendMessages(Messages.getHeader());
	}
	
	@Override
	public void sendMessage(String s) {
		super.sendMessage(StringUtility.conv(s));
	}
	
	@Override
	public void sendMessages(List<String> strings) {
		for (String s : strings) {
			sendMessage(s);
		}
	}
	
	@Override
	public void stopTeleporting() {
		teleportWarmupTask = null;
	}
	
	public void toggleAdminMode() {
		adminMode = !adminMode;
		if (adminMode) sendMessages(Messages.getBypassEnabled());
		else sendMessages(Messages.getBypassDisabled());
	}
	
	@Override
	public void toggleCancelled() {
		cancelled = !cancelled;
	}
}
