package pw.xwy.Factions.utility.inventories;
// made by reeve
// on 2:49 PM

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pw.xwy.Factions.enums.sell.Brewing;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class BrewingMenu {
	
	private static Inventory inv = Bukkit.createInventory(null, 18, ChatColor.RED + "" + ChatColor.BOLD + "Buy Menu");
	
	static {
		ItemStack pGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		pGlass.setDurability((short) 7);
		for (int i = 0; i < 18; i++) {
			inv.setItem(i, pGlass);
		}
		
		int j = 0;
		
		for (Brewing b : Brewing.values()) {
			if (j < 18) {
				inv.setItem(j, b.getItem());
			}
			j++;
		}
	}
	
	public static Inventory getInv() {
		
		return inv;
	}
}
