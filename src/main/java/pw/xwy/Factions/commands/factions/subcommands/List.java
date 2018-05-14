package pw.xwy.factions.commands.factions.subcommands;

import pw.xwy.factions.objects.CommandHandler;
import pw.xwy.factions.objects.SubCommand;
import pw.xwy.factions.objects.faction.XPlayer;
import pw.xwy.factions.objects.faction.XPlayerFaction;
import pw.xwy.factions.utility.Configurations.Config;
import pw.xwy.factions.utility.Configurations.Messages;
import pw.xwy.factions.utility.managers.FactionManager;

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

@CommandHandler
public class List extends SubCommand {
	private int perPage;
	
	public List() {
		super("list", "", "List all factions in order of online members");
		perPage = Config.commandsPerPage;
	}
	
	private void map(XPlayer p, int page) {
		HashMap<Integer, ArrayList<XPlayerFaction>> pages = new HashMap<>();
		
		ArrayList<XPlayerFaction> factions = (ArrayList<XPlayerFaction>) FactionManager.getMostOnline().clone();
		ArrayList<XPlayerFaction> remove = new ArrayList<>();
		ArrayList<XPlayerFaction> used = new ArrayList<>();
		
		int cu = 0;
		while (factions.size() > 0) {
			cu++;
			for (int i = 0; i < perPage; i++) {
				boolean contin = true;
				for (XPlayerFaction faction : factions) {
					if (contin) {
						if (!used.contains(faction)) {
							used.add(faction);
							remove.add(faction);
							contin = false;
						}
					}
				}
			}
			factions.removeAll(remove);
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
	
	private void send(XPlayer p, HashMap<Integer, ArrayList<XPlayerFaction>> pages, int page) {
		p.sendHeader();
		for (XPlayerFaction faction : pages.get(page)) {
			p.sendMessage(Messages.getFactionListFormat(faction));
		}
		p.sendMessages(Messages.getHelpMenuExtraBottom(page, pages.size()));
		p.sendFooter();
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
}
