package pw.xwy.factions.commands.factions.subcommands;

import pw.xwy.factions.objects.CommandHandler;
import pw.xwy.factions.objects.SubCommand;
import pw.xwy.factions.objects.faction.XFaction;
import pw.xwy.factions.objects.faction.XFactionOnlinePlayer;
import pw.xwy.factions.objects.faction.XPlayer;
import pw.xwy.factions.objects.faction.XPlayerFaction;
import pw.xwy.factions.utility.StringUtility;
import pw.xwy.factions.utility.managers.FactionManager;
import pw.xwy.factions.utility.managers.PlayerManager;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

@CommandHandler
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
			if (!(faction instanceof XPlayerFaction)) {
				p.sendMessage("thats a system faction!");
				return;
			}
			XFactionOnlinePlayer player = PlayerManager.getPlayer(p);
			if (player.hasInvite((XPlayerFaction) faction) || (((XPlayerFaction) faction).open) || p.isAdminMode()) {
				if (player.getFaction() == null) {
					player.revokeInvite((XPlayerFaction) faction);
					player.setFaction((XPlayerFaction) faction);
					((XPlayerFaction) faction).addRecruit(p.getUniqueId());
					p.sendMessage(String.format("Welcome to %s", faction.getName()));
				} else {
					p.sendMessage(StringUtility.conv("&cYou are already in a faction!"));
				}
			}
		}
	}
}
