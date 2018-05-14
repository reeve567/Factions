package pw.xwy.factions.utility;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import java.util.ArrayList;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class StringUtility {
	
	public static boolean colorCompare(String one, String two) {
		return conv(one).equalsIgnoreCase(conv(two));
	}
	
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
	
	public static ArrayList<String> getStringList(char seperator, String list) {
		ArrayList<String> strings = new ArrayList<>();
		do {
			if (list.contains(String.valueOf(seperator))) {
				strings.add(list.substring(0, list.indexOf(seperator)));
				list = list.substring(list.indexOf(seperator) + 1);
			} else {
				if (list.length() > 0) {
					strings.add(list);
				}
			}
		}
		while (list.contains(String.valueOf(seperator)));
		
		return strings;
	}
	
	public static boolean hasNext(char seperator, String list) {
		return list.contains(String.valueOf(seperator));
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
