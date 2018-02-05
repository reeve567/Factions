package pw.xwy.Factions.objects;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/4/18 3:18 PM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

import org.bukkit.entity.Player;
import pw.xwy.Factions.enums.ChatType;

public interface XFactionPlayer {
	
	void addPower();
	
	ChatType getChatType();
	
	void setChatType(ChatType type);
	
	TeleportWarmupTask getCurrentTeleportTask();
	
	void setCurrentTeleportTask(TeleportWarmupTask task);
	
	XFaction getFaction();
	
	void setFaction(XFaction xFaction);
	
	String getName();
	
	Player getPlayer();
	
	double getPower();
	
	boolean hasInvite(XFaction faction);
	
	boolean invite(XFaction faction);
	
	boolean isCancelled();
	
	void setCancelled(boolean cancelled);
	
	boolean isNoFallDamage();
	
	void setNoFallDamage(boolean fallDamage);
	
	boolean isTeleporting();
	
	boolean revokeInvite(XFaction faction);
	
	void save();
	
	void stopTeleporting();
	
	void toggleCancelled();
	
}
