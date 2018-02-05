package pw.xwy.Factions.commands.factions.subcommands;

import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.objects.XFactionOnlinePlayer;
import pw.xwy.Factions.objects.XPlayer;
import pw.xwy.Factions.utility.StringUtility;
import pw.xwy.Factions.utility.managers.FactionManager;
import pw.xwy.Factions.utility.managers.PlayerManager;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class Join extends SubCommand {
	public Join() {
		super("join", "<faction>", "Accept an invite with this command, and join a faction.");
	}
	
	@Override
	public void run(XPlayer p, String[] args) {
		if (args.length != 2) {
			sendHelpMessage(p);
		} else if (FactionManager.getFactionByName(args[1]) != null) {
			XFaction faction = FactionManager.getFactionByName(args[1]);
			
			if (faction == null) {
				p.sendMessage("Faction doesn't exist");
				return;
			}
			XFactionOnlinePlayer player = PlayerManager.getPlayer(p);
			if (player.hasInvite(faction) && (faction.open)) {
				if (player.getFaction() == null) {
					player.revokeInvite(faction);
					player.setFaction(faction);
					faction.addRecruit(p.getUniqueId());
					p.sendMessage(String.format("Welcome to %s", faction.getName()));
				} else {
					p.sendMessage(StringUtility.conv("&cYou are already in a faction!"));
				}
			}
		}
	}
}
