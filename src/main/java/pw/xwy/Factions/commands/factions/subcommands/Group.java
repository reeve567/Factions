package pw.xwy.Factions.commands.factions.subcommands;

import org.bukkit.entity.Player;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.objects.XRank;
import pw.xwy.Factions.utility.Messages;
import pw.xwy.Factions.utility.managers.FactionManager;

import java.util.UUID;

public class Group extends SubCommand {
	public Group() {
		super("group", "<create/delete/set> <name value/prefix> [prefix value]", "Allows you to create/delete permissions groups for your faction, or set their prefix.");
	}
	
	@Override
	public void run(Player p, String[] args) {
		UUID id = p.getUniqueId();
		XFaction faction = FactionManager.getUUIDFaction(id);
		boolean isLeader = faction.getLeader().equals(id);
		boolean hasPerm = faction.getRole(id).hasPerm("ManagePerms", true);
		
		if (isLeader || hasPerm) {
			if (args.length < 3) {
				Messages.sendMessages(p,Messages.getCommandHelpFormat(this));
			}
			else if (args.length == 3 && args[1].equalsIgnoreCase("create")) {
				faction.addRank(new XRank(args[2],faction.ranks.size(),faction));
			}
			else if (args.length == 3 && args[1].equalsIgnoreCase("delete")) {
				if (faction.removeRank(args[2])) {
				
				}
			}
			else if (args.length == 3 && args[1].equalsIgnoreCase("set")) {
				Messages.sendMessages(p,Messages.getCommandHelpFormat(this));
			}
			else if (args.length == 3) {
				Messages.sendMessages(p,Messages.getCommandHelpFormat(this));
			}
			else if (args.length == 4 && args[1].equalsIgnoreCase("set") && args[2].equalsIgnoreCase("prefix")) {
			
			}
			else {
				Messages.sendMessages(p,Messages.getCommandHelpFormat(this));
			}
		}
		
	}
}
