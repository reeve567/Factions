package pw.xwy.factions.commands.factions.subcommands;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/4/18 11:19 AM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

import pw.xwy.factions.enums.ChatType;
import pw.xwy.factions.objects.CommandHandler;
import pw.xwy.factions.objects.SubCommand;
import pw.xwy.factions.objects.faction.XFactionOnlinePlayer;
import pw.xwy.factions.objects.faction.XPlayer;
import pw.xwy.factions.objects.faction.XPlayerFaction;
import pw.xwy.factions.utility.Configurations.Messages;
import pw.xwy.factions.utility.managers.PlayerManager;

@CommandHandler
public class Chat extends SubCommand {
	public Chat() {
		super("c", "<type>", "Change chat mode to communicate with other factions, allies, or your own.");
	}
	
	@Override
	public void run(XPlayer p, String[] args) {
		XPlayerFaction faction = PlayerManager.getOnlinePlayerFaction(p);
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
