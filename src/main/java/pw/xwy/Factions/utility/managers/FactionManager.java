package pw.xwy.Factions.utility.managers;

import org.bukkit.Chunk;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.utility.Configurations.Config;

import java.util.ArrayList;
import java.util.UUID;

public class FactionManager {
	
	private static ArrayList<XFaction> factions = new ArrayList<>();
	
	public static void addFaction(XFaction f) {
		factions.add(f);
	}
	
	public static XFaction getPlayerUUIDFaction(UUID id) {
		String p = Config.getPlayer(id).getFactionUUID();
		return getFactionFromUUID(p);
	}
	
	private static XFaction getFactionFromUUID(String p) {
		try {
			return getFactionFromUUID(UUID.fromString(p));
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	public static XFaction getFactionFromUUID(UUID id) {
		for (XFaction f : factions) {
			if (f.id.equals(id)) {
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
	
	public static XFaction getFactionByName(String s) {
		for (XFaction f : factions) {
			if (f.getName().equalsIgnoreCase(s)) {
				return f;
			}
		}
		return null;
	}
	
	public static ArrayList<XFaction> getNonSystemFactions() {
		ArrayList<XFaction> nonSystem = new ArrayList<>();
		for (XFaction f : factions) {
			if (!f.isSystemFac()) nonSystem.add(f);
		}
		return nonSystem;
	}
	
	public static ArrayList<XFaction> getSystemFactions() {
		ArrayList<XFaction> system = new ArrayList<>();
		for (XFaction f : factions) {
			if (f.isSystemFac()) system.add(f);
		}
		return system;
	}
	
	public static XFaction findClaim(Chunk c) {
		for (XFaction f : factions) {
			if (f.claim.isInClaim(c)) {
				return f;
			}
		}
		return null;
	}
	
	public static void removeFaction(XFaction faction) {
		if (factions.contains(faction)) {
			factions.remove(faction);
		}
		Config.saveFactions();
	}
	
	public static ArrayList<XFaction> getFactions() {
		return factions;
	}
}
