package pw.xwy.Factions.commands.factions.subcommands;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/4/18 11:20 AM                             /
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

public class Close extends SubCommand {
	public Close() {
		super("close", "", "Closes your faction so people can't join");
	}
	
	@Override
	public void run(Player p, String[] args) {
		XFaction faction = PlayerManager.getPlayerFaction(p);
		
		if (faction == null) {
			Messages.sendMessage(p, Messages.getWhoSender());
			return;
		}
		
		if (!faction.hasPermission(p, "toggleOpen")) {
			Messages.sendMessage(p, "no permission");
			return;
		}
		
		faction.open = false;
		
		Messages.sendMessage(p, "Faction is now closed");
	}
}
