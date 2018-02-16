package pw.xwy.Factions.commands.factions.subcommands;

import pw.xwy.Factions.objects.CommandHandler;
import pw.xwy.Factions.objects.SubCommand;
import pw.xwy.Factions.objects.faction.XPlayer;
import pw.xwy.Factions.objects.faction.XPlayerFaction;
import pw.xwy.Factions.objects.faction.XRank;
import pw.xwy.Factions.utility.managers.PlayerManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

@CommandHandler
public class Group extends SubCommand {
	public Group() {
		super("group", "<create/delete/set/add> <group-name/prefix> [prefix value/group-name/player-name] [group-name]", "Allows you to create/delete permissions groups for your faction, or set their prefix.");
	}
	
	@Override
	public void run(XPlayer p, String[] args) {
		UUID id = p.getUniqueId();
		XPlayerFaction faction = PlayerManager.getOnlinePlayerFaction(p);
		if (faction != null) {
			boolean isLeader = faction.getLeader().equals(id);
			boolean hasPerm = faction.getRole(id).hasPerm("ManagePerms", true);
			
			if (isLeader || hasPerm) {
				if (args.length < 3) {
					sendHelpMessage(p);
				} else if (args.length == 3 && args[1].equalsIgnoreCase("create")) {
					List<Character> chars = new ArrayList<>();
					for (int i = 97; i < 123; i++) {
						chars.add((char) i);
					}
					for (int i = 65; i < 133; i++) {
						chars.add((char) i);
					}
					for (int i = 48; i < 58; i++) {
						chars.add((char) i);
					}
					boolean found = false;
					for (char c : chars) {
						if (!chars.contains(c)) found = true;
					}
					if (!found) {
						faction.addRank(new XRank(args[2], faction.ranks.size(), faction, false));
					} else {
						p.sendMessage("found a rank already");
					}
				} else if (args.length == 3 && args[1].equalsIgnoreCase("delete")) {
					if (faction.removeRank(args[2])) {
						p.sendMessage("rank removed");
					}
					p.sendMessage("rank not removed");
				} else if (args.length == 5 && args[1].equalsIgnoreCase("set") && args[2].equalsIgnoreCase("prefix")) {
				
				} else if (args.length == 4 && args[1].equalsIgnoreCase("add")) {
					if (faction.getRole(args[2]) != null) {
						UUID idl = PlayerManager.getOfflinePlayerUUID(args[3]);
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
				} else {
					sendHelpMessage(p);
				}
			}
		} else {
			p.sendMessage("not in faction");
		}
		
	}
}
