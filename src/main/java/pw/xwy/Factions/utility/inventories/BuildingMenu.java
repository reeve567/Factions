package pw.xwy.Factions.utility.inventories;
// made by reeve
// on 3:54 PM

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pw.xwy.Factions.enums.sell.Building;
import pw.xwy.Factions.objects.Menu;
import pw.xwy.Factions.utility.ItemUtility;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class BuildingMenu extends Menu {
	
	private static Inventory inv;
	private static Inventory inv2;
	private static Inventory inv3;
	
	public static Inventory getInv() {
		
		return inv;
	}
	
	public static Inventory getInv2() {
		
		return inv2;
	}
	
	public static Inventory getInv3() {
		
		return inv3;
	}
	
	@Override
	public void load() {
		inv = Bukkit.createInventory(null, 54, ChatColor.RED + "" + ChatColor.BOLD + "Buy Menu");
		inv2 = Bukkit.createInventory(null, 54, ChatColor.RED + "" + ChatColor.BOLD + "Buy Menu");
		inv3 = Bukkit.createInventory(null, 54, ChatColor.RED + "" + ChatColor.BOLD + "Buy Menu");
	}
	
	@Override
	public void unload() {
	
	}
	
	@Override
	public void setup() {
		ItemStack pGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		pGlass.setDurability((short) 7);
		for (int i = 0; i < 54; i++) {
			inv.setItem(i, pGlass);
		}
		
		for (int i = 0; i < 54; i++) {
			inv2.setItem(i, pGlass);
		}
		
		for (int i = 0; i < 54; i++) {
			inv3.setItem(i, pGlass);
		}
		
		int c = 0;
		
		for (Building b : Building.values()) {
			if (c < 52) {
				inv.setItem(c, b.getItem());
			} else if (c < 104) {
				inv2.setItem(c - 52, b.getItem());
			} else if (c < 156) {
				inv3.setItem(c - 104, b.getItem());
			}
			c++;
		}
		
		inv.setItem(53, ItemUtility.next());
		inv.setItem(52, ItemUtility.previous());
		
		inv2.setItem(53, ItemUtility.next());
		inv2.setItem(52, ItemUtility.previous());
		
		inv3.setItem(53, ItemUtility.previous());
	}
}
