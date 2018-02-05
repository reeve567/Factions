package pw.xwy.Factions.utility.handlers;

import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import pw.xwy.Factions.utility.StringUtility;
import pw.xwy.Factions.utility.inventories.*;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class CitizensHandler implements Listener {
	
	@EventHandler
	public void onCitizenClick(NPCRightClickEvent e) {
		Player player = e.getClicker().getPlayer();
		if (e.getNPC().getName().equalsIgnoreCase(ChatColor.BLUE + "" + ChatColor.BOLD + "Tools")) {
			player.openInventory(ToolsMenu.getInv());
		} else if (e.getNPC().getName().equalsIgnoreCase(StringUtility.conv("&2&lFarming"))) {
			player.openInventory(FarmingMenu.getInv());
		} else if (e.getNPC().getName().equalsIgnoreCase(StringUtility.conv("&b&lCombat"))) {
			player.openInventory(CombatMenu.getInv());
		} else if (e.getNPC().getName().equalsIgnoreCase(StringUtility.conv("&3&lSpawners"))) {
			player.openInventory(SpawnersMenu.getInv());
		} else if (e.getNPC().getName().equalsIgnoreCase(StringUtility.conv("&a&lBrewing"))) {
			player.openInventory(BrewingMenu.getInv());
		} else if (e.getNPC().getName().equalsIgnoreCase(StringUtility.conv("&4&lRaiding"))) {
			player.openInventory(RaidingMenu.getInv());
		} else if (e.getNPC().getName().equalsIgnoreCase(StringUtility.conv("&lMiscellaneous"))) {
			player.openInventory(MiscMenu.getInv());
		} else if (e.getNPC().getName().equalsIgnoreCase(StringUtility.conv("&5&lBuilding"))) {
			player.openInventory(BuildingMenu.getInv());
		} else if (e.getNPC().getName().equalsIgnoreCase(StringUtility.conv("&6&lSell Shop"))) {
			player.openInventory(SellMenu.getInv(player));
		} else if (e.getNPC().getName().equalsIgnoreCase(ChatColor.GOLD + "ShopKeeper")) {
			player.openInventory(MainMenu.getInv());
		}
	}
	
}
