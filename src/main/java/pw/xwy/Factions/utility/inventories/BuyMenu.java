package pw.xwy.factions.utility.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pw.xwy.factions.objects.Menu;
import pw.xwy.factions.utility.ItemUtility;
import pw.xwy.factions.utility.StringUtility;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class BuyMenu extends Menu {
	
	public static void open(Player player, ItemStack it, ItemMeta im, double price) {
		Inventory inv = Bukkit.createInventory(player, 45, StringUtility.conv("&a&lBuy Menu"));
		inv.setItem(9, ItemUtility.changeName(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14), StringUtility.conv("&cRemove 1 item"), false));
		inv.setItem(10, ItemUtility.changeName(new ItemStack(Material.STAINED_GLASS_PANE, 10, (short) 14), StringUtility.conv("&cRemove 10 items"), false));
		inv.setItem(11, ItemUtility.changeName(new ItemStack(Material.STAINED_GLASS_PANE, 64, (short) 14), StringUtility.conv("&cRemove 64 items"), false));
		inv.setItem(15, ItemUtility.changeName(new ItemStack(Material.STAINED_GLASS_PANE, 64, (short) 5), StringUtility.conv("&aAdd 64 items"), false));
		inv.setItem(16, ItemUtility.changeName(new ItemStack(Material.STAINED_GLASS_PANE, 10, (short) 5), StringUtility.conv("&aAdd 10 items"), false));
		inv.setItem(17, ItemUtility.changeName(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5), StringUtility.conv("&aAdd 1 item"), false));
		it.setItemMeta(im);
		inv.setItem(13, it);
		
		for (int i = 27; i < 45; i++) {
			inv.setItem(i, ItemUtility.changeName(new ItemStack(Material.IRON_FENCE), "&f", true));
		}
		
		inv.setItem(40, ItemUtility.changeName(new ItemStack(Material.FIREWORK_CHARGE), StringUtility.conv("&a$&2" + String.valueOf(price)), false));
		
		inv.setItem(39, ItemUtility.changeName(new ItemStack(Material.STAINED_CLAY, 1, (short) 14), "&cCancel", true));
		inv.setItem(41, ItemUtility.changeName(new ItemStack(Material.STAINED_CLAY, 1, (short) 5), "&aBuy", true));
		player.openInventory(inv);
	}
	
	@Override
	public void load() {
	
	}
	
	@Override
	public void unload() {
	
	}
	
	@Override
	public void setup() {
	
	}
}
