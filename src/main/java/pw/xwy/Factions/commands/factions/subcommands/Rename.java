package pw.xwy.Factions.commands.factions.subcommands;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/4/18 12:05 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

import org.bukkit.entity.Player;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.utility.Configurations.Messages;
import pw.xwy.Factions.utility.managers.PlayerManager;

public class Rename extends SubCommand {
	public Rename() {
		super("rename", "<name>", "Renames your faction");
	}
	
	@Override
	public void run(Player p, String[] args) {
		XFaction faction = PlayerManager.getPlayerFaction(p);
		
		if (faction == null) {
			Messages.sendMessage(p, Messages.getWhoSender());
			return;
		}
		
		if (!faction.hasPermission(p, "rename")) {
			Messages.sendMessage(p, "no permission");
			return;
		}
		
		if (!XFaction.validateName(args[1])) {
			Messages.sendMessage(p, "Invalid faction name");
			return;
		}
		
		// TODO: faction file names need to be renamed to the UUID instead of the faction name
		faction.setName(args[1]);
		
		Messages.sendMessage(faction, String.format("Faction name changed to %s", args[1]));
	}
}


