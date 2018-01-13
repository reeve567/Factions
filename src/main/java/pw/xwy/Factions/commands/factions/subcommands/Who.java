package pw.xwy.Factions.commands.factions.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.enums.Messages;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.utility.Config;
import pw.xwy.Factions.utility.StringUtil;
import pw.xwy.Factions.utility.StringUtility;
import pw.xwy.Factions.utility.managers.FactionManager;
import pw.xwy.Factions.utility.managers.PlayerManager;

import java.util.ArrayList;
import java.util.UUID;

public class Who extends SubCommand {
	
	public Who() {
		super("who", "&6[player/faction]", "&7Displays info about either the selected player's faction, the selected faction, or your faction.");
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
			display(p,Bukkit.getPlayerExact(args[1]).getPlayer());
		}
		else {
			p.sendMessage(StringUtility.conv("&7This faction/player does not exist."));
		}
		
		
	}
	
	private void display(Player p, Player target) {
		String prefix = Messages.PREFIX.get();
		p.sendMessage("");
		p.sendMessage(Messages.HEADER.get());
		XFaction faction = PlayerManager.getPlayerFaction(target);
		if (faction != null) {
			p.sendMessage(prefix + StringUtility.conv("&7Name: &6" + faction.getName()));
			if (!faction.isSystemFac()) {
				Player leader = Bukkit.getPlayer(faction.getLeader());
				p.sendMessage(prefix + StringUtility.conv("&7Power: &6" + faction.getPower() + "/" + faction.getEveryone().size()* Config.maxPower));
				p.sendMessage(prefix + StringUtility.conv("&7Balance: &6" + faction.getBalance()));
				p.sendMessage(prefix + StringUtility.conv("&7Players Online: &6" + faction.getOnlinePlayers()));
				p.sendMessage(prefix + StringUtility.conv("&7Leader: &6" + leader.getName()));
				recuits(p,faction,prefix);
			}
		} else {
			if (p.equals(target)) {
				p.sendMessage(prefix + "You are not in a faction.");
			}
			else {
				p.sendMessage(prefix + "They are not in a faction.");
			}
		}
		p.sendMessage("");
		p.sendMessage(Messages.HEADER.get());
		p.sendMessage("");
	}
	
	private void display(Player p, XFaction faction) {
		String prefix = Messages.PREFIX.get();
		p.sendMessage("");
		p.sendMessage(Messages.HEADER.get());
		p.sendMessage(prefix + StringUtility.conv("&7Name: &6" + faction.getName()));
		if (!faction.isSystemFac()) {
			Player leader = Bukkit.getPlayer(faction.getLeader());
			p.sendMessage(prefix + StringUtility.conv("&7Power: &6" + faction.getPower() + "/" + faction.getEveryone().size()* Config.maxPower));
			p.sendMessage(prefix + StringUtility.conv("&7Balance: &6" + faction.getBalance()));
			p.sendMessage(prefix + StringUtility.conv("&7Players Online: &6" + faction.getOnlinePlayers()));
			p.sendMessage(prefix + StringUtility.conv("&7Faction groups:"));
			p.sendMessage(prefix + StringUtility.conv("&7Leader: &6" + leader.getName()));
			
			recuits(p,faction,prefix);
		}
		p.sendMessage("");
		p.sendMessage(Messages.HEADER.get());
		p.sendMessage("");
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
	
}
