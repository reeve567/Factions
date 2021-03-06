package pw.xwy.factions.utility;
// made by reeve
// on 2:55 PM

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class ItemUtility {
	
	private static ItemStack next = new ItemStack(Material.ARROW);
	private static ItemStack previous = new ItemStack(Material.BARRIER);
	
	static {
		ItemUtility.changeName(next, "&aNext Page", true);
		ItemUtility.changeName(previous, "&cPrevious Page", true);
	}
	
	public static ItemStack addLore(ItemStack itemStack, String lore) {
		List<String> lores = new ArrayList<>();
		if (itemStack.hasItemMeta()) {
			ItemMeta im = itemStack.getItemMeta();
			if (itemStack.getItemMeta().hasLore()) {
				lores = itemStack.getItemMeta().getLore();
			}
			lores.add(lore);
			im.setLore(lores);
			itemStack.setItemMeta(im);
		}
		return itemStack;
	}
	
	public static ItemStack changeName(ItemStack is, String name, boolean trans) {
		ItemMeta meta = is.getItemMeta();
		if (trans) meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		else meta.setDisplayName(name);
		is.setItemMeta(meta);
		return is;
	}
	
	public static ItemStack next() {
		return next;
	}
	
	public static ItemStack previous() {
		return previous;
	}
	
	public static void setLore(ItemStack itemStack, String lore) {
		List<String> lores = new ArrayList<>();
		lores.add(lore);
		ItemMeta im = itemStack.getItemMeta();
		im.setLore(lores);
		itemStack.setItemMeta(im);
	}
}
