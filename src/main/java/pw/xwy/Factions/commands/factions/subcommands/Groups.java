package pw.xwy.Factions.commands.factions.subcommands;

import pw.xwy.Factions.objects.CommandHandler;
import pw.xwy.Factions.objects.SubCommand;
import pw.xwy.Factions.objects.faction.XPlayer;
import pw.xwy.Factions.objects.faction.XPlayerFaction;
import pw.xwy.Factions.utility.Configurations.Messages;
import pw.xwy.Factions.utility.inventories.PermissionsMainMenu;
import pw.xwy.Factions.utility.managers.PlayerManager;

import java.util.UUID;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

@CommandHandler
public class Groups extends SubCommand {
	public Groups() {
		super("groups", "", "Displays groups menu.");
	}
	
	@Override
	public void run(XPlayer p, String[] args) {
		UUID id = p.getUniqueId();
		XPlayerFaction faction = PlayerManager.getOnlinePlayerFaction(p);
		
		if (faction != null) {
			boolean isLeader = faction.getLeader().equals(id);
			boolean hasPerm = faction.getRole(id).hasPerm("ManagePerms", true);
			
			if (isLeader || hasPerm) {
				p.openInventory(PermissionsMainMenu.get(p, isLeader));
			} else {
				p.sendMessages(Messages.getNoPermission());
				
			}
		} else {
			p.sendMessages(Messages.getWhoSender());
		}
	}
}
