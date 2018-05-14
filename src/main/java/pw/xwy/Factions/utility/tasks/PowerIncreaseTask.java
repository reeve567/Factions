package pw.xwy.factions.utility.tasks;

import org.bukkit.scheduler.BukkitRunnable;
import pw.xwy.factions.objects.faction.XFactionOnlinePlayer;
import pw.xwy.factions.objects.faction.XPlayerFaction;
import pw.xwy.factions.utility.managers.PlayerManager;

import java.util.ArrayList;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class PowerIncreaseTask extends BukkitRunnable {
	
	private ArrayList<XPlayerFaction> factions = new ArrayList<>();
	
	public PowerIncreaseTask() {
		System.out.println(getClass().getSimpleName() + " has been started!");
	}
	
	@Override
	public void run() {
		//increase .1 every 1 min
		for (XFactionOnlinePlayer p : PlayerManager.getOnlinePlayers()) {
			p.addPower();
			if (!factions.contains(p.getFaction())) {
				factions.add(p.getFaction());
			}
		}
		for (XPlayerFaction faction : factions) {
			if (faction != null) {
				faction.updatePower();
			}
		}
		factions = new ArrayList<>();
		
	}
}
