package pw.xwy.Factions.enums;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public enum ChatType {
	PUBLIC, FACTION, OFFICER, ALLY, TRUCE;
	
	public static ChatType getType(String s) {
		if (s.equalsIgnoreCase("f")) {
			return ChatType.FACTION;
		} else if (s.equalsIgnoreCase("a")) {
			return ChatType.ALLY;
		} else if (s.equalsIgnoreCase("p")) {
			return ChatType.PUBLIC;
		}
		return null;
	}
}
