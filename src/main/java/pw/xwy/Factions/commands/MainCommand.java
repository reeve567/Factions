package pw.xwy.Factions.commands;

import java.util.ArrayList;

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
	
	public abstract void init();
	
	protected void add(SubCommand subCommand) {
		subCommands.add(subCommand);
	}
	
}
