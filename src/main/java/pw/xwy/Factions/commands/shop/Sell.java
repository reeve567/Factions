package pw.xwy.Factions.commands.shop;
// made by reeve
// on 2:50 PM

import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pw.xwy.Factions.utility.StringUtility;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class Sell {
	
	public static void run(CommandSender sender, Economy economy) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			boolean found = false;
			int money = 0;
			if (player.hasPermission("FactionsShop.sell")) {
				if (player.getItemInHand() != null) {
					for (pw.xwy.Factions.enums.sell.Sell s : pw.xwy.Factions.enums.sell.Sell.values()) {
						if (!found && s.getMaterial().equals(player.getItemInHand().getType())) {
							
							found = true;
							
							
							for (ItemStack i : player.getInventory()) {
								if (i != null && i.getType().equals(s.getMaterial())) {
									money += i.getAmount() * s.getPrice();
								}
							}
						}
					}
					if (!found) player.sendMessage(ChatColor.RED + "This item cannot be sold.");
					else {
						player.sendMessage(ChatColor.GREEN + "Sold for " + money);
						player.getInventory().remove(player.getItemInHand().getType());
						economy.depositPlayer(player, money);
					}
				}
			} else {
				player.sendMessage(StringUtility.conv("&cYou do not have permission to do this!"));
			}
			
		}
	}
	
}
