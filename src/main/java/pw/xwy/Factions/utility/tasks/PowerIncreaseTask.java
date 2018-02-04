package pw.xwy.Factions.utility.tasks;

import org.bukkit.scheduler.BukkitRunnable;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.objects.XPlayer;
import pw.xwy.Factions.utility.managers.PlayerManager;

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
	
	private ArrayList<XFaction> factions = new ArrayList<>();
	
	public PowerIncreaseTask() {
		System.out.println(getClass().getSimpleName() + " has been started!");
	}
	
	@Override
	public void run() {
		//increase .1 every 1 min
		for (XPlayer p : PlayerManager.getPlayers()) {
			p.addPower();
			if (!factions.contains(p.getFaction())) {
				factions.add(p.getFaction());
			}
		}
		for (XFaction faction : factions) {
			if (faction != null) {
				faction.updatePower();
			}
		}
		factions = new ArrayList<>();
		
	}
}
