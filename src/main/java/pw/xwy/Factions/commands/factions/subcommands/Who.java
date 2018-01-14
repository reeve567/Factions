package pw.xwy.Factions.commands.factions.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.objects.XRank;
import pw.xwy.Factions.utility.Messages;
import pw.xwy.Factions.utility.StringUtility;
import pw.xwy.Factions.utility.managers.FactionManager;
import pw.xwy.Factions.utility.managers.PlayerManager;

public class Who extends SubCommand {
	
	public Who() {
		super("who", "[player/faction]", "Displays info about either the selected player's faction, the selected faction, or your faction.");
	}
	
	@Override
	public void run(Player p, String[] args) {
		
		if (args.length < 2) {
			display(p, FactionManager.getPlayerUUIDFaction(p.getUniqueId()),false);
		} else if (FactionManager.getFactionByName(args[1]) != null) {
			XFaction faction = FactionManager.getFactionByName(args[1]);
			assert faction != null;
			display(p, faction, true);
		} else if (Bukkit.getPlayerExact(args[1]) != null) {
			display(p, Bukkit.getPlayerExact(args[1]).getPlayer());
		} else {
			p.sendMessage(StringUtility.conv("&7This faction/player does not exist."));
		}
		
		
	}
	
	private void display(Player p, Player target) {
		XFaction faction = PlayerManager.getPlayerFaction(target);
		display(p, faction, true);
	}
	
	private void display(Player p, XFaction faction, boolean someoneElse) {
		Messages.sendMessages(p, Messages.getHeader());
		if (faction != null) {
			if (!faction.isSystemFac()) {
				for (String s : Messages.getWhoTop()) {
					p.sendMessage(StringUtility.conv(Messages.replaceFactionValues(s, faction)));
				}
				for (XRank rank : faction.ranks) {
					Messages.sendMessages(p, Messages.getWhoList(rank));
				}
				Messages.sendMessages(p, Messages.getWhoList(faction.recruit));
			} else {
				for (String s : Messages.getWhoSystem()) {
					p.sendMessage(StringUtility.conv(Messages.replaceFactionValues(s, faction)));
				}
			}
		} else {
			if (someoneElse) {
				Messages.sendMessages(p, Messages.getWhoTarget());
			} else {
				Messages.sendMessages(p, Messages.getWhoSender());
			}
		}
		Messages.sendMessages(p, Messages.getFooter());
	}
	
	
}
