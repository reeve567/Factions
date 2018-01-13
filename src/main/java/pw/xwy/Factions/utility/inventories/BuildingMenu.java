package pw.xwy.Factions.utility.inventories;
// made by reeve
// on 3:54 PM

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pw.xwy.Factions.enums.sell.Building;
import pw.xwy.Factions.utility.ItemUtil;

public class BuildingMenu {

	private static Inventory inv = Bukkit.createInventory(null, 54, ChatColor.RED + "" + ChatColor.BOLD + "Buy Menu");
	private static Inventory inv2 = Bukkit.createInventory(null, 54, ChatColor.RED + "" + ChatColor.BOLD + "Buy Menu");
	private static Inventory inv3 = Bukkit.createInventory(null, 54, ChatColor.RED + "" + ChatColor.BOLD + "Buy Menu");

	static {
		ItemStack pGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		pGlass.setDurability((short) 7);
		for (int i = 0; i < 54; i++) {
			inv.setItem(i, pGlass);
		}

		for (int i = 0; i < 54; i++) {
			inv2.setItem(i, pGlass);
		}

		for (int i = 0; i < 54; i++) {
			inv3.setItem(i, pGlass);
		}


		int c = 0;

		for (Building b : Building.values()) {
			if (c < 52) {
				inv.setItem(c, b.getItem());
			} else if (c < 104) {
				inv2.setItem(c - 52, b.getItem());
			} else if (c <156) {
				inv3.setItem(c-104,b.getItem());
			}
			c++;
		}

		inv.setItem(53, ItemUtil.next());
		inv.setItem(52, ItemUtil.previous());

		inv2.setItem(53, ItemUtil.next());
		inv2.setItem(52, ItemUtil.previous());

		inv3.setItem(53,ItemUtil.previous());

	}

	public static Inventory getInv() {

		return inv;
	}

	public static Inventory getInv2() {

		return inv2;
	}

	public static Inventory getInv3() {

		return inv3;
	}
}
