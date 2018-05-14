package pw.xwy.factions.commands.factions.subcommands;

import pw.xwy.factions.objects.CommandHandler;
import pw.xwy.factions.objects.SubCommand;
import pw.xwy.factions.objects.faction.XPlayer;
import pw.xwy.factions.utility.Configurations.Config;
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
public class Power extends SubCommand {
	public Power() {
		super("power", "", "Displays your current power");
	}
	
	@Override
	public void run(XPlayer p, String[] args) {
		p.sendMessage("power: " + PlayerManager.getPlayer(p).getPower() + "/" + Config.maxPower);
		
	}
}
