package pw.xwy.Factions.commands.factions.subcommands;

import org.bukkit.entity.Player;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.utility.managers.ClaimManager;
import pw.xwy.Factions.utility.managers.FactionManager;
import pw.xwy.Factions.utility.managers.PlayerManager;

public class Claim extends SubCommand {
	public Claim() {
		super("claim", "", "&7Claim area for your faction");
	}
	
	@Override
	public void run(Player p, String[] args) {
		if (PlayerManager.getPlayerFaction(p) != null) {
			if (p.hasPermission("factions.claim.others")) {
				if (args.length != 2) {
					p.sendMessage("f claim <faction>");
				}
				else {
					XFaction faction = FactionManager.getFactionByName(args[1]);
					if (faction != null) {
						if (!ClaimManager.isClaimed(p.getLocation().getChunk())) {
							faction.claim(p.getLocation().getChunk());
						}
					}
				}
			} else if (PlayerManager.getPlayerFaction(p).getLeader().equals(p.getUniqueId())) {
				XFaction xFaction = PlayerManager.getPlayerFaction(p);
				if (!ClaimManager.isClaimed(p.getLocation().getChunk())) {
					xFaction.claim(p.getLocation().getChunk());
					
					
				}
			}
		}
	}
}
