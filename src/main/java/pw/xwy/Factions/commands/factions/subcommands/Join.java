package pw.xwy.Factions.commands.factions.subcommands;

import org.bukkit.entity.Player;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.utility.Messages;
import pw.xwy.Factions.utility.StringUtility;
import pw.xwy.Factions.utility.managers.FactionManager;
import pw.xwy.Factions.utility.managers.PlayerManager;

public class Join extends SubCommand {
	public Join() {
		super("join", "&6<faction>", "&7Accept an invite with this command, and join a faction.");
	}
	
	@Override
	public void run(Player p, String[] args) {
		if (args.length != 2) {
			Messages.sendMessages(p, Messages.getCommandHelpFormat(this));
		} else if (FactionManager.getFactionByName(args[1]) != null) {
			XFaction faction = FactionManager.getFactionByName(args[1]);
			if (PlayerManager.getXPlayer(p).hasInvite(faction)) {
				if (PlayerManager.getXPlayer(p).getFaction() == null) {
					PlayerManager.getXPlayer(p).revokeInvite(faction);
					PlayerManager.getXPlayer(p).setFaction(faction);
					if (faction != null) {
						faction.addRecruit(p.getUniqueId());
					}
				} else {
					p.sendMessage(StringUtility.conv("&cYou are already in a faction!"));
				}
			}
			
		}
	}
}
