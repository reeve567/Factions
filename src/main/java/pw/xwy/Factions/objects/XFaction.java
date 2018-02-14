package pw.xwy.Factions.objects;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/13/18 6:24 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import pw.xwy.Factions.objects.faction.XClaim;
import pw.xwy.Factions.objects.faction.XFactionConfig;
import pw.xwy.Factions.objects.faction.XPlayerFaction;
import pw.xwy.Factions.utility.StringUtility;
import pw.xwy.Factions.utility.managers.ClaimManager;
import pw.xwy.Factions.utility.managers.FactionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class XFaction {
	public XClaim claim = new XClaim();
	protected String name;
	protected String desc = "Default faction description D:";
	protected String color = "f";
	protected XFactionConfig factionConfig;
	protected UUID id;
	
	public XFaction(XFactionConfig config) {
		loadClaim(config.getClaim());
		name = config.getName();
		id = config.getUUID();
		factionConfig = config;
	}
	
	protected void loadClaim(List<String> claim) {
		for (String st : claim) {
			String temp = st;
			String world = temp.substring(0, temp.indexOf(' '));
			temp = temp.replaceFirst(world + " ", "");
			int x = Integer.parseInt(temp.substring(0, temp.indexOf(' ')));
			temp = temp.replaceFirst(String.valueOf(x) + ' ', "");
			int z = Integer.parseInt(temp);
			Chunk c = Bukkit.getWorld(world).getChunkAt(x, z);
			claim(c);
		}
	}
	
	public void claim(Chunk c) {
		if (!ClaimManager.isClaimed(c) || powerCheck()) {
			claim.add(c, this);
		}
	}
	
	public boolean powerCheck() {
		return true;
	}
	
	public XFaction(String name, String c) {
		this.name = name;
		this.color = c;
		factionConfig = new XFactionConfig(this);
	}
	
	public static XFaction loadFaction(String s) {
		XFactionConfig config = new XFactionConfig(s);
		if (config.isSystem()) {
			return new XFaction(config);
		} else {
			return new XPlayerFaction(config);
		}
	}
	
	public void disband() {
		Bukkit.broadcastMessage(StringUtility.conv("&c" + getName() + " has been disbanded."));
		FactionManager.removeFaction(this);
		ClaimManager.removeChunks(this);
		factionConfig.remove();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public XFactionConfig getFactionConfig() {
		return factionConfig;
	}
	
	public UUID getId() {
		return id;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public String getDesc() {
		return desc;
	}
	
	@Override
	public String toString() {
		return "Name: " + name;
	}
	
	public List<String> getClaimStrings() {
		List<String> strings = new ArrayList<>();
		for (Chunk c : claim.get()) {
			strings.add(c.getWorld().getName() + " " + c.getX() + " " + c.getZ());
		}
		return strings;
	}
}
