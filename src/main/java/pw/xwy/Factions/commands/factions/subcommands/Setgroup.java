package pw.xwy.factions.commands.factions.subcommands;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/16/18 6:28 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

import org.bukkit.Bukkit;
import pw.xwy.factions.objects.SubCommand;
import pw.xwy.factions.objects.faction.XPlayer;
import pw.xwy.factions.objects.faction.XPlayerFaction;
import pw.xwy.factions.objects.faction.XRank;
import pw.xwy.factions.utility.managers.PlayerManager;

import java.util.UUID;

public class Setgroup extends SubCommand {
	public Setgroup() {
		super("setgroup", "<prefix/player-name> <group-name> [prefix-value]", "");
	}
	
	@Override
	public void run(XPlayer p, String[] args) {
		if (p.facCheck()) {
			XPlayerFaction faction = p.getFaction();
			if (p.permCheck("manageperms")) {
				if (args.length == 4 && args[1].equalsIgnoreCase("prefix")) {
					if (faction.getRole(args[2]) != null) {
						faction.getRole(args[2]).prefix = args[3];
						p.sendMessage("prefix set for " + args[2] + " to " + args[3]);
					}
				} else if (args.length == 3 && Bukkit.getPlayer(args[1]) != null) {
					if (faction.getRole(args[2]) != null) {
						UUID idl = PlayerManager.getOfflinePlayerUUID(args[1]);
						if (faction.getEveryone().contains(idl)) {
							if (faction.recruit.getUsers().contains(idl)) {
								faction.recruit.getUsers().remove(idl);
								faction.getRole(args[2]).add(idl);
							} else if (idl.equals(faction.getLeader())) {
								p.sendMessage("You cannot change the leader's rank, in order to change the leader, you must use /f leader <player-name>");
							} else {
								for (XRank rank : faction.ranks) {
									if (rank.isIn(idl)) {
										rank.getUsers().remove(idl);
									}
								}
								faction.getRole(args[2]).add(idl);
							}
						}
					}
				}
			}
		}
	}
}
