package pw.xwy.Factions.commands.factions.subcommands;

import pw.xwy.Factions.objects.SubCommand;
import pw.xwy.Factions.commands.factions.Faction;
import pw.xwy.Factions.objects.XPlayer;
import pw.xwy.Factions.utility.Configurations.Config;
import pw.xwy.Factions.utility.Configurations.Messages;

import java.util.ArrayList;
import java.util.HashMap;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class Help extends SubCommand {
	
	private int perPage;
	private Faction faction;
	
	public Help(Faction faction) {
		super("help", "[page number]", "Displays the help menu on the specified page.");
		
		perPage = Config.commandsPerPage;
		this.faction = faction;
	}
	
	@Override
	public void run(XPlayer p, String[] args) {
		
		if (args.length < 2) {
			map(p, 1);
		} else {
			try {
				int page = Integer.parseInt(args[1]);
				map(p, page);
			} catch (NumberFormatException e) {
				p.sendMessage("invalid page");
			}
		}
	}
	
	private void map(XPlayer p, int page) {
		HashMap<Integer, ArrayList<SubCommand>> pages = new HashMap<>();
		
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
									contin = true;
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
		
		if (page < pages.size()) {
			send(p, pages, page);
		} else {
			send(p, pages, cu);
		}
		
	}
	
	private void send(XPlayer p, HashMap<Integer, ArrayList<SubCommand>> pages, int page) {
		p.sendHeader();
		p.sendMessages(Messages.getHelpMenuExtra(page, pages.size()));
		for (SubCommand subCommand : pages.get(page)) {
			p.sendMessages(Messages.getCommandHelpFormat(subCommand));
		}
		p.sendMessages(Messages.getHelpMenuExtraBottom(page, pages.size()));
		p.sendFooter();
	}
	
}
