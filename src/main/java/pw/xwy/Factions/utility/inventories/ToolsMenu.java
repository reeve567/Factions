package pw.xwy.Factions.utility.inventories;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pw.xwy.Factions.enums.sell.Tools;

public class ToolsMenu {
	
	private static Inventory inv = Bukkit.createInventory(null, 27, ChatColor.RED + "" + ChatColor.BOLD + "Buy Menu");
	
	static {
		ItemStack pGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		pGlass.setDurability((short) 7);
		for (int i = 0; i < 27; i++) {
			inv.setItem(i, pGlass);
		}
		
		int j = 0;
		
		for (Tools m : Tools.values()) {
			if (j < 27) {
				inv.setItem(j, m.getItem());
			}
			j++;
		}
		
	}
	
	public static Inventory getInv() {
		
		return inv;
	}
	
	
}
