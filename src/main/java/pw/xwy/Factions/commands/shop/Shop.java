package pw.xwy.factions.commands.shop;
// made by reeve
// on 2:50 PM

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pw.xwy.factions.objects.CommandHandler;
import pw.xwy.factions.utility.StringUtility;
import pw.xwy.factions.utility.inventories.MainMenu;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

@CommandHandler
public class Shop {
	
	public static void run(CommandSender sender) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("factionsshop.shop")) {
				player.openInventory(MainMenu.getInv());
			} else {
				player.sendMessage(StringUtility.conv("&cYou do not have permission to do this!"));
			}
			
		}
	}
	
}
