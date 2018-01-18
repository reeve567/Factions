package pw.xwy.Factions.commands.factions.subcommands;

import org.bukkit.entity.Player;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.utility.Configurations.Messages;
import pw.xwy.Factions.utility.managers.FactionManager;
import pw.xwy.Factions.utility.managers.PlayerManager;

public class Claim extends SubCommand {
	public Claim() {
		super("claim", "[radius]", "Claim area for your faction");
	}
	
	@Override
	public void run(Player p, String[] args) {
		if (p.hasPermission("factions.claim.others")) {
			if (args.length == 1) {
				if (PlayerManager.getPlayerFaction(p) != null) {
					XFaction xFaction = PlayerManager.getPlayerFaction(p);
					if (xFaction.getRole(p.getUniqueId()).hasPerm("claim", true)) {
						xFaction.claim(p.getLocation().getChunk(), 0, p);
					}
				} else {
				
				}
			} else if (args.length == 2) {
				if (PlayerManager.getPlayerFaction(p) != null) {
					XFaction faction = PlayerManager.getPlayerFaction(p);
					if (faction.getRole(p.getUniqueId()).hasPerm("claim", true)) {
						try {
							faction.claim(p.getLocation().getChunk(), Integer.parseInt(args[1]), p);
						} catch (NumberFormatException e) {
							Messages.sendMessages(p, Messages.getCommandHelpFormat(this));
						}
					}
				} else {
				
				}
			} else {
				if (PlayerManager.getPlayerFaction(p) != null) {
					if (Integer.parseInt(args[1]) > 8) {
						System.out.println("too big");
					} else {
						XFaction xFaction = PlayerManager.getPlayerFaction(p);
						if (xFaction.getRole(p.getUniqueId()).hasPerm("claim", true)) {
							xFaction.claim(p.getLocation().getChunk(), Integer.parseInt(args[1]), p);
						} else {
							System.out.println("no perm");
							
						}
					}
				}
			}
			
			if (PlayerManager.getPlayerFaction(p) != null) {
				if (p.hasPermission("factions.claim.others")) {
					if (args.length != 3) {
						p.sendMessage("f claim [radius] <faction>");
					} else {
						XFaction faction = FactionManager.getFactionByName(args[2]);
						if (faction != null) {
							faction.claim(p.getLocation().getChunk(), Integer.parseInt(args[1]), p);
						}
					}
				} else if (PlayerManager.getPlayerFaction(p).getLeader().equals(p.getUniqueId())) {
				
				}
			} else {
				if (p.hasPermission("factions.claim.others")) {
					if (args.length != 3) {
						p.sendMessage("f claim [radius] <faction>");
					} else {
						XFaction faction = FactionManager.getFactionByName(args[2]);
						if (faction != null) {
							faction.claim(p.getLocation().getChunk(), Integer.parseInt(args[1]), p);
						}
					}
				}
			}
		}
	}
}
