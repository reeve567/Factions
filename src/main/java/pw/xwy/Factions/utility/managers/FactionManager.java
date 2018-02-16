package pw.xwy.Factions.utility.managers;

import org.bukkit.Chunk;
import pw.xwy.Factions.objects.faction.XFaction;
import pw.xwy.Factions.objects.faction.XPlayerFaction;
import pw.xwy.Factions.utility.Configurations.Config;

import java.util.ArrayList;
import java.util.UUID;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class FactionManager implements Manager {
	
	private static ArrayList<XFaction> factions;
	
	public static void addFaction(XFaction f) {
		factions.add(f);
	}
	
	public static XFaction findClaim(Chunk c) {
		for (XFaction f : factions) {
			if (f.claim.isInClaim(c)) {
				return f;
			}
		}
		return null;
	}
	
	public static UUID getAvailableUUID() {
		UUID id;
		do {
			id = UUID.randomUUID();
		}
		while (getFactionFromUUID(id) != null);
		return id;
	}
	
	public static XPlayerFaction getFactionFromUUID(UUID id) {
		if (id != null) {
			for (XFaction f : factions) {
				if (f.getId().equals(id)) {
					return (XPlayerFaction) f;
				}
			}
		}
		return null;
	}
	
	public static XFaction getFactionByName(String s) {
		for (XFaction f : factions) {
			if (f.getName().equalsIgnoreCase(s)) {
				return f;
			}
		}
		return null;
	}
	
	public static ArrayList<XPlayerFaction> getMostValueble() {
		ArrayList<XPlayerFaction> factions = new ArrayList<>();
		ArrayList<XPlayerFaction> temp = getNonSystemFactions();
		for (XPlayerFaction faction : temp) {
			boolean found = false;
			for (XPlayerFaction faction1 : factions) {
				if (faction.getValue() > faction1.getValue()) {
					found = true;
					factions.add(factions.indexOf(faction1), faction);
					break;
				}
			}
			if (!found) {
				factions.add(faction);
			}
			
		}
		
		return factions;
	}
	
	public static ArrayList<XPlayerFaction> getNonSystemFactions() {
		ArrayList<XPlayerFaction> nonSystem = new ArrayList<>();
		for (XFaction f : factions) {
			if (f instanceof XPlayerFaction) {
				nonSystem.add((XPlayerFaction) f);
			}
		}
		return nonSystem;
	}
	
	public static ArrayList<XFaction> getFactions() {
		return factions;
	}
	
	public static ArrayList<XFaction> getSystemFactions() {
		ArrayList<XFaction> system = new ArrayList<>();
		for (XFaction f : factions) {
			if (!(f instanceof XPlayerFaction)) system.add(f);
		}
		return system;
	}
	
	public static void removeFaction(XFaction faction) {
		if (factions.contains(faction)) {
			factions.remove(faction);
		}
		System.gc();
		Config.saveFactions();
	}
	
	@Override
	public void unload() {
		factions = null;
		
	}
	
	@Override
	public void load() {
		factions = new ArrayList<>();
	}
}
