package pw.xwy.factions.utility.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pw.xwy.factions.enums.ChatType;
import pw.xwy.factions.objects.faction.XPlayerFaction;
import pw.xwy.factions.objects.faction.XFactionOnlinePlayer;
import pw.xwy.factions.objects.faction.XOfflinePlayer;
import pw.xwy.factions.objects.faction.XPlayer;
import pw.xwy.factions.utility.StringUtility;

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

public class ChatManager implements Listener {
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player player = e.getPlayer();
		XFactionOnlinePlayer xPlayer = PlayerManager.getPlayer(player);
		ChatType chatType = xPlayer.getChatType();
		XPlayerFaction xPlayerFaction = xPlayer.getFaction();
		
		boolean staffchat;
		
		switch (chatType) {
			case PUBLIC:
				if (xPlayerFaction != null) {
					e.setFormat(e.getFormat().replace("<faction>", StringUtility.conv("&8[&7" + xPlayerFaction.getName() + "&8]&r ")));
				} else {
					e.setFormat(e.getFormat().replace("<faction>", ""));
				}
				break;
			case FACTION:
				assert xPlayerFaction != null;
				e.setCancelled(true);
				ArrayList<UUID> recipients = new ArrayList<>(xPlayerFaction.players);
				String pre = xPlayerFaction.isLeader(player) ? xPlayerFaction.leaderRank.prefix : xPlayerFaction.getRole(player.getUniqueId()).prefix;
				System.out.println(pre);
				ArrayList<XPlayer> rec = new ArrayList<>();
				for (UUID p : recipients) {
					if (!(PlayerManager.getOfflinePlayer(p) instanceof XOfflinePlayer)) {
						rec.add((XPlayer) PlayerManager.getOfflinePlayer(p));
						Bukkit.getPlayer(p).sendMessage(StringUtility.conv("&a[Faction] " + pre + " &a" + e.getPlayer().getName() + ": " + e.getMessage()));
					}
				}
				
				PlayerManager.sendToSpies(StringUtility.conv("&a[Faction] " + pre + " &a" + e.getPlayer().getName() + ": " + e.getMessage()), rec);
				
				break;
			case ALLY:
				e.setCancelled(true);
			case TRUCE:
				e.setCancelled(true);
			case OFFICER:
				e.setCancelled(true);
		}
	}
	
}
