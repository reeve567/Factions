package pw.xwy.Factions.commands.factions.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.objects.XPlayer;
import pw.xwy.Factions.utility.Messages;
import pw.xwy.Factions.utility.StringUtility;
import pw.xwy.Factions.utility.managers.PlayerManager;

public class Invite extends SubCommand {
	
	public Invite() {
		super("invite", "<player>", "Invites the specified player to your faction.");
	}
	
	@Override
	public void run(Player p, String[] args) {
		if (args.length != 2) {
			for (String s : Messages.getCommandHelpFormat(this)) {
				p.sendMessage(s);
			}
		} else if (Bukkit.getPlayer(args[1]) != null) {
			Player player = Bukkit.getPlayer(args[1]);
			XPlayer xPlayer = PlayerManager.getXPlayer(player);
			XFaction xFaction = PlayerManager.getPlayerFaction(p);
			if (!xFaction.equals(xPlayer.getFaction())) {
				if (xPlayer.invite(PlayerManager.getPlayerFaction(p))) {
					p.sendMessage(StringUtility.conv("&aInvite sent!"));
					player.sendMessage(StringUtility.conv("&aYou have been invited to " + xFaction.getName()));
				} else {
					p.sendMessage(StringUtility.conv("&cYou have already invited this player!"));
				}
			} else {
				p.sendMessage(StringUtility.conv("&cThis player is already in your faction!"));
			}
			
		}
		
	}
}