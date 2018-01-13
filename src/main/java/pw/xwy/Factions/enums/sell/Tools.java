package pw.xwy.Factions.enums.sell;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pw.xwy.Factions.utility.ItemNames;

import java.util.ArrayList;
import java.util.List;

public enum Tools {

	ISHOVEL(300,Material.IRON_SPADE),
	IPICK(300,Material.IRON_PICKAXE),
	IAXE(300,Material.IRON_AXE),
	FLINTNSTEEL(200,Material.FLINT_AND_STEEL),
	WSHOVEL(100,Material.WOOD_SPADE),
	WPICK(100,Material.WOOD_PICKAXE),
	WAXE(100,Material.WOOD_AXE),
	SSHOVEL(200,Material.STONE_SPADE),
	SPICK(200,Material.STONE_PICKAXE),
	SAXE(300,Material.STONE_AXE),
	DSHOVEL(400,Material.DIAMOND_SPADE),
	DPICK(400,Material.DIAMOND_PICKAXE),
	DAXE(400,Material.DIAMOND_AXE),
	WHOE(100,Material.WOOD_HOE),
	SHOE(200,Material.STONE_HOE),
	IHOE(300,Material.IRON_HOE),
	DHOE(400,Material.DIAMOND_HOE),
	GHOE(150,Material.GOLD_HOE),
	GPICK(150,Material.GOLD_PICKAXE),
	GSHOVEL(150,Material.GOLD_SPADE),
	GAXE(150,Material.GOLD_AXE),
	COMPASS(500,Material.COMPASS),
	FISHINGROD(400,Material.FISHING_ROD),
	CLOCK(500,Material.WATCH),
	SHEARS(300,Material.SHEARS),
	LEAD(300,Material.LEASH),
	NAMETAG(500,Material.NAME_TAG)

	;

	private final double price;
	private final Material material;
	private final ItemStack item;
	private final int durability;

	Tools(int pr, Material mat) {

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

	Tools(int pr, Material mat, int dur) {

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
