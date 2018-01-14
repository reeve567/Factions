package pw.xwy.Factions.commands.factions.subcommands;

import org.bukkit.entity.Player;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.utility.inventories.PermissionsMainMenu;
import pw.xwy.Factions.utility.managers.FactionManager;

import java.util.UUID;

public class Groups extends SubCommand {
	public Groups() {
		super("groups", "", "Displays groups menu.");
	}
	
	@Override
	public void run(Player p, String[] args) {
		UUID id = p.getUniqueId();
		XFaction faction = FactionManager.getUUIDFaction(id);
		boolean isLeader = faction.getLeader().equals(id);
		boolean hasPerm = faction.getRole(id).hasPerm("ManagePerms", true);
		
		if (isLeader || hasPerm) {
			p.openInventory(PermissionsMainMenu.get(p, isLeader));
		}
		
	}
}
