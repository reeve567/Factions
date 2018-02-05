package pw.xwy.Factions.enums.sell;
// made by reeve
// on 6:07 PM

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

public enum Sell {
	
	TNT(10, Material.TNT),
	EMERALD(100, Material.EMERALD),
	IRON(20, Material.IRON_INGOT),
	GOLD(20, Material.GOLD_INGOT),
	DIAMOND(40, Material.DIAMOND),
	IRONBLOCK(180, Material.IRON_BLOCK),
	DIAMONDBLOCK(360, Material.DIAMOND_BLOCK),
	EMERALDBLOCK(900, Material.EMERALD_BLOCK),
	GOLDBLOCK(180, Material.GOLD_BLOCK),
	COAL(5, Material.COAL),
	CHARCOAL(5, Material.COAL, 1),
	NETHERSTAR(2500, Material.NETHER_STAR),
	BEACON(5000, Material.BEACON),
	BOOK(5, Material.BOOK),
	FEATHER(1, Material.FEATHER),
	BONE(3, Material.BONE),
	BONEMEAL(1, Material.INK_SACK, 15),
	
	LEATHERHELM(10, Material.LEATHER_HELMET),
	LEATHERCHEST(10, Material.LEATHER_CHESTPLATE),
	LEATHERLEGGINGS(10, Material.LEATHER_LEGGINGS),
	LEATHERBOOTS(10, Material.LEATHER_BOOTS),
	
	CHAINHELM(15, Material.CHAINMAIL_HELMET),
	CHAINCHEST(15, Material.CHAINMAIL_CHESTPLATE),
	CHAINLEGGINGS(15, Material.CHAINMAIL_LEGGINGS),
	CHAINBOOTS(15, Material.CHAINMAIL_BOOTS),
	
	GOLDHELM(10, Material.GOLD_HELMET),
	GOLDCHEST(10, Material.GOLD_CHESTPLATE),
	GOLDLEGGINGS(10, Material.GOLD_LEGGINGS),
	GOLDBOOTS(10, Material.GOLD_BOOTS),
	
	IRONHELM(20, Material.IRON_HELMET),
	IRONCHEST(20, Material.IRON_CHESTPLATE),
	IRONLEGGINGS(20, Material.IRON_LEGGINGS),
	IRONBOOTS(20, Material.IRON_BOOTS),
	
	DIAMONDHELM(25, Material.DIAMOND_HELMET),
	DIAMONDCHEST(25, Material.DIAMOND_CHESTPLATE),
	DIAMONDLEGGINGS(25, Material.DIAMOND_LEGGINGS),
	DIAMONDBOOTS(25, Material.DIAMOND_BOOTS),
	
	WOODSWORD(3, Material.WOOD_SWORD),
	STONESWORD(4, Material.WOOD_SWORD),
	GOLDSWORD(3, Material.WOOD_SWORD),
	IRONSWORD(5, Material.WOOD_SWORD),
	DIAMONDSWORD(10, Material.WOOD_SWORD),
	
	BOW(5, Material.BOW),
	INKSAK(1, Material.INK_SACK),
	ROSERED(1, Material.INK_SACK, 1),
	CACTUSGREEN(1, Material.INK_SACK, 2),
	LAPIS(1, Material.INK_SACK, 4),
	PURPLEDYE(1, Material.INK_SACK, 5),
	PINKDYE(1, Material.INK_SACK, 6),
	CYANDYE(1, Material.INK_SACK, 7),
	LIGHTGRAYDYE(1, Material.INK_SACK, 8),
	GRAYDYE(1, Material.INK_SACK, 9),
	LIMEDYE(1, Material.INK_SACK, 10),
	YELLOWDYE(1, Material.INK_SACK, 11),
	LIGHTBLUEDYE(1, Material.INK_SACK, 12),
	MAGENTADYE(1, Material.INK_SACK, 13),
	ORANGEDYE(1, Material.INK_SACK, 14),
	NETHERQUARTZ(5, Material.QUARTZ),
	ICE(10, Material.ICE),
	PACKEDICE(1, Material.PACKED_ICE);
	private final double price;
	private final Material material;
	private final ItemStack item;
	private final int durability;
	
	Sell(int pr, Material mat) {
		
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
	
	Sell(int pr, Material mat, int dur) {
		
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
