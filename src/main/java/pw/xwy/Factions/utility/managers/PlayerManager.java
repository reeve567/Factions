package pw.xwy.Factions.utility.managers;

import org.bukkit.entity.Player;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.objects.XFactionPlayer;
import pw.xwy.Factions.objects.XPlayer;
import pw.xwy.Factions.objects.XPlayerConfig;

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

public class PlayerManager {
	
	private static ArrayList<XFactionPlayer> onlinePlayers = new ArrayList<>();
	
	public static void addXPlayer(XFactionPlayer player) {
		onlinePlayers.add(player);
	}
	
	public static XFactionPlayer getOfflinePlayer(UUID p) {
		return new XPlayer(p, new XPlayerConfig(p));
	}
	
	public static String getOfflinePlayerName(UUID p) {
		return getOfflinePlayer(p).getName();
	}
	
	public static ArrayList<XFactionPlayer> getOnlinePlayers() {
		return onlinePlayers;
	}
	
	public static XFactionPlayer getPlayer(Player p) {
		for (XFactionPlayer pl : onlinePlayers) {
			if (pl.getPlayer().equals(p)) {
				return pl;
			}
		}
		XFactionPlayer xp = new XPlayer(p);
		onlinePlayers.add(xp);
		return xp;
	}
	
	public static XFaction getPlayerFaction(Player p) {
		return getPlayer(p).getFaction();
	}
	
	public static boolean isInFaction(Player p) {
		if (getPlayer(p).getFaction() != null) return true;
		return false;
	}
	
	public static void removePlayer(XFactionPlayer player) {
		onlinePlayers.remove(player);
	}
	
}
