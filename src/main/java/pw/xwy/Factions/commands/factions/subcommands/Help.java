package pw.xwy.Factions.commands.factions.subcommands;

import org.bukkit.entity.Player;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.commands.factions.Faction;
import pw.xwy.Factions.utility.Configurations.Config;
import pw.xwy.Factions.utility.Configurations.Messages;

import java.util.ArrayList;
import java.util.HashMap;

public class Help extends SubCommand {
	
	private int perPage;
	private Faction faction;
	
	public Help(Faction faction) {
		super("help", "[page number]", "Displays the help menu on the specified page.");
		
		perPage = Config.commandsPerPage;
		this.faction = faction;
	}
	
	@Override
	public void run(Player p, String[] args) {
		
		if (args.length < 2) {
		
		} else {
			try {
				HashMap<Integer, ArrayList<SubCommand>> pages = new HashMap<>();
				
				int page = Integer.parseInt(args[1]);
				ArrayList<SubCommand> subCommands = (ArrayList<SubCommand>) faction.subCommands.clone();
				ArrayList<SubCommand> remove = new ArrayList<>();
				ArrayList<SubCommand> used = new ArrayList<>();
				
				int cu = 0;
				while (subCommands.size() > 0) {
					cu++;
					for (int i = 0; i < perPage; i++) {
						boolean contin = true;
						for (SubCommand subCommand : subCommands) {
							if (contin) {
								if (!used.contains(subCommand)) {
									if (Config.usePermissions || subCommand.adminCommand) {
										if (p.hasPermission(subCommand.permission)) {
											used.add(subCommand);
											remove.add(subCommand);
											contin = false;
										} else {
											remove.add(subCommand);
											contin = false;
										}
									} else {
										used.add(subCommand);
										remove.add(subCommand);
										contin = false;
									}
								}
							}
						}
					}
					subCommands.removeAll(remove);
					remove = new ArrayList<>();
					pages.put(cu, used);
					used = new ArrayList<>();
				}
				
				Messages.sendMessages(p, Messages.getHeader());
				Messages.sendMessages(p, Messages.getHelpMenuExtra(page, pages.size()));
				for (SubCommand subCommand : pages.get(page)) {
					Messages.sendMessages(p, Messages.getCommandHelpFormat(subCommand));
				}
				Messages.sendMessages(p, Messages.getHelpMenuExtraBottom(page, pages.size()));
				Messages.sendMessages(p, Messages.getFooter());
				
			} catch (NumberFormatException ignored) {
			
			}
			
			
		}
		
	}
}
