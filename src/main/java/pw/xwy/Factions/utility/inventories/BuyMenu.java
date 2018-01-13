package pw.xwy.Factions.utility.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pw.xwy.Factions.utility.ItemUtil;
import pw.xwy.Factions.utility.StringUtil;

public class BuyMenu {
	
	public static void open(Player player, ItemStack it, ItemMeta im, double price) {
		Inventory inv = Bukkit.createInventory(player, 45, StringUtil.chatColorConv("&a&lBuy Menu"));
		inv.setItem(9, ItemUtil.changeName(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14), StringUtil.chatColorConv("&cRemove 1 item"), false));
		inv.setItem(10, ItemUtil.changeName(new ItemStack(Material.STAINED_GLASS_PANE, 10, (short) 14), StringUtil.chatColorConv("&cRemove 10 items"), false));
		inv.setItem(11, ItemUtil.changeName(new ItemStack(Material.STAINED_GLASS_PANE, 64, (short) 14), StringUtil.chatColorConv("&cRemove 64 items"), false));
		inv.setItem(15, ItemUtil.changeName(new ItemStack(Material.STAINED_GLASS_PANE, 64, (short) 5), StringUtil.chatColorConv("&aAdd 64 items"), false));
		inv.setItem(16, ItemUtil.changeName(new ItemStack(Material.STAINED_GLASS_PANE, 10, (short) 5), StringUtil.chatColorConv("&aAdd 10 items"), false));
		inv.setItem(17, ItemUtil.changeName(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5), StringUtil.chatColorConv("&aAdd 1 item"), false));
		it.setItemMeta(im);
		inv.setItem(13, it);
		
		for (int i = 27; i < 45; i++) {
			inv.setItem(i, ItemUtil.changeName(new ItemStack(Material.IRON_FENCE), "&f", true));
		}
		
		inv.setItem(40, ItemUtil.changeName(new ItemStack(Material.FIREWORK_CHARGE), StringUtil.chatColorConv("&a$&2" + String.valueOf(price)),false));
		
		
		inv.setItem(39, ItemUtil.changeName(new ItemStack(Material.STAINED_CLAY, 1, (short) 14), "&cCancel", true));
		inv.setItem(41, ItemUtil.changeName(new ItemStack(Material.STAINED_CLAY, 1, (short) 5), "&aBuy", true));
		player.openInventory(inv);
	}
	
}
