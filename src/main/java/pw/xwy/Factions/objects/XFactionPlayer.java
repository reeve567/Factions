package pw.xwy.Factions.objects;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/4/18 3:18 PM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

import java.util.UUID;

public interface XFactionPlayer {
	
	XPlayerConfig config = null;
	XFaction faction = null;
	
	XFaction getFaction();
	void setFaction(XFaction xFaction);
	String getName();
	double getPower();
	UUID getUniqueId();
	boolean isOnline();
	void save();
}
