package pw.xwy.Factions.commands.factions.subcommands;

import org.bukkit.entity.Player;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.utility.managers.FactionManager;

import java.util.UUID;

public class Group extends SubCommand {
	public Group() {
		super("group", "&6<create/delete/set> <name/prefix>", "&7Allows you to create/delete permissions groups for your faction, or set their prefix.");
	}
	
	@Override
	public void run(Player p, String[] args) {
		UUID id = p.getUniqueId();
		XFaction faction = FactionManager.getUUIDFaction(id);
		boolean isLeader = faction.getLeader().equals(id);
		boolean hasPerm = faction.getRole(id).hasPerm("ManagePerms",true);
		
		if (isLeader || hasPerm) {
		
		}
		
	}
}
