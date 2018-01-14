package pw.xwy.Factions.commands.factions.subcommands;

import org.bukkit.entity.Player;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.utility.Messages;
import pw.xwy.Factions.utility.managers.FactionManager;

public class Ally extends SubCommand {
	public Ally() {
		super("ally", "<faction>", "Allows you to send another faction an ally request.");
	}
	
	@Override
	public void run(Player p, String[] args) {
		XFaction faction = FactionManager.getPlayerUUIDFaction(p.getUniqueId());
		if (faction.getRole(p.getUniqueId()).hasPerm("ally",true)) {
			if (args.length < 2) {
				Messages.sendMessages(p,Messages.getCommandHelpFormat(this));
			}
			else {
				XFaction faction1 = FactionManager.getFactionByName(args[1]);
				
				if (faction1 != null) {
					if (!faction.getAllies().contains(faction1)) {
						if (faction.allyRequests.contains(faction1)) {
							//accept ally request from faction1
							faction.addAlly(faction1);
						}
						else {
							//send ally request
							faction.sendAllyRequest(faction1);
							Messages.sendMessages(p,Messages.getAllyRequestRecieved(faction1));
						}
						
					}
					else {
						//already ally
						
					}
				}
				else {
					//not a faction
					
				}
			}
		}
	}
}
