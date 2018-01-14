package pw.xwy.Factions.commands.factions.subcommands;

import org.bukkit.entity.Player;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.utility.managers.FactionManager;
import pw.xwy.Factions.utility.managers.PlayerManager;

public class Claim extends SubCommand {
	public Claim() {
		super("claim", "[radius]", "Claim area for your faction");
	}
	
	@Override
	public void run(Player p, String[] args) {
		if (PlayerManager.getPlayerFaction(p) != null) {
			if (p.hasPermission("factions.claim.others")) {
				if (args.length != 3) {
					p.sendMessage("f claim [radius] <faction>");
				} else {
					XFaction faction = FactionManager.getFactionByName(args[2]);
					if (faction != null) {
						faction.claim(p.getLocation().getChunk(), Integer.parseInt(args[1]),p);
					}
				}
			} else if (PlayerManager.getPlayerFaction(p).getLeader().equals(p.getUniqueId())) {
				if (args.length != 2) {
					XFaction xFaction = PlayerManager.getPlayerFaction(p);
					if (xFaction.getRole(p.getUniqueId()).hasPerm("claim", true)) {
						xFaction.claim(p.getLocation().getChunk(), 1,p);
					}
				}
				if (Integer.parseInt(args[1]) > 8) {
				
				} else {
					XFaction xFaction = PlayerManager.getPlayerFaction(p);
					if (xFaction.getRole(p.getUniqueId()).hasPerm("claim", true)) {
						xFaction.claim(p.getLocation().getChunk(), Integer.parseInt(args[1]),p);
					} else {
						//no perm
						
					}
				}
			}
		}
	}
}
