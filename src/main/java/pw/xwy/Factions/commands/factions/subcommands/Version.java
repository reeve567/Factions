package pw.xwy.Factions.commands.factions.subcommands;

import org.bukkit.entity.Player;
import pw.xwy.Factions.XFactionsCore;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.enums.Messages;
import pw.xwy.Factions.utility.StringUtility;

public class Version extends SubCommand {
	public Version() {
		super("version", "", "&7Displays the current version and author.");
	}
	
	@Override
	public void run(Player p, String[] args) {
		p.sendMessage("");
		p.sendMessage(Messages.HEADER.get());
		p.sendMessage(Messages.PREFIX.get() + "Name: " + StringUtility.conv("&6" + XFactionsCore.getXFactionsCore().getDescription().getName()));
		p.sendMessage(Messages.PREFIX.get() + "Version: " + StringUtility.conv("&6" + XFactionsCore.getXFactionsCore().getDescription().getVersion()));
		p.sendMessage(Messages.PREFIX.get() + "Author: " + StringUtility.conv("&6Xwy"));
		p.sendMessage(Messages.PREFIX.get() + "Credits: " + StringUtility.conv("&6MrBorder for ideas"));
		p.sendMessage("");
		p.sendMessage(Messages.HEADER.get());
		p.sendMessage("");
	}
}
