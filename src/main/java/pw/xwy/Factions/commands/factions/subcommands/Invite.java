package pw.xwy.Factions.commands.factions.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.objects.XFactionOnlinePlayer;
import pw.xwy.Factions.objects.XFactionPlayer;
import pw.xwy.Factions.objects.XPlayer;
import pw.xwy.Factions.utility.Configurations.Messages;
import pw.xwy.Factions.utility.StringUtility;
import pw.xwy.Factions.utility.managers.PlayerManager;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class Invite extends SubCommand {
	
	public Invite() {
		super("invite", "<player>", "Invites the specified player to your faction.");
	}
	
	@Override
	public void run(XPlayer p, String[] args) {
		if (args.length != 2) {
			for (String s : Messages.getCommandHelpFormat(this)) {
				p.sendMessage(s);
			}
		} else if (Bukkit.getPlayer(args[1]) != null) {
			Player player = Bukkit.getPlayer(args[1]);
			XFactionOnlinePlayer xPlayer = PlayerManager.getPlayer(player);
			XFaction xFaction = PlayerManager.getOnlinePlayerFaction(p);
			if (!xFaction.equals(xPlayer.getFaction())) {
				if (xPlayer.invite(PlayerManager.getOnlinePlayerFaction(p))) {
					p.sendMessage(StringUtility.conv("&aInvite sent!"));
					player.sendMessage(StringUtility.conv("&aYou have been invited to " + xFaction.getName()));
				} else {
					p.sendMessage(StringUtility.conv("&cYou have already invited this player!"));
				}
			} else {
				p.sendMessage(StringUtility.conv("&cThis player is already in your faction!"));
			}
			
		}
		
	}
}