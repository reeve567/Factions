package pw.xwy.Factions.objects;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {
	
	private ItemStack itemStack;
	
	public ItemBuilder(Material type) {
		itemStack = new ItemStack(type);
	}
	
	public ItemBuilder setName(String s) {
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', s));
		itemStack.setItemMeta(meta);
		return this;
	}
	
	public ItemBuilder addLore(String s) {
		List<String> lores = new ArrayList<>();
		if (itemStack.hasItemMeta()) {
			ItemMeta im = itemStack.getItemMeta();
			if (itemStack.getItemMeta().hasLore()) {
				lores = itemStack.getItemMeta().getLore();
			}
			lores.add(ChatColor.translateAlternateColorCodes('&', s));
			im.setLore(lores);
			itemStack.setItemMeta(im);
		}
		return this;
	}
	
	public ItemBuilder addGlow() {
		ItemMeta meta = itemStack.getItemMeta();
		meta.addEnchant(new Glow(999),1,true);
		itemStack.setItemMeta(meta);
		return this;
	}
	
	public ItemBuilder setLore(String s) {
		List<String> lores = new ArrayList<>();
		lores.add(s);
		ItemMeta im = itemStack.getItemMeta();
		im.setLore(lores);
		itemStack.setItemMeta(im);
		return this;
	}
	
	public ItemBuilder setLore(List<String> lore) {
		ItemMeta im = itemStack.getItemMeta();
		im.setLore(lore);
		itemStack.setItemMeta(im);
		return this;
	}
	
	public ItemBuilder setAmount(int amount) {
		itemStack.setAmount(amount);
		return this;
	}
	
	public ItemBuilder setDurability(int data) {
		itemStack.setDurability((short) data);
		return this;
	}
	
	public ItemStack get() {
		return itemStack;
	}
}
