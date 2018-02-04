package pw.xwy.Factions.utility;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class StringUtility {
	
	public static String conv(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	public static Location fromString(String temp) {
		String loc = temp;
		
		
		String world = loc.substring(0, loc.indexOf(" "));
		loc = loc.replaceFirst(world + " ", "");
		double x = Double.parseDouble(loc.substring(0, loc.indexOf(" ")));
		loc = loc.replaceFirst(String.valueOf(x) + " ", "");
		double y = Double.parseDouble(loc.substring(0, loc.indexOf(" ")));
		loc = loc.replaceFirst(String.valueOf(y) + " ", "");
		double z = Double.parseDouble(loc.substring(0, loc.indexOf(" ")));
		loc = loc.replaceFirst(String.valueOf(z) + " ", "");
		float pitch = Float.parseFloat(loc.substring(0, loc.indexOf(" ")));
		loc = loc.replaceFirst(String.valueOf(pitch) + " ", "");
		float yaw = Float.parseFloat(loc);
		return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
	}
	
	public static String properName(String name) {
		char[] chars = name.toLowerCase().toCharArray();
		chars[0] = String.valueOf(chars[0]).toUpperCase().charAt(0);
		String s = String.valueOf(chars);
		s = s.replaceAll("_", " ");
		return s;
	}
	
	public static String toString(Location loc) {
		if (loc != null) {
			return loc.getWorld().getName() + " " + loc.getX() + " " + loc.getY() + " " + loc.getZ() + " " + loc.getPitch() + " " + loc.getYaw();
		} else {
			return "null";
		}
	}
}
