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

public enum Building {
	STONE(3, Material.STONE),
	GRANITE(3, Material.STONE, 1),
	POLISHEDGRANITE(4, Material.STONE, 2),
	DIORITE(3, Material.STONE, 3),
	POLISHEDDIORITE(4, Material.STONE, 4),
	GRASS(3, Material.GRASS),
	DIRT(3, Material.DIRT),
	COARSEDIRT(3, Material.DIRT, 1),
	PODZOL(3, Material.DIRT, 2),
	COBBLESTONE(3, Material.COBBLESTONE),
	OAKWOODPLANKS(3, Material.WOOD),
	SPRUCEWOODPLANKS(3, Material.WOOD, 1),
	BIRCHWOODPLANKS(3, Material.WOOD, 2),
	JUNGLEWOODPLANKS(3, Material.WOOD, 3),
	ACACIAWOODPLANKS(3, Material.WOOD, 4),
	DARKOAKWOODPLANKS(3, Material.WOOD, 5),
	SAND(3, Material.SAND),
	REDSAND(3, Material.SAND, 1),
	GRAVEL(3, Material.GRAVEL),
	GOLDORE(500, Material.GOLD_ORE),
	IRONORE(500, Material.IRON_ORE),
	COALORE(300, Material.COAL_ORE),
	OAKWOOD(12, Material.LOG),
	SPRUCEWOOD(12, Material.LOG, 1),
	BIRCHWOOD(12, Material.LOG, 2),
	JUNGLEWOOD(12, Material.LOG, 3),
	ACACIAWOOD(12, Material.LOG_2),
	DARKOAKWOOD(12, Material.LOG_2, 1),
	SPONGE(100, Material.SPONGE),
	GLASS(5, Material.GLASS),
	LAPISORE(30, Material.LAPIS_ORE),
	LAPISBLOCK(50, Material.LAPIS_BLOCK),
	SANDSTONE(3, Material.SANDSTONE),
	CHISELLEDSANDSTONE(3, Material.SANDSTONE, 1),
	SMOOTHSANDSTONE(3, Material.SANDSTONE, 2),
	WOOL(3, Material.WOOL),
	ORANGEWOOL(3, Material.WOOL, 1),
	MAGENTAWOOL(3, Material.WOOL, 2),
	LIGHTBLUEWOOL(3, Material.WOOL, 3),
	YELLOWWOOL(3, Material.WOOL, 4),
	LIMEWOOL(3, Material.WOOL, 5),
	PINKWOOL(3, Material.WOOL, 6),
	GRAYWOOL(3, Material.WOOL, 7),
	LIGHTGRAYWOOL(3, Material.WOOL, 8),
	CYANWOOL(3, Material.WOOL, 9),
	PURPLEWOOL(3, Material.WOOL, 10),
	BLUEWOOL(3, Material.WOOL, 11),
	BROWNWOOL(3, Material.WOOL, 12),
	GREENWOOL(3, Material.WOOL, 13),
	REDWOOL(3, Material.WOOL, 14),
	BLACKWOOL(3, Material.WOOL, 15),
	GOLDBLOCK(1000, Material.GOLD_BLOCK),
	DIAMONDBLOCK(2000, Material.DIAMOND_BLOCK),
	EMERALDBLOCK(5000, Material.EMERALD_BLOCK),
	IRONBLOCK(1000, Material.IRON_BLOCK),
	COALBLOCK(500, Material.COAL_BLOCK),
	SLAB(3, Material.STEP),
	SANDSTONESLAB(3, Material.STEP, 1),
	COBBLESTONESLAB(3, Material.STEP, 3),
	BRICKSLAB(3, Material.STEP, 4),
	STONEBRICKSLAB(3, Material.STEP, 5),
	NETHERBRICKSLAB(3, Material.STEP, 6),
	QUARTZSLAB(3, Material.STEP, 7),
	BRICKS(5, Material.BRICK),
	BOOKSHELF(10, Material.BOOKSHELF),
	MOSSSTONE(5, Material.MOSSY_COBBLESTONE),
	OBSIDIAN(16, Material.OBSIDIAN),
	OAKWOODSTAIRS(3, Material.WOOD_STAIRS),
	DIAMONDORE(800, Material.DIAMOND_ORE),
	COBBLESTONESTAIRS(3, Material.COBBLESTONE_STAIRS),
	REDSTONEORE(100, Material.REDSTONE_ORE),
	ICE(100, Material.ICE),
	SNOW(3, Material.SNOW),
	CLAY(3, Material.CLAY),
	PUMPKIN(3, Material.PUMPKIN),
	NETHERRACK(5, Material.NETHERRACK),
	SOULSAND(100, Material.SOUL_SAND),
	GLOWSTONE(100, Material.GLOWSTONE),
	JACKOLANTERN(5, Material.JACK_O_LANTERN),
	STAINEDGLASS(3, Material.STAINED_GLASS),
	ORANGESTAINEDGLASS(3, Material.STAINED_GLASS, 1),
	MAGENTASTAINEDGLASS(3, Material.STAINED_GLASS, 2),
	LIGHTBLUESTAINEDGLASS(3, Material.STAINED_GLASS, 3),
	YELLOWSTAINEDGLASS(3, Material.STAINED_GLASS, 4),
	LIGHTGREENSTAINEDGLASS(3, Material.STAINED_GLASS, 5),
	PINKSTAINEDGLASS(3, Material.STAINED_GLASS, 6),
	GRAYSTAINEDGLASS(3, Material.STAINED_GLASS, 7),
	LIGHTGRAYSTAINEDGLASS(3, Material.STAINED_GLASS, 8),
	CYANSTAINEDGLASS(3, Material.STAINED_GLASS, 9),
	PURPLESTAINEDGLASS(3, Material.STAINED_GLASS, 10),
	BLUESTAINEDGLASS(3, Material.STAINED_GLASS, 11),
	BROWNSTAINEDGLASS(3, Material.STAINED_GLASS, 12),
	GREENSTAINEDGLASS(3, Material.STAINED_GLASS, 13),
	REDSTAINEDGLASS(3, Material.STAINED_GLASS, 14),
	BLACKSTAINEDGLASS(3, Material.STAINED_GLASS, 15),
	STONEBRICKS(5, Material.SMOOTH_BRICK),
	MOSSYSTONEBRICKS(5, Material.SMOOTH_BRICK, 1),
	CRACKEDSTONEBRICKS(5, Material.SMOOTH_BRICK, 2),
	CHISELEDSTONEBRICKS(5, Material.SMOOTH_BRICK, 3),
	BRICKSTAIRS(5, Material.BRICK_STAIRS),
	STONEBRICKSTAIRS(5, Material.SMOOTH_STAIRS),
	MYCELIUM(3, Material.MYCEL),
	NETHERBRICKS(5, Material.NETHER_BRICK),
	NETHERBRICKSTAIRS(5, Material.NETHER_BRICK_STAIRS),
	ENDSTONE(5, Material.ENDER_STONE),
	OAKWOODSLAB(3, Material.WOOD_STEP),
	SPRUCEWOODSLAB(3, Material.WOOD_STEP, 1),
	BIRCHWOODSLAB(3, Material.WOOD_STEP, 2),
	JUNGLEWOODSLAB(3, Material.WOOD_STEP, 3),
	ACACIAWOODSLAB(3, Material.WOOD_STEP, 4),
	DARKOAKWOODSLAB(3, Material.WOOD_STEP, 5),
	SANDSTONESTAIRS(3, Material.SANDSTONE_STAIRS),
	EMERALDORE(1200, Material.EMERALD_ORE),
	SPRUCEWOODSTAIRS(3, Material.SPRUCE_WOOD_STAIRS),
	BIRCHWOODSTAIRS(3, Material.BIRCH_WOOD_STAIRS),
	JUNGLEWOODSTIARS(3, Material.JUNGLE_WOOD_STAIRS),
	COBBLESTONEWALL(5, Material.COBBLE_WALL),
	MOSSYCOBBLESTONEWALL(5, Material.COBBLE_WALL, 1),
	QUARTZBLOCK(10, Material.QUARTZ_BLOCK),
	CHISELEDQUARTZBLOCK(10, Material.QUARTZ_BLOCK, 1),
	PILLARQUARTZBLOCK(10, Material.QUARTZ_BLOCK, 2),
	QUARTZSTAIRS(10, Material.QUARTZ_STAIRS),
	WHITESTAINEDCLAY(3, Material.STAINED_CLAY),
	ORANGESTAINEDCLAY(3, Material.STAINED_CLAY, 1),
	MAGENTASTAINEDCLAY(3, Material.STAINED_CLAY, 2),
	LIGHTBLUESTAINEDCLAY(3, Material.STAINED_CLAY, 3),
	YELLOWSTAINEDCLAY(3, Material.STAINED_CLAY, 4),
	LIMESTAINEDCLAY(3, Material.STAINED_CLAY, 5),
	PINKSTAINEDCLAY(3, Material.STAINED_CLAY, 6),
	GRAYSTAINEDCLAY(3, Material.STAINED_CLAY, 7),
	LIGHTGRAYSTAINEDCLAY(3, Material.STAINED_CLAY, 8),
	CYANSTAINEDCLAY(3, Material.STAINED_CLAY, 9),
	PURPLESTAINEDCLAY(3, Material.STAINED_CLAY, 10),
	BLUESTAINEDCLAY(3, Material.STAINED_CLAY, 11),
	BROWNSTAINEDCLAY(3, Material.STAINED_CLAY, 12),
	GREENSTAINEDCLAY(3, Material.STAINED_CLAY, 13),
	REDSTAINEDCLAY(3, Material.STAINED_CLAY, 14),
	BLACKSTAINEDCLAY(3, Material.STAINED_CLAY, 15),
	ACACIAWOODSTAIRS(3, Material.ACACIA_STAIRS),
	DARKOAKWOODSTAIRS(3, Material.DARK_OAK_STAIRS),
	PRISMARINE(10, Material.PRISMARINE),
	PRISMARINEBRICK(20, Material.PRISMARINE, 1),
	DARKPRISMARINE(20, Material.PRISMARINE, 2),
	SEALANTERN(20, Material.SEA_LANTERN),
	HAYBLOCK(10, Material.HAY_BLOCK),
	HARDENEDCLAY(3, Material.HARD_CLAY),
	PACKEDICE(10, Material.PACKED_ICE),
	REDSANDSTONE(3, Material.RED_SANDSTONE),
	REDCHISELEDSANDSTONE(3, Material.RED_SANDSTONE, 1),
	REDSMOOTHSANDSTONE(3, Material.RED_SANDSTONE, 2),
	REDSANDSTONESTAIRS(3, Material.RED_SANDSTONE_STAIRS),
	REDSANDSTONESLAB(3, Material.STONE_SLAB2);
	
	private final double price;
	private final Material material;
	private final ItemStack item;
	private final int durability;
	
	Building(int pr, Material mat) {
		
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
	
	Building(int pr, Material mat, int dur) {
		
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
