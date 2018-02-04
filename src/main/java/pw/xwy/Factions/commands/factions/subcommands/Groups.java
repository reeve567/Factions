package pw.xwy.Factions.commands.factions.subcommands;

import org.bukkit.entity.Player;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.utility.Configurations.Messages;
import pw.xwy.Factions.utility.inventories.PermissionsMainMenu;
import pw.xwy.Factions.utility.managers.FactionManager;

import java.util.UUID;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class Groups extends SubCommand {
	public Groups() {
		super("groups", "", "Displays groups menu.");
	}
	
	@Override
	public void run(Player p, String[] args) {
		UUID id = p.getUniqueId();
		XFaction faction = FactionManager.getPlayerUUIDFaction(id);
		
		if (faction != null) {
			boolean isLeader = faction.getLeader().equals(id);
			boolean hasPerm = faction.getRole(id).hasPerm("ManagePerms", true);
			
			if (isLeader || hasPerm) {
				p.openInventory(PermissionsMainMenu.get(p, isLeader));
			}
			else {
				p.sendMessage("no permission");
			}
		}
		else {
			Messages.sendMessages(p,Messages.getWhoSender());
		}
	}
}
