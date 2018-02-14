package pw.xwy.Factions.commands.factions.subcommands;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/9/18 3:21 PM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

import org.bukkit.Bukkit;
import pw.xwy.Factions.XFactionsCore;
import pw.xwy.Factions.objects.SubCommand;
import pw.xwy.Factions.objects.faction.XPlayer;

public class Reload extends SubCommand {
	public Reload() {
		super("reload", "", "reload the plugin", true);
	}
	
	@Override
	public void run(XPlayer p, String[] args) {
		p.sendMessage("Reloading, current version is " + XFactionsCore.getXFactionsCore().getDescription().getVersion());
		Bukkit.getPluginManager().enablePlugin(XFactionsCore.getXFactionsCore());
	}
}
