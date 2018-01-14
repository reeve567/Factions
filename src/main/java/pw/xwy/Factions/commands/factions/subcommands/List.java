package pw.xwy.Factions.commands.factions.subcommands;

import org.bukkit.entity.Player;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.utility.managers.FactionManager;

public class List extends SubCommand {
	public List() {
		super("list", "", "List all factions in order of online members");
	}
	
	@Override
	public void run(Player p, String[] args) {
		p.sendMessage(String.valueOf(FactionManager.getFactions()));
	}
}
