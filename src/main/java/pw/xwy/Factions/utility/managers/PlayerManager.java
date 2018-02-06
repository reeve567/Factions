package pw.xwy.Factions.utility.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pw.xwy.Factions.objects.*;
import pw.xwy.Factions.utility.StringUtility;

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

public class PlayerManager {
	
	private static ArrayList<XFactionOnlinePlayer> onlinePlayers = new ArrayList<>();
	
	public static void addOnlinePlayer(XFactionOnlinePlayer player) {
		onlinePlayers.add(player);
	}
	
	public static XFactionPlayer getOfflinePlayer(UUID p) {
		if (isOnline(p)) {
			return getPlayer(Bukkit.getPlayer(p));
		}
		return new XOfflinePlayer(p);
	}
	
	public static XFaction getOfflinePlayerFaction(UUID uuid) {
		return getOfflinePlayer(uuid).getFaction();
	}
	
	public static String getOfflinePlayerName(UUID p) {
		return getOfflinePlayer(p).getName();
	}
	
	public static XFaction getOnlinePlayerFaction(Player p) {
		return getPlayer(p).getFaction();
	}
	
	public static ArrayList<XFactionOnlinePlayer> getOnlinePlayers() {
		return onlinePlayers;
	}
	
	public static XFactionOnlinePlayer getPlayer(Player p) {
		for (XFactionOnlinePlayer pl : onlinePlayers) {
			if (pl.equals(p)) {
				return pl;
			}
		}
		XFactionOnlinePlayer xp = new XPlayer(p);
		onlinePlayers.add(xp);
		return xp;
	}
	
	public static boolean isOnline(UUID id) {
		for (XFactionOnlinePlayer player : onlinePlayers) {
			if (((XPlayer) player).getUniqueId().equals(id)) {
				return true;
			}
		}
		return false;
		
	}
	
	public static boolean isInFaction(Player p) {
		return getPlayer(p).getFaction() != null;
	}
	
	public static void removePlayer(XFactionOnlinePlayer player) {
		onlinePlayers.remove(player);
	}
	
	public static void sendMessages(String message) {
		Bukkit.broadcastMessage(StringUtility.conv(message));
	}
	
	public static void sendMessages(List<String> messages) {
		for (String s : messages) {
			sendMessages(s);
		}
	}
	
}
