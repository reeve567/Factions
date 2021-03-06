package pw.xwy.factions.commands.factions.subcommands;

import org.bukkit.Chunk;
import pw.xwy.factions.objects.CommandHandler;
import pw.xwy.factions.objects.SubCommand;
import pw.xwy.factions.objects.faction.XFaction;
import pw.xwy.factions.objects.faction.XPlayer;
import pw.xwy.factions.objects.faction.XPlayerFaction;
import pw.xwy.factions.utility.StringUtility;
import pw.xwy.factions.utility.managers.FactionManager;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

@CommandHandler
public class Claim extends SubCommand {
	public Claim() {
		super("claim", "[radius]", "Claim area for your faction");
	}
	
	@Override
	public void run(XPlayer p, String[] args) {
		XPlayerFaction faction;
		Chunk c = p.getLocation().getChunk();
		if (p.permCheck("claim", "f.claim.others")) {
			if (args.length == 1) {
				//claim current chunk
				if (p.facCheck()) {
					faction = p.getFaction();
					faction.claim(c, 0, p);
				}
			} else if (args.length == 2) {
				//claim radius || claim for another faction
				if (args[1].length() > 3) {
					if (p.hasPermission("f.claim.others")) {
						XFaction fac = FactionManager.getFactionByName(args[1]);
						if (fac != null) {
							p.sendMessage("claimed");
							fac.claim(c);
						} else {
							p.sendMessage(StringUtility.conv("&cThat faction does not seem to exist."));
						}
					} else {
						p.sendMessage(StringUtility.conv("&cYou cannot claim for other factions!"));
					}
				} else {
					int size;
					try {
						if (p.facCheck()) {
							faction = p.getFaction();
							size = Integer.parseInt(args[1]);
							faction.claim(c, size, p);
						}
					} catch (NumberFormatException ignored) {
						p.sendMessage(StringUtility.conv("&cInvalid radius!"));
					}
				}
				
			} else if (args.length == 3) {
				//claim radius for another faction
				if (args[1].length() > 3) {
					if (p.hasPermission("f.claim.others")) {
						XFaction fac = FactionManager.getFactionByName(args[1]);
						if (fac != null) {
							p.sendMessage("claimed");
							fac.claim(c, Integer.parseInt(args[2]));
						} else {
							p.sendMessage(StringUtility.conv("&cThat faction does not seem to exist."));
						}
					} else {
						p.sendMessage(StringUtility.conv("&cYou cannot claim for other factions!"));
					}
				} else {
					p.sendMessage("Invalid faction name");
				}
			}
		}
		
	}
}
