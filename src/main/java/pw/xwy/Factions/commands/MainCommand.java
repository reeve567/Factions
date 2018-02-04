package pw.xwy.Factions.commands;

import org.bukkit.entity.Player;

import java.util.ArrayList;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
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
	
	public abstract void run(Player p, String[] args);
}
