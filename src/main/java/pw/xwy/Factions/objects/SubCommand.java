package pw.xwy.factions.objects;

import pw.xwy.factions.objects.faction.XPlayer;
import pw.xwy.factions.utility.Configurations.Messages;
import pw.xwy.factions.utility.StringUtility;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/13/18 6:09 PM                             /
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
	
	protected void sendHelpMessage(XPlayer p) {
		p.sendMessages(Messages.getCommandHelpFormat(this));
	}
	
	public abstract void run(XPlayer p, String[] args);
	
}
