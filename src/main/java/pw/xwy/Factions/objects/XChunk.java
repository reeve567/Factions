package pw.xwy.Factions.objects;

import org.bukkit.Chunk;
import org.bukkit.scheduler.BukkitTask;
import pw.xwy.Factions.XFactionsCore;
import pw.xwy.Factions.utility.tasks.ChunkValueTask;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
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
	public String key;
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
