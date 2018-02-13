package pw.xwy.Factions.utility.handlers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class UnknownCommandHandler implements Listener {
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
		if (!event.isCancelled()) {
			Player player = event.getPlayer();
			boolean canUseAdmin = player.hasPermission("f.commandbypass");
			
			String cmd = event.getMessage().split(" ")[0];
			HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(cmd);
			if ((cmd.contains(":") || cmd.equalsIgnoreCase("/pl")) && !canUseAdmin) {
				topic = null;
			}
			
			if (topic == null) {
				player.sendMessage(ChatColor.RED + "This command does not exist!");
				event.setCancelled(true);
			}
		}
	}
	
}
