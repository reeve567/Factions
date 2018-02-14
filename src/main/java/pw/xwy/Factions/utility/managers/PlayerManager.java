package pw.xwy.Factions.utility.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pw.xwy.Factions.XFactionsCore;
import pw.xwy.Factions.objects.faction.*;
import pw.xwy.Factions.utility.StringUtility;

import java.io.File;
import java.util.*;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class PlayerManager implements Manager {
	
	public static ArrayList<XPlayer> chatspies;
	private static ArrayList<XFactionOnlinePlayer> onlinePlayers;
	private static HashMap<String, UUID> nameAndIDs;
	private static File userdata;
	
	public static void addOnlinePlayer(XFactionOnlinePlayer player) {
		onlinePlayers.add(player);
	}
	
	public static XPlayerFaction getOfflinePlayerFaction(String s) {
		return getOfflinePlayerFaction(nameAndIDs.get(s));
	}
	
	public static XPlayerFaction getOfflinePlayerFaction(UUID uuid) {
		return getOfflinePlayer(uuid).getFaction();
	}
	
	public static XFactionPlayer getOfflinePlayer(UUID p) {
		if (isOnline(p)) {
			return getPlayer(Bukkit.getPlayer(p));
		}
		return new XOfflinePlayer(p);
	}
	
	public static boolean isOnline(UUID id) {
		for (XFactionOnlinePlayer player : onlinePlayers) {
			if (player.getUniqueId().equals(id)) {
				return true;
			}
		}
		return false;
		
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
	
	public static UUID getOfflinePlayerUUID(String name) {
		return nameAndIDs.get(name);
	}
	
	public static String getOfflinePlayerName(UUID p) {
		return getOfflinePlayer(p).getName();
	}
	
	public static XPlayerFaction getOnlinePlayerFaction(Player p) {
		return getPlayer(p).getFaction();
	}
	
	public static ArrayList<XFactionOnlinePlayer> getOnlinePlayers() {
		return onlinePlayers;
	}
	
	public static boolean isInFaction(Player p) {
		return getPlayer(p).getFaction() != null;
	}
	
	public static void removePlayer(XFactionOnlinePlayer player) {
		onlinePlayers.remove(player);
	}
	
	public static void sendMessages(List<String> messages) {
		for (String s : messages) {
			sendMessages(s);
		}
	}
	
	public static void sendMessages(String message) {
		Bukkit.broadcastMessage(StringUtility.conv(message));
	}
	
	public static void loadOfflinePlayers() {
		if (userdata != null) {
			File[] files = userdata.listFiles();
			if (files != null) {
				int index = 0;
				int total = files.length;
				for (File f : files) {
					index++;
					System.out.println("Loading user " + f.getName().substring(0, f.getName().indexOf(".")) + " (" + index + "/" + total + ")");
					XPlayerConfig config = new XPlayerConfig(UUID.fromString(f.getName().substring(0, f.getName().indexOf('.'))));
					nameAndIDs.put(config.getName(), UUID.fromString(f.getName().substring(0, f.getName().indexOf('.'))));
				}
			}
		}
		
		
	}
	
	public static void sendToSpies(String conv, XFactionPlayer... rec) {
		sendToSpies(conv, (XPlayer[]) rec);
	}
	
	public static void sendToSpies(String s, XPlayer... recipients) {
		sendToSpies(s, Arrays.asList(recipients));
	}
	
	public static void sendToSpies(String s, List<XPlayer> recipients) {
		ArrayList<XPlayer> dontSend = new ArrayList<>();
		
		for (XPlayer player : recipients) {
			if (chatspies.contains(player)) {
				dontSend.add(player);
			}
		}
		
		for (XPlayer player : chatspies) {
			if (!dontSend.contains(player)) {
				player.sendMessage(s);
			}
		}
	}
	
	public void unload() {
		chatspies = null;
		onlinePlayers = null;
		nameAndIDs = null;
		userdata = null;
	}
	
	@Override
	public void load() {
		userdata = new File(XFactionsCore.getXFactionsCore().getDataFolder(), File.separator + "PlayerData");
		chatspies = new ArrayList<>();
		onlinePlayers = new ArrayList<>();
		nameAndIDs = new HashMap<>();
	}
}
