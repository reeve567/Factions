package pw.xwy.factions.utility.inventories;
// made by reeve
// on 7:15 PM

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pw.xwy.factions.utility.ItemUtility;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class SellMenu {
	
	public static Inventory getInv(Player player) {
		
		Inventory inv = Bukkit.createInventory(player, 45, ChatColor.RED + "" + ChatColor.BOLD + "Sell Menu");
		
		for (int i = 27; i < 45; i++) {
			inv.setItem(i, ItemUtility.changeName(new ItemStack(Material.IRON_FENCE), "&a ", true));
		}
		
		inv.setItem(36, ItemUtility.changeName(new ItemStack(Material.FIREWORK_CHARGE), "&aItems Inserted", true));
		inv.setItem(39, ItemUtility.changeName(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5), "&aSell", true));
		inv.setItem(40, ItemUtility.changeName(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14), "&cCancel", true));
		inv.setItem(41, ItemUtility.changeName(new ItemStack(Material.GOLD_NUGGET), "&6Value of items", true));
		
		return inv;
	}
	
}
