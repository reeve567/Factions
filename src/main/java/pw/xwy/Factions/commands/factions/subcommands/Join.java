package pw.xwy.Factions.commands.factions.subcommands;

import org.bukkit.entity.Player;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.objects.XFactionOnlinePlayer;
import pw.xwy.Factions.utility.Configurations.Messages;
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
	public void run(Player p, String[] args) {
		if (args.length != 2) {
			Messages.sendMessages(p, Messages.getCommandHelpFormat(this));
		} else if (FactionManager.getFactionByName(args[1]) != null) {
			XFaction faction = FactionManager.getFactionByName(args[1]);
			
			if (faction == null) {
				Messages.sendMessage(p, "Faction doesn't exist");
				return;
			}
			XFactionOnlinePlayer player = PlayerManager.getPlayer(p);
			if (player.hasInvite(faction) && (faction.open)) {
				if (player.getFaction() == null) {
					player.revokeInvite(faction);
					player.setFaction(faction);
					faction.addRecruit(p.getUniqueId());
					Messages.sendMessage(p, String.format("Welcome to the %s faction", faction.getName()));
				} else {
					Messages.sendMessage(p, StringUtility.conv("&cYou are already in a faction!"));
				}
			}
		}
	}
}
