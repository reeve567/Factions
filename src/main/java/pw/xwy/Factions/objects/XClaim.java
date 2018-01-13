package pw.xwy.Factions.objects;

import org.bukkit.Chunk;
import pw.xwy.Factions.commands.factions.subcommands.Claim;
import pw.xwy.Factions.utility.managers.ClaimManager;

import java.util.ArrayList;

public class XClaim {
	
	private ArrayList<Chunk> claim = new ArrayList<>();
	
	public void add(Chunk c, XFaction faction) {
		if (ClaimManager.factionMap.containsKey(faction)) {
			ArrayList<Chunk> temp = new ArrayList<>();
			temp.add(c);
			ClaimManager.factionMap.put(faction,temp);
		}
		else {
			ArrayList<Chunk> temp = new ArrayList<>();
			temp.add(c);
			ClaimManager.factionMap.put(faction,temp);
		}
		
		ClaimManager.chunkMap.put(c,faction);
		claim.add(c);
	}
	
	public boolean isInClaim(Chunk c) {
		return claim.contains(c);
	}
	
	public boolean isNextToClaim(Chunk c) {
		int x = c.getX();
		int z = c.getX();
		for (Chunk ch: claim) {
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
	
	public ArrayList<Chunk> get() {
		return claim;
	}
}
