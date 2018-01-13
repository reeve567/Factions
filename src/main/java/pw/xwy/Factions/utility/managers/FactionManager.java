package pw.xwy.Factions.utility.managers;

import org.bukkit.Chunk;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.utility.Config;

import java.util.ArrayList;
import java.util.UUID;

public class FactionManager {
	
	private static ArrayList<XFaction> factions = new ArrayList<>();
	
	public static void addFaction(XFaction f) {
		factions.add(f);
	}
	
	public static XFaction getUUIDFaction(UUID id) {
		String p = Config.getPlayer(id).getFactionName();
		return getFactionByName(p.substring(p.indexOf(";") + 1, p.length()));
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
		for (XFaction f: factions) {
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
		for (XFaction f: factions) {
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
