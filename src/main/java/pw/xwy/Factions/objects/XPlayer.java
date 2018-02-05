package pw.xwy.Factions.objects;

import net.minecraft.server.v1_8_R3.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import pw.xwy.Factions.enums.ChatType;
import pw.xwy.Factions.utility.Configurations.Config;
import pw.xwy.Factions.utility.Configurations.Messages;
import pw.xwy.Factions.utility.StringUtility;
import pw.xwy.Factions.utility.managers.FactionManager;
import pw.xwy.Factions.utility.managers.PlayerManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
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
	private XPlayerConfig config;
	private double power;
	private XFaction faction;
	private ChatType chatType = ChatType.PUBLIC;
	private TeleportWarmupTask teleportWarmupTask = null;
	private ArrayList<XFaction> invites = new ArrayList<>();
	
	public XPlayer(Player player) {
		super((CraftServer) Bukkit.getServer(), ((CraftPlayer) player).getHandle());
		config = new XPlayerConfig(this);
		try {
			faction = FactionManager.getFactionFromUUID(UUID.fromString(config.getFactionUUID()));
		} catch (IllegalArgumentException e) {
			faction = null;
		}
		power = config.getPower();
		
	}
	
	public static XPlayer getXPlayer(Player p) {
		return (XPlayer) PlayerManager.getPlayer(p);
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
	public void sendMessages(List<String> strings) {
		for (String s: strings) {
			sendMessage(s);
		}
	}
	
	@Override
	public void sendMessage(String s) {
		super.sendMessage(StringUtility.conv(s));
	}
	
	public void sendHeader() {
		sendMessages(Messages.getHeader());
	}
	
	public void sendFooter() {
		sendMessages(Messages.getFooter());
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
