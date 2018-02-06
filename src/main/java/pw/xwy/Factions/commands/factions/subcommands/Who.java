package pw.xwy.Factions.commands.factions.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.objects.XPlayer;
import pw.xwy.Factions.objects.XRank;
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

public class Who extends SubCommand {
	
	public Who() {
		super("who", "[player/faction]", "Displays info about either the selected player's faction, the selected faction, or your faction.");
	}
	
	private void display(XPlayer p, Player target) {
		XFaction faction = PlayerManager.getOnlinePlayerFaction(target);
		display(p, faction, true);
	}
	
	private void display(XPlayer p, XFaction faction, boolean someoneElse) {
		p.sendHeader();
		if (faction != null) {
			if (!faction.isSystemFac()) {
				for (String s : Messages.getWhoTop()) {
					p.sendMessage(StringUtility.conv(Messages.replaceFactionValues(s, faction)));
				}
				for (XRank rank : faction.ranks) {
					p.sendMessages(Messages.getWhoList(rank));
				}
				p.sendMessages(Messages.getWhoList(faction.recruit));
			} else {
				for (String s : Messages.getWhoSystem()) {
					p.sendMessage(StringUtility.conv(Messages.replaceFactionValues(s, faction)));
				}
			}
		} else {
			if (someoneElse) {
				p.sendMessages(Messages.getWhoTarget());
			} else {
				p.sendMessages(Messages.getWhoSender());
			}
		}
		p.sendFooter();
	}
	
	@Override
	public void run(XPlayer p, String[] args) {
		
		if (args.length < 2) {
			display(p, XPlayer.getXPlayer(p).getFaction(), false);
		} else if (FactionManager.getFactionByName(args[1]) != null) {
			XFaction faction = FactionManager.getFactionByName(args[1]);
			assert faction != null;
			display(p, faction, true);
		} else if (Bukkit.getPlayerExact(args[1]) != null) {
			display(p, Bukkit.getPlayerExact(args[1]).getPlayer());
		} else {
			p.sendMessage(StringUtility.conv("&7This faction/player does not exist."));
		}
		
	}
	
}
