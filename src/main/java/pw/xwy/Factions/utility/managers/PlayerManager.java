package pw.xwy.Factions.utility.managers;

import org.bukkit.entity.Player;
import pw.xwy.Factions.objects.XFaction;
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
	
	private static ArrayList<XPlayer> players = new ArrayList<>();
	
	public static void addXPlayer(XPlayer xPlayer) {
		players.add(xPlayer);
	}
	
	public static XPlayer getOfflinePlayer(UUID p) {
		return new XPlayer(p, new XPlayerConfig(p));
	}
	
	public static String getOfflinePlayerName(UUID p) {
		return getOfflinePlayer(p).getName();
	}
	
	public static XFaction getPlayerFaction(Player p) {
		return getXPlayer(p).getFaction();
	}
	
	public static ArrayList<XPlayer> getPlayers() {
		return players;
	}
	
	public static XPlayer getXPlayer(Player p) {
		for (XPlayer pl : players) {
			if (pl.getPlayer().equals(p)) {
				return pl;
			}
		}
		XPlayer xp = new XPlayer(p);
		players.add(xp);
		return xp;
	}
	
	public static boolean isInFaction(Player p) {
		if (getXPlayer(p).getFaction() != null) return true;
		return false;
	}
	
	public static void removeXPlayer(XPlayer xPlayer) {
		players.remove(xPlayer);
	}
	
	public static void setFaction(XPlayer xPlayer, XFaction xFaction) {
		for (XPlayer p : players) {
			if (p.equals(xPlayer)) {
				p.setFaction(xFaction);
			}
		}
	}
}
