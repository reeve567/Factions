package pw.xwy.Factions.objects;

import org.bukkit.Chunk;

public class XChunk {
	
	public XFaction xFaction;
	public Chunk chunk;
	public String key;
	
	public XChunk(XFaction xFaction, Chunk c) {
		this.xFaction = xFaction;
		this.chunk = c;
	}
	
	public boolean isClaimed() {
		return xFaction != null;
	}
	
	
}
