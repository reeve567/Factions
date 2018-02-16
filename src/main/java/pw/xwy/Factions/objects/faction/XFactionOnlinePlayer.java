package pw.xwy.Factions.objects.faction;

import pw.xwy.Factions.enums.ChatType;
import pw.xwy.Factions.objects.TeleportWarmupTask;

import java.util.List;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/13/18 6:24 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public interface XFactionOnlinePlayer extends XFactionPlayer {
	
	void addPower();
	ChatType getChatType();
	void setChatType(ChatType type);
	TeleportWarmupTask getCurrentTeleportTask();
	void setCurrentTeleportTask(TeleportWarmupTask task);
	boolean hasInvite(XPlayerFaction faction);
	boolean invite(XPlayerFaction faction);
	boolean isCancelled();
	void setCancelled(boolean cancelled);
	boolean isNoFallDamage();
	void setNoFallDamage(boolean fallDamage);
	boolean isTeleporting();
	boolean revokeInvite(XPlayerFaction faction);
	void sendMessage(String s);
	void sendMessages(List<String> strings);
	void stopTeleporting();
	void toggleCancelled();
}
