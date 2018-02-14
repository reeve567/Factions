package pw.xwy.Factions.commands.factions.subcommands;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/4/18 12:05 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

import pw.xwy.Factions.objects.SubCommand;
import pw.xwy.Factions.objects.faction.XPlayerFaction;
import pw.xwy.Factions.objects.faction.XPlayer;
import pw.xwy.Factions.utility.Configurations.Messages;

public class Rename extends SubCommand {
	public Rename() {
		super("rename", "<name>", "Renames your faction");
	}
	
	@Override
	public void run(XPlayer p, String[] args) {
		XPlayerFaction faction = p.getFaction();
		
		if (faction == null) {
			p.sendMessages(Messages.getWhoSender());
			return;
		}
		
		if (!faction.hasPermission(p, "rename")) {
			p.sendMessages(Messages.getNoPermission());
			return;
		}
		
		if (args.length != 2) {
			sendHelpMessage(p);
			return;
		}
		
		if (!XPlayerFaction.validateName(args[1])) {
			p.sendMessage("Invalid faction name");
			return;
		}
		
		// TODO: faction file names need to be renamed to the UUID instead of the faction name
		faction.setName(args[1]);
		faction.sendMessage(String.format("Faction name changed to %s", args[1]));
	}
}


