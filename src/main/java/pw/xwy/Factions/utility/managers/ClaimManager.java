package pw.xwy.Factions.utility.managers;

import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.utility.StringUtility;

import java.util.ArrayList;
import java.util.HashMap;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class ClaimManager {
	
	public static HashMap<Chunk, XFaction> chunkMap = new HashMap<>();
	
	public static HashMap<XFaction, ArrayList<Chunk>> factionMap = new HashMap<>();
	
	public static XFaction getChunk(Chunk c) {
		return chunkMap.get(c);
	}
	
	public static String getMessage(Chunk c, Player p) {
		if (getChunk(c) != null) {
			String name = getChunk(c).getName();
			if (getChunk(c).getEveryone().contains(p.getUniqueId())) {
				name = StringUtility.conv("&a" + name);
			} else {
				name = StringUtility.conv("&f" + name);
			}
			return StringUtility.conv("&6You are now entering " + name + "&6.");
		} else {
			return StringUtility.conv("&6You are now entering &2Wilderness&6.");
		}
		
	}
	
	public static boolean isClaimed(Chunk c) {
		return chunkMap.get(c) != null;
	}
	
	public static void removeChunks(XFaction f) {
		if (factionMap.containsKey(f)) {
			for (Chunk c : factionMap.get(f)) {
				chunkMap.remove(c);
			}
		}
	}
	
}
