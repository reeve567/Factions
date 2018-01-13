package pw.xwy.Factions.enums.sell;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pw.xwy.Factions.utility.ItemNames;

import java.util.ArrayList;
import java.util.List;

public enum Farming {
	VINES(10,Material.VINE),
	LILLYPAD(10,Material.WATER_LILY),
	NETHERWARRT(50,Material.NETHER_STALK),
	SEEDS(10,Material.SEEDS),
	PUMPKINSEEDS(10,Material.PUMPKIN_SEEDS),
	MELONSEEDS(10,Material.MELON_SEEDS),
	OAKSAPLING(75,Material.SAPLING),
	SPRUCESAPLING(75,Material.SAPLING,1),
	BIRCHSAPLING(75,Material.SAPLING,2),
	JUNGLESAPLING(75,Material.SAPLING,3),
	ACACIASAPLING(75,Material.SAPLING,4),
	DARKOAKSAPLING(75,Material.SAPLING,5),
	MELON(10,Material.MELON),
	POTATO(10,Material.POTATO_ITEM),
	CARROT(15,Material.CARROT_ITEM),
	WHEAT(5,Material.WHEAT),
	BREAD(7,Material.BREAD),
	PUMPKIN(15,Material.PUMPKIN),
	MELONBLOCK(100,Material.MELON_BLOCK),
	SUGARCANE(10,Material.SUGAR_CANE),
	CACTUS(10,Material.CACTUS),
	BROWNMUSHROOM(50,Material.BROWN_MUSHROOM),
	REDMUSHROOM(50,Material.RED_MUSHROOM),
	GOLDENAPPLE(5000,Material.GOLDEN_APPLE),
	APPLE(12,Material.APPLE),
	COOKIE(10,Material.COOKIE),
	CAKE(20,Material.CAKE),
	BAKEDPOTATO(20,Material.BAKED_POTATO),
	COOKEDMUTTON(20,Material.COOKED_MUTTON),
	COOKEDCHICKEN(20,Material.COOKED_CHICKEN),
	STEAK(25,Material.COOKED_BEEF),
	COOKEDPORK(20,Material.GRILLED_PORK),
	CLOWNFISH(5,Material.RAW_FISH,2),
	COOKEDFISH(15,Material.COOKED_FISH),
	COOKEDSALMON(15,Material.COOKED_FISH,1)



	;
	private final double price;
	private final Material material;
	private final ItemStack item;
	private final int durability;

	Farming(int pr, Material mat) {

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

	Farming(int pr, Material mat, int dur) {

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
