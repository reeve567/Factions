package pw.xwy.Factions.commands.factions.subcommands;

import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.objects.XFaction;
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

public class Claim extends SubCommand {
	public Claim() {
		super("claim", "[radius]", "Claim area for your faction");
	}
	
	private boolean hasPerm(XFaction faction, Player p) {
		return faction.hasPermission(p,"claim","factions.claim.others");
	}
	
	@Override
	public void run(Player p, String[] args) {
		XFaction faction;
		Chunk c = p.getLocation().getChunk();
		//is in faction
		if ((faction = PlayerManager.getPlayerFaction(p)) != null) {
			
			if (hasPerm(faction, p)) {
				
				if (args.length == 1) {
					//claim current chunk
					faction.claim(c, 0, p);
					
				} else if (args.length == 2) {
					//claim radius || claim for another faction
					if (args[1].length() > 3) {
						if (p.hasPermission("factions.claim.others")) {
							XFaction fac = FactionManager.getFactionByName(args[1]);
							if (fac != null) {
								faction.claim(c, 0, true);
							} else {
								p.sendMessage(StringUtility.conv("&cThat faction does not seem to exist."));
							}
							
							
						} else {
							p.sendMessage(StringUtility.conv("&cYou cannot claim for other factions!"));
						}
						
					} else {
						int size;
						try {
							size = Integer.parseInt(args[1]);
							faction.claim(c, size, p);
						} catch (NumberFormatException ignored) {
							p.sendMessage(StringUtility.conv("&cInvalid radius!"));
						}
					}
					
				} else if (args.length == 3) {
					//claim radius for another faction
					
					
				}
			} else {
				p.sendMessage("no perm");
			}
		} else {
			p.sendMessage("not in faction");
		}
	}
}
