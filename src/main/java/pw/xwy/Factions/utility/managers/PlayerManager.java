package pw.xwy.Factions.utility.managers;

import org.bukkit.entity.Player;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.objects.XPlayer;
import pw.xwy.Factions.objects.XPlayerConfig;

import java.util.ArrayList;
import java.util.UUID;

public class PlayerManager {
	
	private static ArrayList<XPlayer> players = new ArrayList<>();
	
	public static XFaction getPlayerFaction(Player p) {
		return getXPlayer(p).getFaction();
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
	
	public static ArrayList<XPlayer> getPlayers() {
		return players;
	}
	
	public static XPlayer getOfflinePlayer(UUID p) {
		return new XPlayer(p, new XPlayerConfig(p));
	}
	
	public static String getOfflinePlayerName(UUID p) {
		return getOfflinePlayer(p).getName();
	}
	
	public static void addXPlayer(XPlayer xPlayer) {
		players.add(xPlayer);
	}
	
	public static void setFaction(XPlayer xPlayer, XFaction xFaction) {
		for (XPlayer p : players) {
			if (p.equals(xPlayer)) {
				p.setFaction(xFaction);
			}
		}
	}
	
	public static void removeXPlayer(XPlayer xPlayer) {
		players.remove(xPlayer);
	}
	
	public static boolean isInFaction(Player p) {
		if (getXPlayer(p).getFaction() != null) return true;
		return false;
	}
}
