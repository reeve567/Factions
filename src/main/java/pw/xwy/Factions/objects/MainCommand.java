package pw.xwy.factions.objects;

import pw.xwy.factions.objects.faction.XPlayer;

import java.util.ArrayList;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/13/18 6:09 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public abstract class MainCommand {
	
	public String command;
	public ArrayList<SubCommand> subCommands = new ArrayList<>();
	public boolean adminCommand = false;
	public String permission;
	
	public MainCommand(String command, String permission, boolean admin) {
		this(command, permission);
		adminCommand = admin;
	}
	
	public MainCommand(String command, String permission) {
		this.command = command;
		this.permission = permission;
		init();
	}
	
	protected void add(SubCommand subCommand) {
		subCommands.add(subCommand);
	}
	
	public abstract void init();
	
	public abstract void run(XPlayer p, String[] args);
}
