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

@Deprecated
public class XPlayer {
	
	public XPlayerConfig playerConfig;
	public int spawnCooldown = 0;
	public int homeCooldown = 0;
	public boolean canceled = false;
	public boolean stopNextFallDamage = false;
	private String name;
	private Player player;
	private double power = 0.0;
	private XFaction faction = null;
	private ChatType chatType = ChatType.PUBLIC;
	private ArrayList<XFaction> invites = new ArrayList<>();
	
	public XPlayer(Player player) {
		this.player = player;
		playerConfig = new XPlayerConfig(this);
	}
	
	public XPlayer(UUID id, XPlayerConfig s) {
		try {
			playerConfig = s;
			player = Bukkit.getPlayer(id);
			this.name = playerConfig.getName();
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
	
	public void addPower() {
		int po = (int) (power * 10);
		po += 1;
		power = po / 10.0;
		if (po >= Config.maxPower) {
			power = Config.maxPower;
		}
		playerConfig.save(this);
		
	}
	
	public ChatType getChatType() {
		return chatType;
	}
	
	public void setChatType(ChatType chatType) {
		this.chatType = chatType;
	}
	
	public XFaction getFaction() {
		return faction;
	}
	
	public void setFaction(XFaction xFaction) {
		faction = xFaction;
		if (xFaction != null) {
			playerConfig.set("info.faction", xFaction.id.toString());
		} else {
			playerConfig.set("info.faction", "no-faction");
		}
		
		playerConfig.save();
	}
	
	public UUID getID() {
		return player.getUniqueId();
	}
	
	public String getName() {
		return name;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public double getPower() {
		return power;
	}
	
	public boolean hasInvite(XFaction faction) {
		return invites.contains(faction);
	}
	
	public boolean invite(XFaction faction) {
		if (!invites.contains(faction)) {
			invites.add(faction);
			return true;
		}
		return false;
	}
	
	public void nextChatType() {
		int num = chatType.ordinal();
		if (++num > ChatType.TRUCE.ordinal()) {
			num = 0;
		}
		chatType = ChatType.values()[num];
	}
	
	public boolean revokeInvite(XFaction faction) {
		if (invites.contains(faction)) {
			invites.remove(faction);
			return true;
		}
		return false;
	}
}
