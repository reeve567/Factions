package pw.xwy.Factions.commands.factions.subcommands;

import org.bukkit.entity.Player;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.utility.managers.PlayerManager;

public class Leave extends SubCommand {
	public Leave() {
		super("leave", "", "&7Leaves your current faction.");
	}
	
	@Override
	public void run(Player p, String[] args) {
		if (PlayerManager.getPlayerFaction(p) != null) {
			if (!PlayerManager.getPlayerFaction(p).getLeader().equals(p.getUniqueId())) {
				PlayerManager.getPlayerFaction(p).leave(PlayerManager.getXPlayer(p),true);
			}
		}
	}
}
