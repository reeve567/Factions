package pw.xwy.Factions.objects.faction;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/14/18 8:49 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class XWarp {
	
	private Location location;
	private String password = "";
	private boolean passwordProtected = false;
	private String name;
	
	public XWarp(String name, Location location, String password) {
		this.name = name;
		this.location = location;
		this.password = password;
		this.passwordProtected = true;
	}
	
	public XWarp(String name, Location location) {
		this.name = name;
		this.location = location;
	}
	
	public static XWarp getXWarp(String fullyQualifiedWarpString) {
		String name = fullyQualifiedWarpString.substring(0, fullyQualifiedWarpString.indexOf(';'));
		fullyQualifiedWarpString = fullyQualifiedWarpString.substring(fullyQualifiedWarpString.indexOf(';') + 1);
		String password = fullyQualifiedWarpString.substring(0, fullyQualifiedWarpString.indexOf(';'));
		fullyQualifiedWarpString = fullyQualifiedWarpString.substring(fullyQualifiedWarpString.indexOf(';') + 1);
		String world = fullyQualifiedWarpString.substring(0, fullyQualifiedWarpString.indexOf(';'));
		fullyQualifiedWarpString = fullyQualifiedWarpString.substring(fullyQualifiedWarpString.indexOf(';') + 1);
		double x = Double.parseDouble(fullyQualifiedWarpString.substring(0, fullyQualifiedWarpString.indexOf(';')));
		fullyQualifiedWarpString = fullyQualifiedWarpString.substring(fullyQualifiedWarpString.indexOf(';') + 1);
		double y = Double.parseDouble(fullyQualifiedWarpString.substring(0, fullyQualifiedWarpString.indexOf(';')));
		fullyQualifiedWarpString = fullyQualifiedWarpString.substring(fullyQualifiedWarpString.indexOf(';') + 1);
		double z = Double.parseDouble(fullyQualifiedWarpString);
		XWarp warp;
		if (!password.equals("")) {
			warp = new XWarp(name, new Location(Bukkit.getWorld(world), x, y, z), password);
		} else {
			warp = new XWarp(name, new Location(Bukkit.getWorld(world), x, y, z));
		}
		return warp;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isPasswordProtected() {
		return passwordProtected;
	}
	
	@Override
	public String toString() {
		return name + ";" + password + ";" + location.getWorld().getName() + ";" + location.getX() + ";" + location.getY() + ";" + location.getZ();
	}
}
