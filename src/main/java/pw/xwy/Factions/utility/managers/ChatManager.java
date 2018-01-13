package pw.xwy.Factions.utility.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pw.xwy.Factions.enums.ChatType;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.objects.XPlayer;
import pw.xwy.Factions.objects.XRank;
import pw.xwy.Factions.utility.StringUtil;
import pw.xwy.Factions.utility.StringUtility;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;

public class ChatManager implements Listener {
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player player = e.getPlayer();
		XPlayer xPlayer = PlayerManager.getXPlayer(player);
		ChatType chatType = xPlayer.getChatType();
		XFaction xFaction = xPlayer.getFaction();
		XRank roleType = null;
		if (xFaction != null) {
			roleType = xFaction.getRole(xPlayer.getPlayer().getUniqueId());
		}
		
		boolean staffchat;
		
		String prefix = "";
		switch (chatType) {
			case PUBLIC:
				if (roleType != null) {
					e.setFormat(e.getFormat().replace("<faction>", StringUtil.chatColorConv("&8[&7" + xFaction.getName() + "&8]&r ")));
				} else {
					e.setFormat(e.getFormat().replace("<faction>", ""));
				}
				
				
				break;
			case FACTION:
				e.setCancelled(true);
				Set<Player> recipients = Collections.emptySet();
				for (UUID p : xFaction.players) {
					recipients.add(Bukkit.getPlayer(p));
				}
				recipients.add(Bukkit.getPlayer(xFaction.getLeader()));
				for (Player p : recipients) {
					p.sendMessage(StringUtility.conv("&a[" + prefix + player.getName() + "] "));
				}
				
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
