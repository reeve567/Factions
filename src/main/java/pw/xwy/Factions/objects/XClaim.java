package pw.xwy.Factions.objects;

import org.bukkit.Chunk;
import pw.xwy.Factions.XFactionsCore;
import pw.xwy.Factions.utility.managers.ClaimManager;
import pw.xwy.Factions.utility.tasks.ChunkValueTask;

import java.util.ArrayList;
import java.util.HashSet;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class XClaim {
	
	private ArrayList<Chunk> claim = new ArrayList<>();
	public HashSet<XChunk> chunks = new HashSet<>();
	
	public void add(Chunk c, XFaction faction) {
		claim.add(c);
		XChunk chunk = new XChunk(faction,c);
		chunks.add(chunk);
		chunk.calculateValue();
		ClaimManager.factionMap.put(faction, claim);
		ClaimManager.chunkMap.put(c, faction);
	}
	
	public ArrayList<Chunk> get() {
		return claim;
	}
	
	public boolean isInClaim(Chunk c) {
		return claim.contains(c);
	}
	
	public boolean isNextToClaim(Chunk c) {
		int x = c.getX();
		int z = c.getX();
		for (Chunk ch : claim) {
			if (c.getWorld().equals(ch.getWorld())) {
				int xc = ch.getX();
				int zc = ch.getZ();
				if ((xc - x == 1 || xc - x == -1) && (zc - z == 1 || zc - z == -1)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public XChunk getChunk(Chunk c) {
		for (XChunk ch : chunks) {
			if (ch.chunk.equals(c)) {
				return ch;
			}
		}
		return null;
	}
	
	public void remove(Chunk c, XFaction faction) {
		XChunk xChunk = getChunk(c);
		claim.remove(c);
		ClaimManager.factionMap.put(faction, claim);
		ClaimManager.chunkMap.remove(c);
		chunks.remove(xChunk);
	}
	
}
