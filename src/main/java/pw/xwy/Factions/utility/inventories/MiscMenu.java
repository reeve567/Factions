package pw.xwy.factions.utility.inventories;
// made by reeve
// on 1:44 AM

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pw.xwy.factions.enums.sell.Miscellaneous;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class MiscMenu {
	
	private static Inventory inv = Bukkit.createInventory(null, 54, ChatColor.RED + "" + ChatColor.BOLD + "Buy Menu");
	
	static {
		ItemStack pGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		pGlass.setDurability((short) 7);
		for (int i = 0; i < 54; i++) {
			inv.setItem(i, pGlass);
		}
		
		int j = 0;
		
		for (Miscellaneous m : Miscellaneous.values()) {
			if (j < 54) {
				inv.setItem(j, m.getItem());
			}
			j++;
		}
	}
	
	public static Inventory getInv() {
		
		return inv;
	}
}
