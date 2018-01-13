package pw.xwy.Factions.enums.sell;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pw.xwy.Factions.utility.ItemNames;

import java.util.ArrayList;
import java.util.List;

public enum Combat {
	LEATHERHELM(110,Material.LEATHER_HELMET),
	LEATHERCHEST(130,Material.LEATHER_CHESTPLATE),
	LEATHERLEGGINGS(120,Material.LEATHER_LEGGINGS),
	LEATHERBOOTS(100,Material.LEATHER_BOOTS),

	CHAINHELM(310,Material.CHAINMAIL_HELMET),
	CHAINCHEST(330,Material.CHAINMAIL_CHESTPLATE),
	CHAINLEGGINGS(320,Material.CHAINMAIL_LEGGINGS),
	CHAINBOOTS(300,Material.CHAINMAIL_BOOTS),

	GOLDHELM(210,Material.GOLD_HELMET),
	GOLDCHEST(230,Material.GOLD_CHESTPLATE),
	GOLDLEGGINGS(220,Material.GOLD_LEGGINGS),
	GOLDBOOTS(200,Material.GOLD_BOOTS),

	IRONHELM(410,Material.IRON_HELMET),
	IRONCHEST(430,Material.IRON_CHESTPLATE),
	IRONLEGGINGS(420,Material.IRON_LEGGINGS),
	IRONBOOTS(400,Material.IRON_BOOTS),

	DIAMONDHELM(710,Material.DIAMOND_HELMET),
	DIAMONDCHEST(730,Material.DIAMOND_CHESTPLATE),
	DIAMONDLEGGINGS(720,Material.DIAMOND_LEGGINGS),
	DIAMONDBOOTS(700,Material.DIAMOND_BOOTS),

	WOODSWORD(100,Material.WOOD_SWORD),
	STONESWORD(300,Material.STONE_SWORD),
	GOLDSWORD(200,Material.GOLD_SWORD),
	IRONSWORD(400,Material.IRON_SWORD),
	DIAMONDSWORD(700,Material.DIAMOND_SWORD),

	BOW(200,Material.BOW),
	ARROW(2,Material.ARROW)

	;

	private final double price;
	private final Material material;
	private final ItemStack item;
	private final int durability;

	Combat(int pr, Material mat) {

		this.price = pr;
		this.material = mat;

		ItemStack it = new ItemStack(mat);
		ItemMeta im = it.getItemMeta();
		im.setDisplayName(ChatColor.GREEN + ItemNames.get(String.valueOf(it.getTypeId())));
		List<String> lores = new ArrayList<>();
		lores.add(ChatColor.translateAlternateColorCodes('&',"&a$&2"+pr));
		im.setLore(lores);
		it.setItemMeta(im);


		this.item = it;
		this.durability = 0;
	}

	Combat(int pr, Material mat, int dur) {

		this.price = pr;
		this.material = mat;

		ItemStack it = new ItemStack(mat, 1, (short) dur);
		ItemMeta im = it.getItemMeta();
		im.setDisplayName(ChatColor.GREEN + ItemNames.get(it.getTypeId()+ ":" + dur));
		List<String> lores = new ArrayList<>();
		lores.add(ChatColor.GREEN + "$" + ChatColor.DARK_GREEN + pr);
		im.setLore(lores);
		it.setItemMeta(im);


		this.item = it;
		this.durability = dur;
	}

	public double getPrice() {

		return price;
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
}
