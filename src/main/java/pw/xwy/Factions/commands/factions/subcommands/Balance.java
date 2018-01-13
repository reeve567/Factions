package pw.xwy.Factions.commands.factions.subcommands;

import org.bukkit.entity.Player;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.enums.Messages;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.objects.XPlayer;
import pw.xwy.Factions.utility.StringUtility;
import pw.xwy.Factions.utility.managers.PlayerManager;

public class Balance extends SubCommand {
	public Balance() {
		super("balance","","&7Displays the balance menu for depositing, withdrawing, and checking balance");
	}
	
	@Override
	public void run(Player p, String[] args) {
		XPlayer xPlayer = PlayerManager.getXPlayer(p);
		XFaction xFaction = xPlayer.getFaction();
		
		if (args.length == 1) {
			String prefix = Messages.PREFIX.get();
			p.sendMessage("");
			p.sendMessage(Messages.HEADER.get());
			p.sendMessage(prefix + StringUtility.conv("&7Balance: &6" + xFaction.getBalance()));
			p.sendMessage(StringUtility.conv("&8Sub-commands"));
			p.sendMessage(prefix + StringUtility.conv(args[0].toLowerCase() + " deposit &6<amount>"));
			p.sendMessage(prefix + StringUtility.conv(args[0].toLowerCase() + " withdraw &6<amount>"));
			p.sendMessage("");
			p.sendMessage(Messages.HEADER.get());
			p.sendMessage("");
		}
	}
}
