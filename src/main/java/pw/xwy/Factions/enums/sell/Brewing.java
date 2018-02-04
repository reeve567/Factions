package pw.xwy.Factions.enums.sell;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pw.xwy.Factions.utility.ItemNames;

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

public enum Brewing {
	
	WATERBOTTLE(10, Material.POTION),
	GHASTTEAR(1000, Material.GHAST_TEAR),
	GLASSBOTTLE(5, Material.GLASS_BOTTLE),
	FERMENTEDSPIDEREYE(35, Material.FERMENTED_SPIDER_EYE),
	BLAZEPOWDER(80, Material.BLAZE_POWDER),
	MAGMACREAM(50, Material.MAGMA_CREAM),
	BREWINGSTAND(500, Material.BREWING_STAND_ITEM),
	CAULDRON(500, Material.CAULDRON_ITEM),
	GLISTERINGMELON(25, Material.SPECKLED_MELON),
	GOLDENCARROT(25, Material.GOLDEN_CARROT),
	NETHERWART(20, Material.NETHER_STALK),
	REDSTONE(15, Material.REDSTONE),
	GUNPOWDER(25, Material.SULPHUR),
	GLOWSTONEDUST(20, Material.GLOWSTONE_DUST),
	SUGAR(20, Material.SUGAR),
	PUFFERFISH(35, Material.RAW_FISH, 3);
	private final double price;
	private final Material material;
	private final ItemStack item;
	private final int durability;
	
	Brewing(int pr, Material mat) {
		
		this.price = pr;
		this.material = mat;
		
		ItemStack it = new ItemStack(mat);
		ItemMeta im = it.getItemMeta();
		im.setDisplayName(ChatColor.GREEN + ItemNames.get(String.valueOf(it.getTypeId())));
		List<String> lores = new ArrayList<>();
		lores.add(ChatColor.translateAlternateColorCodes('&', "&a$&2" + pr));
		im.setLore(lores);
		it.setItemMeta(im);
		
		
		this.item = it;
		this.durability = 0;
	}
	
	Brewing(int pr, Material mat, int dur) {
		
		this.price = pr;
		this.material = mat;
		
		ItemStack it = new ItemStack(mat, 1, (short) dur);
		ItemMeta im = it.getItemMeta();
		im.setDisplayName(ChatColor.GREEN + ItemNames.get(it.getTypeId() + ":" + dur));
		List<String> lores = new ArrayList<>();
		lores.add(ChatColor.GREEN + "$" + ChatColor.DARK_GREEN + pr);
		im.setLore(lores);
		it.setItemMeta(im);
		
		
		this.item = it;
		this.durability = dur;
	}
	
	public int getDurability() {
		
		return durability;
	}
	
	public ItemStack getItem() {
		
		return item;
	}
	
	public Material getMaterial() {
		
		return material;
	}
	
	public double getPrice() {
		
		return price;
	}
}
