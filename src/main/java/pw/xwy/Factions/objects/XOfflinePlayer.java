package pw.xwy.Factions.objects;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/5/18 3:50 PM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

import pw.xwy.Factions.utility.managers.FactionManager;

import java.util.UUID;

public class XOfflinePlayer implements XFactionPlayer {
	
	private XPlayerConfig config;
	private UUID uuid;
	private XFaction faction;
	
	public XOfflinePlayer(UUID uuid) {
		this.uuid = uuid;
		config = new XPlayerConfig(uuid);
		faction = FactionManager.getFactionFromUUID(UUID.fromString(config.getFactionUUID()));
	}
	
	@Override
	public XFaction getFaction() {
		return faction;
	}
	
	@Override
	public void setFaction(XFaction xFaction) {
		faction = xFaction;
	}
	
	@Override
	public String getName() {
		return config.getName();
	}
	
	@Override
	public double getPower() {
		return 0;
	}
	
	public UUID getUuid() {
		return uuid;
	}
	
	@Override
	public boolean isOnline() {
		return false;
	}
	
	@Override
	public void save() {
		config.save();
	}
}
