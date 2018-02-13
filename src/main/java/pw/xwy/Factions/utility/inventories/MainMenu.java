package pw.xwy.Factions.utility.inventories;
// made by reeve
// on 2:51 PM

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
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

public class MainMenu extends Menu {
	
	private static Inventory inv;
	
	public static Inventory getInv() {
		
		return inv;
	}
	
	@Override
	public void load() {
		inv = Bukkit.createInventory(null, 45, ChatColor.GOLD + "Shops");
	}
	
	@Override
	public void unload() {
		inv = null;
	}
	
	@Override
	public void setup() {
		ItemStack pGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		pGlass.setDurability((short) 7);
		for (int i = 0; i < 45; i++) {
			inv.setItem(i, pGlass);
		}
		
		inv.setItem(10, ItemUtility.changeName(new ItemStack(Material.TNT), ChatColor.RED + "" + ChatColor.BOLD + "Raiding Shop", false));
		inv.setItem(12, ItemUtility.changeName(new ItemStack(Material.POTION, 1, (short) 8265), ChatColor.GREEN + "" + ChatColor.BOLD + "Brewing Shop", false));
		inv.setItem(14, ItemUtility.changeName(new ItemStack(Material.MOB_SPAWNER), ChatColor.translateAlternateColorCodes('&', "&3&lSpawners Shop"), false));
		inv.setItem(16, ItemUtility.changeName(new ItemStack(Material.DIAMOND_SWORD), ChatColor.AQUA + "" + ChatColor.BOLD + "Combat Shop", false));
		inv.setItem(22, ItemUtility.changeName(new ItemStack(Material.SIGN), ChatColor.GOLD + "" + ChatColor.BOLD + "Sell Shop", false));
		inv.setItem(28, ItemUtility.changeName(new ItemStack(Material.DIAMOND_HOE), ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Farming Shop", false));
		inv.setItem(30, ItemUtility.changeName(new ItemStack(Material.DIAMOND_PICKAXE), ChatColor.translateAlternateColorCodes('&', "&9&lTools Shop"), false));
		inv.setItem(32, ItemUtility.changeName(new ItemStack(Material.BRICK), ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Building Shop", false));
		inv.setItem(34, ItemUtility.changeName(new ItemStack(Material.EYE_OF_ENDER), ChatColor.WHITE + "" + ChatColor.BOLD + "Misc Shop", false));
	}
}
