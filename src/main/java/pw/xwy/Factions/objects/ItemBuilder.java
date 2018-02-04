package pw.xwy.Factions.objects;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pw.xwy.Factions.utility.StringUtility;

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

public class ItemBuilder {
	
	private ItemStack itemStack;
	private ItemMeta meta;
	
	public ItemBuilder(Material type) {
		itemStack = new ItemStack(type);
		meta = itemStack.getItemMeta();
	}
	
	public ItemBuilder(ItemStack stack) {
		itemStack = stack;
		meta = itemStack.getItemMeta();
	}
	
	private void setMeta() {
		itemStack.setItemMeta(meta);
	}
	
	public ItemBuilder addGlow() {
		meta.addEnchant(new Glow(999), 1, true);
		return this;
	}
	
	public ItemBuilder removeGlow() {
		meta.removeEnchant(new Glow(999));
		return this;
	}
	
	public ItemBuilder addLore(String s) {
		List<String> lores = new ArrayList<>();
		if (itemStack.hasItemMeta()) {
			if (itemStack.getItemMeta().hasLore()) {
				lores = itemStack.getItemMeta().getLore();
			}
			lores.add(ChatColor.translateAlternateColorCodes('&', s));
			meta.setLore(lores);
		}
		return this;
	}
	
	public ItemStack get() {
		setMeta();
		return itemStack;
	}
	
	public ItemBuilder setAmount(int amount) {
		itemStack.setAmount(amount);
		return this;
	}
	
	public ItemBuilder setDurability(int data) {
		itemStack.setDurability((short) data);
		return this;
	}
	
	public ItemBuilder setLore(List<String> lore) {
		meta.setLore(lore);
		return this;
	}
	
	public ItemBuilder setLore(String s) {
		List<String> lores = new ArrayList<>();
		lores.add(StringUtility.conv(s));
		meta.setLore(lores);
		return this;
	}
	
	public ItemBuilder setName(String s) {
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', s));
		return this;
	}
}
