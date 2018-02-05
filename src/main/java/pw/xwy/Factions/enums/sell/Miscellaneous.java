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

public enum Miscellaneous {
	BOOK(10, Material.BOOK),
	BEACON(25000, Material.BEACON),
	BUCKET(50, Material.BUCKET),
	WATERBUCKET(100, Material.WATER_BUCKET),
	LAVABUCKET(200, Material.LAVA_BUCKET),
	BOOKANDQUILL(15, Material.BOOK_AND_QUILL),
	PAPER(6, Material.PAPER),
	ENDERPEARL(16, Material.ENDER_PEARL),
	SNOWBALL(6, Material.SNOW_BALL),
	BLAZEROD(50, Material.BLAZE_ROD),
	BONE(50, Material.BONE),
	BONEMEAL(50, Material.INK_SACK, 15),
	COAL(50, Material.COAL),
	CHARCOAL(50, Material.COAL, 1),
	DIAMOND(200, Material.DIAMOND),
	IRON(100, Material.IRON_INGOT),
	GOLD(100, Material.GOLD_INGOT),
	STICK(5, Material.STICK),
	BOWL(5, Material.BOWL),
	STRING(5, Material.STRING),
	FEATHER(5, Material.FEATHER),
	LEATHER(5, Material.LEATHER),
	BRICK(5, Material.CLAY_BRICK),
	CLAY(5, Material.CLAY_BALL),
	EGG(5, Material.EGG),
	INKSACK(5, Material.INK_SACK),
	REDDYE(5, Material.INK_SACK, 1),
	GREENDYE(5, Material.INK_SACK, 2),
	LAPIS(5, Material.INK_SACK, 4),
	PURPLEDYE(5, Material.INK_SACK, 5),
	CYANDYE(5, Material.INK_SACK, 6),
	LGRAYDYE(5, Material.INK_SACK, 7),
	GRAYDYE(5, Material.INK_SACK, 8),
	PINKDYE(5, Material.INK_SACK, 9),
	LIMEDYE(5, Material.INK_SACK, 10),
	YELLOWDYE(5, Material.INK_SACK, 11),
	LBLUEDYE(5, Material.INK_SACK, 12),
	MAGENTADYE(5, Material.INK_SACK, 13),
	ORAGEDYE(5, Material.INK_SACK, 14),
	EMERALD(500, Material.EMERALD),
	NETHERSTAR(12500, Material.NETHER_STAR),
	NETHERQUARTZ(10, Material.QUARTZ),
	PRISMARINESHARD(10, Material.PRISMARINE_SHARD),
	PRISMARINECRYSTALS(10, Material.PRISMARINE_CRYSTALS),
	ENDERCHEST(1000, Material.ENDER_CHEST),
	CHEST(100, Material.CHEST),
	TRAPPEDCHEST(200, Material.TRAPPED_CHEST);
	
	private final double price;
	private final Material material;
	private final ItemStack item;
	private final int durability;
	
	Miscellaneous(int pr, Material mat) {
		
		this.price = pr;
		this.material = mat;
		
		ItemStack it = new ItemStack(mat);
		ItemMeta im = it.getItemMeta();
		im.setDisplayName(ChatColor.GREEN + ItemNames.get(String.valueOf(it.getTypeId())));
		List<String> lores = new ArrayList<>();
		lores.add(ChatColor.GREEN + "$" + ChatColor.DARK_GREEN + pr);
		im.setLore(lores);
		it.setItemMeta(im);
		
		this.item = it;
		this.durability = 0;
	}
	
	Miscellaneous(int pr, Material mat, int dur) {
		
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
