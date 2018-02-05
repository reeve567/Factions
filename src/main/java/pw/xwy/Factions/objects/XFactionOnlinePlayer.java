package pw.xwy.Factions.objects;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/5/18 3:51 PM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

import pw.xwy.Factions.enums.ChatType;

import java.util.List;

public interface XFactionOnlinePlayer extends XFactionPlayer {
	
	void sendMessages(List<String> strings);
	void sendMessage(String s);
	void addPower();
	ChatType getChatType();
	void setChatType(ChatType type);
	TeleportWarmupTask getCurrentTeleportTask();
	void setCurrentTeleportTask(TeleportWarmupTask task);
	boolean hasInvite(XFaction faction);
	boolean invite(XFaction faction);
	boolean isCancelled();
	void setCancelled(boolean cancelled);
	boolean isNoFallDamage();
	void setNoFallDamage(boolean fallDamage);
	boolean isTeleporting();
	boolean revokeInvite(XFaction faction);
	void stopTeleporting();
	void toggleCancelled();
}
