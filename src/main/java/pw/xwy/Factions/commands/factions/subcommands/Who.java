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

import java.util.ArrayList;
import java.util.UUID;

public class Who extends SubCommand {
	
	public Who() {
		super("who", "[player/faction]", "Displays info about either the selected player's faction, the selected faction, or your faction.");
	}
	
	@Override
	public void run(Player p, String[] args) {
		
		if (args.length < 2) {
			display(p, p);
		} else if (FactionManager.getFactionByName(args[1]) != null) {
			XFaction faction = FactionManager.getFactionByName(args[1]);
			assert faction != null;
			display(p, faction);
		} else if (Bukkit.getPlayerExact(args[1]) != null) {
			display(p, Bukkit.getPlayerExact(args[1]).getPlayer());
		} else {
			p.sendMessage(StringUtility.conv("&7This faction/player does not exist."));
		}
		
		
	}
	
	private void display(Player p, Player target) {
		XFaction faction = PlayerManager.getPlayerFaction(target);
		display(p, faction);
	}
	
	private void display(Player p, XFaction faction) {
		Messages.sendMessages(p, Messages.getHeader());
		if (!faction.isSystemFac()) {
			for (String s : Messages.getWhoTop()) {
				p.sendMessage(StringUtility.conv(Messages.replaceFactionValues(s, faction)));
			}
			for (XRank rank: faction.ranks) {
				Messages.sendMessages(p,Messages.getWhoList(rank));
			}
		} else {
			for (String s : Messages.getWhoSystem()) {
				p.sendMessage(StringUtility.conv(Messages.replaceFactionValues(s, faction)));
			}
		}
		Messages.sendMessages(p, Messages.getFooter());
	}
	
	private void recuits(Player p, XFaction target, String prefix) {
		ArrayList<UUID> players = new ArrayList<>();
		players.addAll(target.recruit.users);
		if (players.size() > 0) {
			if (players.size() > 1) {
				String te = "";
				for (UUID uuid : players) {
					te += Bukkit.getPlayer(uuid).getName();
				}
				p.sendMessage(prefix + StringUtility.conv("&7Recruits: &6" + te));
			} else {
				p.sendMessage(prefix + StringUtility.conv("&7Recruit: &6" + Bukkit.getPlayer(target.recruit.users.get(0)).getName()));
			}
		}
	}
	
	private void disp(Player p, XFaction faction) {
	
	}
	
}
