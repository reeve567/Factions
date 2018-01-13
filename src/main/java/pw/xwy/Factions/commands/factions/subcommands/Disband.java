package pw.xwy.Factions.commands.factions.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.objects.XPlayer;
import pw.xwy.Factions.utility.managers.FactionManager;
import pw.xwy.Factions.utility.managers.PlayerManager;

public class Disband extends SubCommand {
	public Disband() {
		super("disband", "", "&7Disband your faction.");
	}
	
	@Override
	public void run(Player p, String[] args) {
		if (p.hasPermission("factions.disband.others")) {
			if (args.length < 2) {
				p.sendMessage("f disband <faction>");
			}
			else {
				XFaction xFaction = FactionManager.getFactionByName(args[1]);
				if (xFaction != null) {
					if (!xFaction.isSystemFac()) {
						xFaction.disband(PlayerManager.getXPlayer(Bukkit.getPlayer(xFaction.getLeader())));
					}
					else {
						xFaction.disband();
					}
				}
			}
		}
		else {
			XPlayer xPlayer = PlayerManager.getXPlayer(p);
			if (xPlayer != null && xPlayer.getFaction() != null) {
				xPlayer.getFaction().disband(xPlayer);
			}
		}
	}
}
