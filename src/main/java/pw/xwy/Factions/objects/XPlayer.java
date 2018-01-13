package pw.xwy.Factions.objects;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pw.xwy.Factions.enums.ChatType;
import pw.xwy.Factions.utility.Config;
import pw.xwy.Factions.utility.managers.FactionManager;

import java.util.ArrayList;
import java.util.UUID;

public class XPlayer {
	
	private Player player;
	public XPlayerConfig playerConfig;
	public int spawnCooldown = 0;
	public boolean canceled = false;
	private double power = 0.0;
	private XFaction faction = null;
	private ChatType chatType = ChatType.PUBLIC;
	private ArrayList<XFaction> invites = new ArrayList<>();
	
	public XPlayer(Player player) {
		this.player = player;
		playerConfig = new XPlayerConfig(this);
	}
	
	public double getPower() {
		return power;
	}
	
	//load from string
	public XPlayer(UUID id, XPlayerConfig s) {
		try {
			playerConfig = s;
			player = Bukkit.getPlayer(id);
			power = s.getPower();
			faction = FactionManager.getFactionByName(s.getFactionName());
		} catch (NullPointerException ignored) {
			System.out.println("Player not found in config, creating file now...");
			player = Bukkit.getPlayer(id);
			power = 0;
			faction = null;
		}
	}
	
	@Override
	public String toString() {
		if (faction != null) {
			return power + ";" + faction.getName();
		}
		else {
			return power + ";no-faction";
		}
	}
	
	public void addPower() {
		int po = (int) (power*10);
		po += 1;
		power = po/10.0;
		if (po >= Config.maxPower) {
			power = Config.maxPower;
		}
		playerConfig.save(this);
		
	}
	
	public boolean invite(XFaction faction) {
		if (!invites.contains(faction)) {
			invites.add(faction);
			return true;
		}
		return false;
	}
	
	public boolean revokeInvite(XFaction faction) {
		if (invites.contains(faction)) {
			invites.remove(faction);
			return true;
		}
		return false;
	}
	
	public boolean hasInvite(XFaction faction) {
		return invites.contains(faction);
	}
	
	public void setChatType(ChatType chatType) {
		this.chatType = chatType;
	}
	
	public ChatType getChatType() {
		return chatType;
	}
	
	public void nextChatType() {
		int num = chatType.ordinal();
		if (++num > ChatType.TRUCE.ordinal()) {
			num = 0;
		}
		chatType = ChatType.values()[num];
	}
	
	public UUID getID() {
		return player.getUniqueId();
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public XFaction getFaction() {
		return faction;
	}
	
	public void setFaction(XFaction xFaction) {
		faction = xFaction;
		if (xFaction != null) {
			playerConfig.set("info.faction",xFaction.getName());
		}
		else {
			playerConfig.set("info.faction","no-faction");
		}
		
		playerConfig.save();
	}
}
