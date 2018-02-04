package pw.xwy.Factions.commands.factions.subcommands;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/4/18 9:03 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

import org.bukkit.entity.Player;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.utility.Configurations.Messages;
import pw.xwy.Factions.utility.managers.PlayerManager;

public class Fly extends SubCommand {
	public Fly() {
		super("fly", "", "Allows you to fly inside faction claim.");
	}
	
	@Override
	public void run(Player p, String[] args) {
		
		XFaction faction = PlayerManager.getPlayerFaction(p);
		if (faction != null) {
			if (faction.hasPermission(p,"fly")) {
				if (faction.claim.isInClaim(p.getLocation().getChunk())) {
					faction.toggleFlying(p);
				}
				else {
					p.sendMessage("you are not in your claim");
				}
			} else {
				p.sendMessage("no permission");
			}
		} else {
			Messages.sendMessages(p, Messages.getWhoSender());
		}
	}
}
