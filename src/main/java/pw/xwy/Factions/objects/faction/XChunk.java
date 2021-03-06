package pw.xwy.factions.objects.faction;

import org.bukkit.Chunk;
import org.bukkit.scheduler.BukkitTask;
import pw.xwy.factions.XFactionsCore;
import pw.xwy.factions.utility.tasks.ChunkValueTask;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/13/18 6:24 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class XChunk {
	
	public XFaction xFaction;
	public Chunk chunk;
	public Integer value;
	private BukkitTask task;
	
	XChunk(XFaction xFaction, Chunk c) {
		this.xFaction = xFaction;
		this.chunk = c;
	}
	
	public void calculateValue() {
		if (task != null) {
			try {
				task.cancel();
			} catch (IllegalStateException ignored) {
			}
		}
		value = 0;
		task = new ChunkValueTask(this, true).runTaskTimer(XFactionsCore.getXFactionsCore(), 2, 2);
	}
	
	public boolean isClaimed() {
		return xFaction != null;
	}
	
}
