package pw.xwy.Factions.commands.factions.subcommands;

import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.objects.XPlayer;
import pw.xwy.Factions.utility.managers.FactionManager;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class List extends SubCommand {
	public List() {
		super("list", "", "List all factions in order of online members");
	}
	
	@Override
	public void run(XPlayer p, String[] args) {
		p.sendMessage(String.valueOf(FactionManager.getFactions()));
	}
}
