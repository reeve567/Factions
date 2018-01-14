package pw.xwy.Factions.utility.inventories;
// made by reeve
// on 3:50 PM

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pw.xwy.Factions.enums.sell.Farming;

public class FarmingMenu {
	private static Inventory inv = Bukkit.createInventory(null, 36, ChatColor.RED + "" + ChatColor.BOLD + "Buy Menu");
	
	static {
		ItemStack pGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		pGlass.setDurability((short) 7);
		for (int i = 0; i < 36; i++) {
			inv.setItem(i, pGlass);
		}
		
		int j = 0;
		
		for (Farming m : Farming.values()) {
			if (j < 36) {
				inv.setItem(j, m.getItem());
			}
			j++;
		}
	}
	
	public static Inventory getInv() {
		
		return inv;
	}
	
}
