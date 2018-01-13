package pw.xwy.Factions.utility.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import pw.xwy.Factions.objects.ItemBuilder;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.objects.XRank;
import pw.xwy.Factions.utility.managers.FactionManager;

public class PermissionsMainMenu {
	
	public static Inventory get(Player p, boolean leader) {
		Inventory inv = Bukkit.createInventory(null, 9, "Groups");
		XFaction faction = FactionManager.getUUIDFaction(p.getUniqueId());
		int slot = 0;
		if (!leader) {
			for (XRank rank : faction.getRole(p.getUniqueId()).lower) {
				inv.setItem(slot++, new ItemBuilder(Material.STAINED_GLASS_PANE)
						.setName("&7&l» &6&l" + rank.name)
						.setDurability(1).get());
			}
			while (slot < 9) {
				inv.setItem(slot++, new ItemBuilder(Material.STAINED_GLASS_PANE)
						.setName("&c&lCreate a Group")
						.setDurability(7).get());
			}
		} else {
			for (XRank rank : faction.ranks) {
				inv.setItem(slot++, new ItemBuilder(Material.STAINED_GLASS_PANE)
						.setName("&7&l» &6&l" + rank.name)
						.setDurability(1).get());
			}
			while (slot < 9) {
				inv.setItem(slot++, new ItemBuilder(Material.STAINED_GLASS_PANE)
						.setName("&c&lCreate a Group")
						.setDurability(7).get());
			}
		}
		
		
		return inv;
	}
	
}
