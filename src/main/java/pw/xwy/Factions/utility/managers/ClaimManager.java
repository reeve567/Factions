package pw.xwy.Factions.utility.managers;

import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.objects.faction.XFactionOnlinePlayer;
import pw.xwy.Factions.objects.faction.XPlayer;
import pw.xwy.Factions.objects.faction.XPlayerFaction;
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

public class ClaimManager implements Manager {
	
	public static HashMap<Chunk, XFaction> chunkMap;
	
	public static HashMap<XFaction, ArrayList<Chunk>> factionMap;
	
	public static String getMessage(Chunk c, XPlayer p) {
		XFaction faction = getChunk(c);
		if (faction != null) {
			String name = faction.getName();
			String desc = faction.getDesc();
			if (getChunk(c).equals(p.getFaction())) {
				name = StringUtility.conv("&a" + name);
			} else {
				name = StringUtility.conv("&f" + name);
			}
			return StringUtility.conv("&6You are now entering &" + faction.getColor() + name + " &6- &" + faction.getColor() + desc + "&6.");
		} else {
			return StringUtility.conv("&6You are now entering &2Wilderness&6.");
		}
		
	}
	
	public static XFaction getChunk(Chunk c) {
		return chunkMap.get(c);
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
	
	@Override
	public void unload() {
		chunkMap = null;
		factionMap = null;
	}
	
	@Override
	public void load() {
		chunkMap = new HashMap<>();
		factionMap = new HashMap<>();
	}
}
