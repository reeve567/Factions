package pw.xwy.factions.objects.faction;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import pw.xwy.factions.utility.StringUtility;
import pw.xwy.factions.utility.managers.ClaimManager;
import pw.xwy.factions.utility.managers.FactionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/16/18 2:38 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class XFaction {
	public XClaim claim = new XClaim();
	protected String name;
	protected XFactionConfig factionConfig;
	protected UUID id;
	private String desc = "Default faction description D:";
	private String color;
	
	public XFaction(XFactionConfig config) {
		loadClaim(config.getClaim());
		name = config.getName();
		id = config.getUUID();
		color = config.getColor();
		factionConfig = config;
	}
	
	public XFaction(String name, String c, boolean makeConfig) {
		this.name = name;
		this.color = c;
		this.id = FactionManager.getAvailableUUID();
		if (makeConfig) {
			setupConfig();
		}
	}
	
	public static XFaction loadFaction(String s) {
		XFactionConfig config = new XFactionConfig(s);
		if (config.isSystem()) {
			return new XFaction(config);
		} else {
			return new XPlayerFaction(config);
		}
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
	
	public void claim(Chunk c, int radius) {
		for (int x = -radius; x <= radius; x++) {
			for (int y = -radius; y <= radius; y++) {
				Chunk ch = c.getWorld().getChunkAt(c.getX() + x, c.getZ() + y);
				if (!ClaimManager.isClaimed(ch) || powerCheck()) {
					claim.add(ch, this);
				}
			}
		}
	}
	
	public void disband() {
		Bukkit.broadcastMessage(StringUtility.conv("&c" + getName() + " has been disbanded."));
		FactionManager.removeFaction(this);
		ClaimManager.removeChunks(this);
		factionConfig.remove();
	}
	
	public List<String> getClaimStrings() {
		List<String> strings = new ArrayList<>();
		for (Chunk c : claim.get()) {
			strings.add(c.getWorld().getName() + " " + c.getX() + " " + c.getZ());
		}
		return strings;
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
	
	public XFactionConfig getFactionConfig() {
		return factionConfig;
	}
	
	public UUID getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean powerCheck() {
		return true;
	}
	
	public void setupConfig() {
		factionConfig = new XFactionConfig(this);
	}
	
	@Override
	public String toString() {
		return "{Name: " + name + "; Color: " + color + "}";
	}
}
