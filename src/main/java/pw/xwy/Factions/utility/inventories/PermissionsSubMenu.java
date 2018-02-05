package pw.xwy.Factions.utility.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pw.xwy.Factions.objects.ItemBuilder;
import pw.xwy.Factions.objects.XRank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class PermissionsSubMenu {
	
	public static HashMap<String, String> perms = new HashMap<>();

	static {
		perms.put("Change faction tag", "rename");
		perms.put("Change description", "desc");
		perms.put("Invite players", "invite");
		perms.put("Kick members", "kick");
		perms.put("Claim faction land", "claim");
		perms.put("Unclaim faction land", "unclaim");
		perms.put("Faction ally", "ally");
		perms.put("Faction enemy", "enemy");
		perms.put("Faction neutral", "neutral");
		perms.put("Set faction home", "sethome");
		perms.put("Set faction warps", "setwarps");
		perms.put("Delete faction warps", "delwarps");
		perms.put("Use faction home", "home");
		perms.put("Use faction warps", "warps");
		perms.put("Faction fly", "fly");
		perms.put("Place blocks", "place");
		perms.put("Destroy blocks", "break");
		perms.put("Toggle faction openness", "toggleOpen");
	}
	
	public static Inventory get(Player p, XRank rank) {
		Inventory inventory = Bukkit.createInventory(p, 18, "Permissions - " + rank.name);
		ArrayList<ItemStack> itemStacks = new ArrayList<>();
		itemStacks.addAll(Arrays.asList(
				get("Change faction tag", "Ability to change the faction name", rank, "rename"),
				get("Change description", "Sets the description for your faction", rank, "desc"),
				get("Invite players", "Can invite players to your faction", rank, "invite"),
				get("Kick members", "Can kick members from the faction", rank, "kick"),
				get("Claim faction land", "Permission to claim land for your faction", rank, "claim"),
				get("Unclaim faction land", "Permission to unclaim land for your faction", rank, "unclaim"),
				get("Faction ally", "Can send other factions ally requests", rank, "ally"),
				get("Faction enemy", "Gives the ability to enemy other factions", rank, "enemy"),
				get("Faction neutral", "Can send other factions neutral requests", rank, "neutral"),
				get("Set faction home", "Can set a faction home", rank, "sethome"),
				get("Set faction warps", "Can set warps for your faction", rank, "setwarps"),
				get("Delete faction warps", "Can delete warps for your faction", rank, "delwarps"),
				get("Use faction home", "Allows the use of teleporting to faction home", rank, "home"),
				get("Use faction warps", "Allows using faction warps", rank, "warps"),
				get("Faction fly", "Allows fling in your claims", rank, "fly"),
				get("Place blocks", "Gives the ability to place blocks", rank, "place"),
				get("Destroy blocks", "Gives the ability to break blocks", rank, "break"),
				get("Toggle faction openness", "Can toggle whether or not the faction is open to players", rank, "toggleOpen")
		));
		
		int slot = 0;
		for (ItemStack i : itemStacks) {
			inventory.setItem(slot++, i);
		}
		
		return inventory;
	}
	
	private static ItemStack get(String s, String st, XRank rank, String perm) {
		String prefix = "&c&l";
		ItemBuilder ib = new ItemBuilder(Material.STAINED_GLASS_PANE).setLore("&8&l» &7" + st).setDurability(1);
		if (rank.hasPerm(perm, true)) {
			prefix = "&a&l";
			ib = ib.addGlow();
		}
		
		return ib.setName(prefix + s).get();
	}
	
}
