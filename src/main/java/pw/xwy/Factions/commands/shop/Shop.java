package pw.xwy.Factions.commands.shop;
// made by reeve
// on 2:50 PM

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pw.xwy.Factions.utility.StringUtil;
import pw.xwy.Factions.utility.inventories.MainMenu;

public class Shop {

	public static void run(CommandSender sender) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("factionsshop.shop")) {
				player.openInventory(MainMenu.getInv());
			}
			else {
				player.sendMessage(StringUtil.chatColorConv("&cYou do not have permission to do this!"));
			}

		}
	}

}
