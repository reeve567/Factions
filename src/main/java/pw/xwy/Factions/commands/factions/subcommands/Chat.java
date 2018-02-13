package pw.xwy.Factions.commands.factions.subcommands;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/4/18 11:19 AM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

import pw.xwy.Factions.objects.SubCommand;
import pw.xwy.Factions.enums.ChatType;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.objects.XFactionOnlinePlayer;
import pw.xwy.Factions.objects.XPlayer;
import pw.xwy.Factions.utility.Configurations.Messages;
import pw.xwy.Factions.utility.managers.PlayerManager;

public class Chat extends SubCommand {
	public Chat() {
		super("c", "<type>", "Change chat mode to communicate with other factions, allies, or your own.");
	}
	
	@Override
	public void run(XPlayer p, String[] args) {
		XFaction faction = PlayerManager.getOnlinePlayerFaction(p);
		if (faction != null) {
			XFactionOnlinePlayer player = PlayerManager.getPlayer(p);
			if (args.length != 2) {
				sendHelpMessage(p);
			} else {
				ChatType chatType = ChatType.getType(args[1]);
				if (chatType != null) {
					player.setChatType(chatType);
					p.sendMessage("chat type changed to " + chatType.toString());
				} else {
					p.sendMessage("invalid chat type");
				}
			}
		} else {
			p.sendMessages(Messages.getWhoSender());
		}
		
	}
}
