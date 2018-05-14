package pw.xwy.factions.commands.factions.subcommands;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/16/18 5:18 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

import pw.xwy.factions.objects.SubCommand;
import pw.xwy.factions.objects.faction.XPlayer;

public class Leader extends SubCommand {
	public Leader() {
		super("leader", "Make another faction member leader of the faction", "");
	}
	
	@Override
	public void run(XPlayer p, String[] args) {
	
	}
}
