package pw.xwy.Factions.commands.factions.subcommands;

import org.bukkit.entity.Player;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.utility.Configurations.Config;
import pw.xwy.Factions.utility.managers.PlayerManager;

public class Power extends SubCommand {
	public Power() {
		super("power", "", "Displays your current power");
	}
	
	@Override
	public void run(Player p, String[] args) {
		p.sendMessage("power: " + PlayerManager.getXPlayer(p).getPower() + "/" + Config.maxPower);
		
		
	}
}
