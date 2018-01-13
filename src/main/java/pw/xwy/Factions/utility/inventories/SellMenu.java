package pw.xwy.Factions.utility.inventories;
// made by reeve
// on 7:15 PM

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pw.xwy.Factions.utility.ItemUtil;

public class SellMenu {

	public static Inventory getInv(Player player) {

		Inventory inv = Bukkit.createInventory(player,45, ChatColor.RED + "" + ChatColor.BOLD + "Sell Menu");

		for (int i = 27; i < 45; i++) {
			inv.setItem(i, ItemUtil.changeName(new ItemStack(Material.IRON_FENCE),"&a ",true));
		}

		inv.setItem(36,ItemUtil.changeName(new ItemStack(Material.FIREWORK_CHARGE),"&aItems Inserted",true));
		inv.setItem(39,ItemUtil.changeName(new ItemStack(Material.STAINED_GLASS_PANE,1, (short) 5),"&aSell",true));
		inv.setItem(40,ItemUtil.changeName(new ItemStack(Material.STAINED_GLASS_PANE,1, (short) 14),"&cCancel",true));
		inv.setItem(41,ItemUtil.changeName(new ItemStack(Material.GOLD_NUGGET),"&6Value of items",true));

		return inv;
	}

}
