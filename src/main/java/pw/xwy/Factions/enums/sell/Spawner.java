package pw.xwy.factions.enums.sell;
// made by reeve
// on 1:40 PM

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pw.xwy.factions.utility.ItemNames;

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

public enum Spawner {
	IRONGOLEM(2000000, 99),
	BLAZE(750000, 61),
	SLIME(1000000, 55),
	ZOMBIE(125000, 54),
	WITCH(500000, 66),
	SKELETON(125000, 51),
	CHICKEN(25000, 93),
	COW(50000, 92),
	SHEEP(45000, 91),
	MOOSHROOM(75000, 96),
	PIG(45000, 90),
	SQUID(25000, 94),
	BAT(15000, 65),
	ZOMBIEPIGMAN(500000, 57),
	WOLF(15000, 95),
	OCELOT(15000, 98),
	SPIDER(125000, 52),
	CAVESPIDER(125000, 59),
	MAGMACUBE(1000000, 62),
	CREEPER(225000, 50);
	private final double price;
	private final Material material;
	private final ItemStack item;
	private final int durability;
	
	Spawner(int pr, int dur) {
		
		price = pr;
		material = Material.MONSTER_EGG;
		
		ItemStack it = new ItemStack(material, 1, (short) dur);
		ItemMeta im = it.getItemMeta();
		im.setDisplayName(ChatColor.GREEN + ItemNames.get(String.valueOf(it.getTypeId()), (short) dur));
		List<String> lores = new ArrayList<>();
		lores.add(ChatColor.translateAlternateColorCodes('&', "&a$&2" + pr));
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
