package pw.xwy.factions.enums.sell;
// made by reeve
// on 2:18 PM

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

public enum Raiding {
	TNT(50, Material.TNT),
	CREEPEREGG(40000, Material.MONSTER_EGG, 50),
	FLINTNSTEEL(200, Material.FLINT_AND_STEEL),
	WATERBUCKET(100, Material.WATER_BUCKET),
	BUCKET(50, Material.BUCKET),
	LAVABUCKET(200, Material.LAVA_BUCKET),
	STONESLAB(10, Material.STEP),
	COBWEB(25, Material.WEB),
	SPONGE(100, Material.SPONGE),
	REDSTONE(15, Material.REDSTONE),
	REDSTONETORCH(8, Material.REDSTONE_TORCH_ON),
	REDSTONEBLOCK(115, Material.REDSTONE_BLOCK),
	REDSTONECOMPARATOR(25, Material.REDSTONE_COMPARATOR),
	REDSTONEREPEATER(12, Material.DIODE),
	PISTON(35, Material.PISTON_BASE),
	STICKYPISTON(70, Material.PISTON_STICKY_BASE),
	DISPENSER(15, Material.DISPENSER),
	BUTTON(6, Material.STONE_BUTTON),
	LEVER(6, Material.LEVER),
	HOPPER(10000, Material.HOPPER),
	MINECART(350, Material.MINECART),
	RAIL(10, Material.RAILS),
	DETECTORRAIL(50, Material.DETECTOR_RAIL),
	POWEREDRAIL(100, Material.POWERED_RAIL),
	ACTIVATORRAIL(100, Material.ACTIVATOR_RAIL),
	IRONDOOR(100, Material.IRON_DOOR),
	IRONTRAPDOOR(100, Material.IRON_TRAPDOOR),
	STONEPRESSUREPLATE(50, Material.STONE_PLATE),
	WOODPRESSUREPLATE(50, Material.WOOD_PLATE),
	GOLDPRESSUREPLATE(50, Material.GOLD_PLATE),
	IRONPRESSUREPLATE(50, Material.IRON_PLATE),
	REDSTONELAMP(100, Material.REDSTONE_LAMP_OFF),
	TRIPWIREHOOK(25, Material.TRIPWIRE_HOOK),
	DAYLIGHTSENSOR(50, Material.DAYLIGHT_DETECTOR);
	
	private final double price;
	private final Material material;
	private final ItemStack item;
	private final int durability;
	
	Raiding(int pr, Material mat) {
		
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
	
	Raiding(int pr, Material mat, int dur) {
		
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
