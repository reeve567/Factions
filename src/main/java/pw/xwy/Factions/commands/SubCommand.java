package pw.xwy.Factions.commands;

import org.bukkit.entity.Player;
import pw.xwy.Factions.utility.Configurations.Messages;
import pw.xwy.Factions.utility.StringUtility;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public abstract class SubCommand {
	
	public String command;
	public String help;
	public String help1;
	public boolean adminCommand = false;
	public String permission;
	
	public SubCommand(String command, String help, String help1) {
		this.command = command;
		this.help = StringUtility.conv(help);
		this.help1 = StringUtility.conv(help1);
		this.permission = "f." + command.toLowerCase();
	}
	
	public SubCommand(String command, String help, String help1, boolean adminCommand) {
		this.adminCommand = adminCommand;
		this.command = command;
		this.help = StringUtility.conv(help);
		this.help1 = StringUtility.conv(help1);
		this.permission = "f." + command.toLowerCase();
	}
	
	public abstract void run(Player p, String[] args);
	
	public void sendHelpMessage(Player p) {
		Messages.sendMessages(p, Messages.getCommandHelpFormat(this));
	}
	
}
