package pw.xwy.Factions.utility.Configurations;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import pw.xwy.Factions.XFactionsCore;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.objects.XRank;
import pw.xwy.Factions.utility.StringUtility;
import pw.xwy.Factions.utility.managers.PlayerManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	private static List<String> whoTop;
	private static List<String> whoList;
	private static List<String> whoSystem;
	private static List<String> whoTarget;
	private static List<String> whoSender;
	private static List<String> allyRequest;
	private static List<String> allyRequestRecieved;
	private static List<String> allyRequestAccepted;
	
	
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
		allyRequestRecieved = config.getStringList("general.ally-request-recieved");
		allyRequestAccepted = config.getStringList("general.ally-request-accepted");
		
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
	
	private static void saveConfig() {
		try {
			config.save(configF);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static List<String> getHeader() {
		return colorConv(header);
	}
	
	private static List<String> colorConv(List<String> s) {
		ArrayList<String> temp = new ArrayList<>();
		for (String st : s) {
			temp.add(StringUtility.conv(st));
		}
		return convGeneralPlaceHolders(temp);
	}
	
	public static List<String> getFooter() {
		return colorConv(footer);
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
	
	public static void sendMessages(Player p, List<String> messages) {
		for (String s : messages) {
			p.sendMessage(s);
		}
	}
	
	public static String getMapFinalFooter() {
		return StringUtility.conv(mapFinalFooter);
	}
	
	public static String getMapHeader() {
		return StringUtility.conv(mapHeader);
	}
	
	public static List<String> getWhoSender() {
		return colorConv(whoSender);
	}
	
	public static List<String> getWhoTarget() {
		return colorConv(whoTarget);
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
	
	public static String getMapMidFooter() {
		return StringUtility.conv(mapMidFooter);
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
	
	public static List<String> getWhoSystem() {
		return colorConv(whoSystem);
	}
	
	public static List<String> getAllyRequest(XFaction faction) {
		ArrayList<String> temp = new ArrayList<>();
		for (String s : allyRequest) {
			s = replaceFactionValues(s.replace("<ally>", faction.getName()),faction);
			temp.add(s);
		}
		return colorConv(temp);
	}
	
	public static List<String> getWhoTop() {
		return colorConv(whoTop);
	}
	
	public static String replaceFactionValues(String s, XFaction faction) {
		s = s.replace("<faction-name>", faction.getName());
		s = s.replace("<faction-desc>", faction.desc);
		s = s.replace("<faction-claimed-land>", String.valueOf(faction.claim.get().size()));
		s = s.replace("<faction-max-land>", String.valueOf((int) faction.getMaxPower()));
		s = s.replace("<faction-members-online>", String.valueOf(faction.getOnlinePlayers()));
		s = s.replace("<faction-members-total>", String.valueOf(faction.players.size() + 1));
		s = s.replace("<faction-leader>", PlayerManager.getOfflinePlayer(faction.getLeader()).getPlayer().getName());
		s = s.replace("<faction-color>", "&" + faction.color);
		return s;
	}
	
	public static List<String> getAllyRequestRecieved(XFaction faction) {
		ArrayList<String> temp = new ArrayList<>();
		for (String s : allyRequestRecieved) {
			s = replaceFactionValues(s.replace("<ally>", faction.getName()), faction);
			temp.add(s);
		}
		return colorConv(temp);
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
	
	public static List<String> getAllyRequestAccepted(XFaction faction) {
		ArrayList<String> temp = new ArrayList<>();
		for (String s : allyRequestAccepted) {
			s = replaceFactionValues(s.replace("<ally>", faction.getName()),faction);
			temp.add(s);
		}
		return colorConv(temp);
	}
}
