package pw.xwy.Factions.commands.factions.subcommands;

import org.bukkit.entity.Player;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.utility.managers.FactionManager;

import java.util.UUID;

public class Groups extends SubCommand {
	public Groups() {
		super("groups", "", "&7Displays groups menu.");
	}
	
	@Override
	public void run(Player p, String[] args) {
		UUID id = p.getUniqueId();
		XFaction faction = FactionManager.getUUIDFaction(id);
		if (faction.getLeader().equals(id) || faction.getRole(id).hasPerm("ManagePerms",true)) {
		
		}
		
		
	}
}
