package pw.xwy.Factions.objects.faction;

import java.util.UUID;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/13/18 6:24 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public interface XFactionPlayer {
	
	XPlayerConfig config = null;
	XPlayerFaction faction = null;
	
	XPlayerFaction getFaction();
	void setFaction(XPlayerFaction xPlayerFaction);
	String getName();
	double getPower();
	UUID getUniqueId();
	boolean isOnline();
	void save();
}
