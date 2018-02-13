package pw.xwy.Factions.objects;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/13/18 6:09 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public abstract class Menu {

	public Menu() {
		load();
		setup();
	}
	
	public abstract void load();
	public abstract void unload();
	public abstract void setup();

}
