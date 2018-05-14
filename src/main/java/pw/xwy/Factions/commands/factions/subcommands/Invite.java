package pw.xwy.factions.commands.factions.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pw.xwy.factions.objects.CommandHandler;
import pw.xwy.factions.objects.SubCommand;
import pw.xwy.factions.objects.faction.XFactionOnlinePlayer;
import pw.xwy.factions.objects.faction.XPlayer;
import pw.xwy.factions.objects.faction.XPlayerFaction;
import pw.xwy.factions.utility.StringUtility;
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
public class Invite extends SubCommand {
	
	public Invite() {
		super("invite", "<player>", "Invites the specified player to your faction.");
	}
	
	@Override
	public void run(XPlayer p, String[] args) {
		if (args.length != 2) {
			sendHelpMessage(p);
		} else if (Bukkit.getPlayer(args[1]) != null) {
			Player player = Bukkit.getPlayer(args[1]);
			XFactionOnlinePlayer xPlayer = PlayerManager.getPlayer(player);
			if (xPlayer != null) {
				XPlayerFaction senderFaction = p.getFaction();
				if (senderFaction != null) {
					if (xPlayer.getFaction() == null || !senderFaction.equals(xPlayer.getFaction())) {
						if (xPlayer.invite(PlayerManager.getOnlinePlayerFaction(p))) {
							p.sendMessage(StringUtility.conv("&aInvite sent!"));
							player.sendMessage(StringUtility.conv("&aYou have been invited to " + senderFaction.getName()));
						} else {
							p.sendMessage(StringUtility.conv("&cYou have already invited this player!"));
						}
					} else {
						p.sendMessage(StringUtility.conv("&cThis player is already in your faction!"));
					}
				} else {
					p.sendMessage("you are not in a faction");
				}
			} else {
				p.sendMessage("player not found");
			}
			
		}
	}
}