package pw.xwy.factions.objects.faction;

import pw.xwy.factions.utility.managers.FactionManager;

import java.util.UUID;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/13/18 6:24 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class XOfflinePlayer implements XFactionPlayer {
	
	private XPlayerConfig config;
	private UUID uuid;
	private XPlayerFaction faction;
	
	public XOfflinePlayer(UUID uuid) {
		this.uuid = uuid;
		config = new XPlayerConfig(uuid);
		faction = FactionManager.getFactionFromUUID(UUID.fromString(config.getFactionUUID()));
	}
	
	@Override
	public XPlayerFaction getFaction() {
		return faction;
	}
	
	@Override
	public void setFaction(XPlayerFaction xPlayerFaction) {
		faction = xPlayerFaction;
	}
	
	@Override
	public String getName() {
		return config.getName();
	}
	
	@Override
	public double getPower() {
		return 0;
	}
	
	@Override
	public UUID getUniqueId() {
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
