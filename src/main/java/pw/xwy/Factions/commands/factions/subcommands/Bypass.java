package pw.xwy.factions.commands.factions.subcommands;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/9/18 11:01 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

import pw.xwy.factions.objects.CommandHandler;
import pw.xwy.factions.objects.SubCommand;
import pw.xwy.factions.objects.faction.XPlayer;

@CommandHandler
public class Bypass extends SubCommand {
	public Bypass() {
		super("bypass", "", "Bypass group permissions and break on other's claims", true);
	}
	
	@Override
	public void run(XPlayer p, String[] args) {
		p.toggleAdminMode();
	}
}
