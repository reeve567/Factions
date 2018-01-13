package pw.xwy.Factions.utility.tasks;

import org.bukkit.scheduler.BukkitRunnable;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.objects.XPlayer;
import pw.xwy.Factions.utility.managers.PlayerManager;

import java.util.ArrayList;

public class PowerIncreaseTask extends BukkitRunnable {
	
	public PowerIncreaseTask() {
		System.out.println(getClass().getSimpleName() + " has been started!");
	}
	
	private ArrayList<XFaction> factions = new ArrayList<>();
	
	@Override
	public void run() {
		//increase .1 every 1 min
		for (XPlayer p : PlayerManager.getPlayers()) {
			p.addPower();
			if (!factions.contains(p.getFaction())) {
				factions.add(p.getFaction());
			}
		}
		for (XFaction faction: factions) {
			if (faction != null) {
				faction.updatePower();
			}
		}
		factions = new ArrayList<>();
		
	}
}
