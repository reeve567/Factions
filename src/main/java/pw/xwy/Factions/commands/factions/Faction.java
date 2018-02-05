package pw.xwy.Factions.commands.factions;

import org.bukkit.entity.Player;
import pw.xwy.Factions.commands.MainCommand;
import pw.xwy.Factions.commands.factions.subcommands.*;
import pw.xwy.Factions.objects.XPlayer;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class Faction extends MainCommand {
	public Faction() {
		super("f", "Factions.use");
	}
	
	@Override
	public void init() {
		add(new Ally());
		add(new Chat());
		add(new Claim());
		add(new Close());
		add(new Create());
		add(new Disband());
		//add(new Fly());
		add(new Group());
		add(new Groups());
		add(new Help(this));
		add(new Home());
		add(new Invite());
		add(new Join());
		add(new Leave());
		add(new List());
		add(new Map());
		add(new Open());
		add(new Power());
		add(new Rename());
		add(new Sethome());
		add(new Top());
		add(new Unclaim());
		add(new Update());
		//add(new Version());
		add(new Who());
	}
	
	@Override
	public void run(XPlayer p, String[] args) {
	
	}
}
