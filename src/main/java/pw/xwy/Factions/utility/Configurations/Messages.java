package pw.xwy.factions.utility.Configurations;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import pw.xwy.factions.XFactionsCore;
import pw.xwy.factions.objects.SubCommand;
import pw.xwy.factions.objects.faction.*;
import pw.xwy.factions.utility.StringUtility;
import pw.xwy.factions.utility.managers.PlayerManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class Messages {
	
	private static File configF;
	private static FileConfiguration config;
	private static XFactionsCore xFactionsCore = XFactionsCore.getXFactionsCore();
	
	private static List<String> header;
	private static List<String> footer;
	private static List<String> commandHelpFormat;
	private static List<String> helpMenuExtra;
	private static List<String> helpMenuExtraBottom;
	private static String mapHeader;
	private static String mapMidFooter;
	private static String mapFinalFooter;
	private static List<String> whoTop, whoList, whoSystem, whoSender, whoTarget, factionCreated, allyRequest, allyRequestAccepted, allyRequestRecieved;
	private static List<String> claimed, unclaimed, alreadyClaimed, noPermission, factionDisbanded, notEnoughPower, memberLeft, notInFaction;
	private static List<String> bypassEnabled, bypassDisabled;
	private static List<String> factionChatFormat;
	private static String factionListFormat;
	
	private static List<String> colorConv(List<String> s) {
		ArrayList<String> temp = new ArrayList<>();
		for (String st : s) {
			temp.add(StringUtility.conv(st));
		}
		return convGeneralPlaceHolders(temp);
	}
	
	private static List<String> convFactionPlaceHolders(List<String> strings, XPlayerFaction faction) {
		List<String> st = new ArrayList<>();
		for (String s : strings) {
			s = replaceFactionValues(s, faction);
			st.add(s);
		}
		return st;
	}
	
	public static List<String> convGeneralPlaceHolders(List<String> strings) {
		XFactionsCore core = XFactionsCore.getXFactionsCore();
		List<String> st = new ArrayList<>();
		for (String s : strings) {
			s = s.replace("<plugin-name>", core.getDescription().getFullName());
			s = s.replace("<plugin-version>", core.getDescription().getVersion());
			s = s.replace("<players-online-total>", String.valueOf(Bukkit.getOnlinePlayers().size()));
			st.add(s);
		}
		return st;
	}
	
	private static void createConfig() {
		
		configF = new File(xFactionsCore.getDataFolder(), "messages.yml");
		
		config = new YamlConfiguration();
		
		if (!configF.exists()) {
			configF.getParentFile().mkdirs();
			xFactionsCore.saveResource("messages.yml", false);
		}
		
		try {
			config.load(configF);
		} catch (InvalidConfigurationException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static List<String> getAllyRequest(XPlayerFaction faction) {
		ArrayList<String> temp = new ArrayList<>();
		for (String s : allyRequest) {
			s = replaceFactionValues(s.replace("<ally>", faction.getName()), faction);
			temp.add(s);
		}
		return colorConv(temp);
	}
	
	public static List<String> getAllyRequestAccepted(XPlayerFaction faction) {
		ArrayList<String> temp = new ArrayList<>();
		for (String s : allyRequestAccepted) {
			s = replaceFactionValues(s.replace("<ally>", faction.getName()), faction);
			temp.add(s);
		}
		return colorConv(temp);
	}
	
	public static List<String> getAllyRequestRecieved(XPlayerFaction faction) {
		ArrayList<String> temp = new ArrayList<>();
		for (String s : allyRequestRecieved) {
			s = replaceFactionValues(s.replace("<ally>", faction.getName()), faction);
			temp.add(s);
		}
		return colorConv(temp);
	}
	
	public static List<String> getAlreadyClaimed() {
		return colorConv(alreadyClaimed);
	}
	
	public static List<String> getBypassDisabled() {
		return colorConv(bypassDisabled);
	}
	
	public static List<String> getBypassEnabled() {
		return colorConv(bypassEnabled);
	}
	
	public static List<String> getClaimed() {
		return colorConv(claimed);
	}
	
	public static List<String> getCommandHelpFormat(SubCommand command) {
		List<String> st = commandHelpFormat;
		List<String> temp = new ArrayList<>();
		for (String s : st) {
			s = s.replace("<sub-command>", command.command);
			s = s.replace("<args>", command.help);
			s = s.replace("<description>", command.help1);
			temp.add(s);
		}
		return colorConv(temp);
	}
	
	public static List<String> getFactionChatFormat(XPlayer player) {
		return convFactionPlaceHolders(factionChatFormat, player.getFaction());
	}
	
	public static List<String> getFactionCreated(XPlayer player, XPlayerFaction faction) {
		return replacePlayer(convFactionPlaceHolders(colorConv(factionCreated), faction), player);
	}
	
	public static List<String> getFactionDisbanded(XPlayer player, XPlayerFaction faction) {
		return replacePlayer(convFactionPlaceHolders(colorConv(factionDisbanded), faction), player);
	}
	
	public static String getFactionListFormat(XPlayerFaction faction) {
		return StringUtility.conv(replaceFactionValues(factionListFormat,faction));
	}
	
	public static List<String> getFooter() {
		return colorConv(footer);
	}
	
	public static List<String> getHeader() {
		return colorConv(header);
	}
	
	public static List<String> getHelpMenuExtra(int page, int maxpage) {
		List<String> temp = new ArrayList<>();
		for (String s : helpMenuExtra) {
			s = s.replace("<page>", String.valueOf(page));
			s = s.replace("<total-pages>", String.valueOf(maxpage));
			temp.add(s);
		}
		return colorConv(temp);
	}
	
	public static List<String> getHelpMenuExtraBottom(int page, int maxPage) {
		List<String> temp = new ArrayList<>();
		for (String s : helpMenuExtraBottom) {
			s = s.replace("<page>", String.valueOf(page));
			s = s.replace("<total-pages>", String.valueOf(maxPage));
			temp.add(s);
		}
		return colorConv(temp);
	}
	
	public static String getMapFinalFooter() {
		return StringUtility.conv(mapFinalFooter);
	}
	
	public static String getMapHeader() {
		return StringUtility.conv(mapHeader);
	}
	
	public static String getMapMidFooter() {
		return StringUtility.conv(mapMidFooter);
	}
	
	public static List<String> getMemberLeft(XFactionPlayer player) {
		return replacePlayer(colorConv(memberLeft), player);
	}
	
	public static List<String> getNoPermission() {
		return colorConv(noPermission);
	}
	
	public static List<String> getNotEnoughPower() {
		return colorConv(notEnoughPower);
	}
	
	public static List<String> getNotInFaction() {
		return colorConv(notInFaction);
	}
	
	public static List<String> getUnclaimed() {
		return colorConv(unclaimed);
	}
	
	public static List<String> getWhoList(XRank rank) {
		List<String> temp = new ArrayList<>();
		for (String s : whoList) {
			s = s.replace("<faction-group-name>", rank.properName());
			s = s.replace("<faction-group-members>", rank.memberString());
			temp.add(s);
		}
		return colorConv(temp);
	}
	
	public static List<String> getWhoSender() {
		return colorConv(whoSender);
	}
	
	public static List<String> getWhoSystem() {
		return colorConv(whoSystem);
	}
	
	public static List<String> getWhoTarget() {
		return colorConv(whoTarget);
	}
	
	public static List<String> getWhoTop() {
		return colorConv(whoTop);
	}
	
	static void loadConfig() {
		createConfig();
		saveConfig();
		
		header = config.getStringList("general.header");
		footer = config.getStringList("general.footer");
		commandHelpFormat = config.getStringList("general.command-help");
		mapHeader = config.getString("map.header");
		mapMidFooter = config.getString("map.mid-footer");
		mapFinalFooter = config.getString("map.final-footer");
		whoTop = config.getStringList("who.top");
		whoList = config.getStringList("who.groupsList");
		whoSystem = config.getStringList("who.system");
		whoTarget = config.getStringList("who.target-no-faction");
		whoSender = config.getStringList("who.sender-no-faction");
		helpMenuExtra = config.getStringList("general.help-menu-extra-top");
		helpMenuExtraBottom = config.getStringList("general.help-menu-extra-bottom");
		allyRequest = config.getStringList("general.ally-request-sent");
		allyRequestRecieved = config.getStringList("general.ally-request-received");
		allyRequestAccepted = config.getStringList("general.ally-request-accepted");
		factionCreated = config.getStringList("faction.faction-created");
		factionDisbanded = config.getStringList("faction.faction-disbanded");
		alreadyClaimed = config.getStringList("faction.already-claimed");
		noPermission = config.getStringList("faction.no-permission");
		claimed = config.getStringList("faction.claimed");
		unclaimed = config.getStringList("faction.unclaimed");
		notEnoughPower = config.getStringList("faction.not-enough-power");
		memberLeft = config.getStringList("faction.member-left");
		notInFaction = config.getStringList("faction.not-in-faction");
		bypassEnabled = config.getStringList("general.admin-bypass-enabled");
		bypassDisabled = config.getStringList("general.admin-bypass-disabled");
		factionChatFormat = config.getStringList("faction.faction-chat-format");
		factionListFormat = config.getString("faction.faction-list-format");
	}
	
	public static String replaceFactionValues(String s, XPlayerFaction faction) {
		s = s.replace("<faction-name>", faction.getName());
		s = s.replace("<faction-desc>", faction.getDesc());
		s = s.replace("<faction-claimed-land>", String.valueOf(faction.claim.get().size()));
		s = s.replace("<faction-max-land>", String.valueOf((int) faction.getMaxPower()));
		s = s.replace("<faction-members-online>", String.valueOf(faction.getOnlinePlayers()));
		s = s.replace("<faction-members-total>", String.valueOf(faction.players.size()));
		s = s.replace("<faction-leader>", PlayerManager.getOfflinePlayerName(faction.getLeader()));
		s = s.replace("<faction-color>", "&" + faction.getColor());
		return s;
	}
	
	public static String replaceFactionValues(String s, XFaction faction) {
		if (faction instanceof XPlayerFaction) {
			return replaceFactionValues(s, (XPlayerFaction) faction);
		}
		s = s.replace("<faction-name>", faction.getName());
		s = s.replace("<faction-desc>", faction.getDesc());
		s = s.replace("<faction-claimed-land>", String.valueOf(faction.claim.get().size()));
		s = s.replace("<faction-color>", StringUtility.conv("&" + faction.getColor()));
		return s;
	}
	
	private static List<String> replacePlayer(List<String> strings, XFactionPlayer player) {
		List<String> st = new ArrayList<>();
		for (String s : strings) {
			s = s.replaceAll("<player>", player.getName());
			st.add(s);
		}
		return st;
	}
	
	private static void saveConfig() {
		try {
			config.save(configF);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
