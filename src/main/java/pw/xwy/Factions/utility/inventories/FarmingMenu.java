package pw.xwy.Factions.utility.inventories;
// made by reeve
// on 3:50 PM

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pw.xwy.Factions.enums.sell.Farming;
import pw.xwy.Factions.objects.Menu;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class FarmingMenu extends Menu {
	private static Inventory inv;
	
	public static Inventory getInv() {
		
		return inv;
	}
	
	@Override
	public void load() {
		inv = Bukkit.createInventory(null, 36, ChatColor.RED + "" + ChatColor.BOLD + "Buy Menu");
	}
	
	@Override
	public void unload() {
		inv = null;
	}
	
	@Override
	public void setup() {
		ItemStack pGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		pGlass.setDurability((short) 7);
		for (int i = 0; i < 36; i++) {
			inv.setItem(i, pGlass);
		}
		
		int j = 0;
		
		for (Farming m : Farming.values()) {
			if (j < 36) {
				inv.setItem(j, m.getItem());
			}
			j++;
		}
	}
}
