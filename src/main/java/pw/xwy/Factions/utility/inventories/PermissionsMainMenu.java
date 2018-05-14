package pw.xwy.factions.utility.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import pw.xwy.factions.objects.ItemBuilder;
import pw.xwy.factions.objects.faction.XPlayerFaction;
import pw.xwy.factions.objects.faction.XRank;
import pw.xwy.factions.utility.managers.PlayerManager;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class PermissionsMainMenu {
	
	public static Inventory get(Player p, boolean leader) {
		Inventory inv = Bukkit.createInventory(null, 9, "Groups");
		XPlayerFaction faction = PlayerManager.getOnlinePlayerFaction(p);
		int slot = 0;
		if (!leader) {
			for (XRank rank : faction.getRole(p.getUniqueId()).lower) {
				inv.setItem(slot++, new ItemBuilder(Material.STAINED_GLASS_PANE)
						.setName("&7&l» &6&l" + rank.name)
						.setDurability(1).addGlow().get());
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
						.setDurability(1).addGlow().get());
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
