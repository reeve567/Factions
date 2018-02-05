package pw.xwy.Factions.commands.factions.subcommands;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/4/18 11:19 AM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

import org.bukkit.entity.Player;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.enums.ChatType;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.objects.XFactionPlayer;
import pw.xwy.Factions.objects.XPlayer;
import pw.xwy.Factions.utility.Configurations.Messages;
import pw.xwy.Factions.utility.managers.PlayerManager;

public class Chat extends SubCommand {
	public Chat() {
		super("chat", "<type>", "Change chat mode to communicate with other factions, allies, or your own.");
	}
	
	@Override
	public void run(Player p, String[] args) {
		XFaction faction = PlayerManager.getPlayerFaction(p);
		if (faction != null) {
			
			XFactionPlayer player = PlayerManager.getPlayer(p);
			if (args.length != 2) {
				Messages.sendMessages(p, Messages.getCommandHelpFormat(this));
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
			Messages.sendMessages(p, Messages.getWhoSender());
		}
		
		
	}
}
