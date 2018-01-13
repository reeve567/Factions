package pw.xwy.Factions.utility.inventories;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.objects.XRank;
import pw.xwy.Factions.utility.managers.FactionManager;

public class PermissionsMainMenu {
	
	public static Inventory get(Player p, boolean leader) {
		Inventory inv = Bukkit.createInventory(null,9,"Groups");
		XFaction faction = FactionManager.getUUIDFaction(p.getUniqueId());
		if (!leader) {
			for (XRank rank : faction.getRole(p.getUniqueId()).lower) {
				
			}
		}
		
		
		return inv;
	}
	
}
