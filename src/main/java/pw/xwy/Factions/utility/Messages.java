package pw.xwy.Factions.utility;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import pw.xwy.Factions.XFactionsCore;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.objects.XRank;
import pw.xwy.Factions.utility.managers.PlayerManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Messages {
	
	private static File configF;
	private static FileConfiguration config;
	private static XFactionsCore xFactionsCore = XFactionsCore.getXFactionsCore();
	
	private static List<String> header;
	private static List<String> footer;
	private static List<String> commandHelpFormat;
	private static String mapHeader;
	private static String mapMidFooter;
	private static String mapFinalFooter;
	private static List<String> whoTop;
	private static List<String> whoList;
	private static List<String> whoSystem;
	private static List<String> whoTarget;
	private static List<String> whoSender;
	
	
	public static void loadConfig() {
		createConfig();
		saveConfig();
		
		header = config.getStringList("general.header");
		footer = config.getStringList("general.footer");
		commandHelpFormat = config.getStringList("general.command-help");
		mapHeader = config.getString("map.header");
		mapMidFooter = config.getString("map.mid-footer");
		mapFinalFooter = config.getString("map.final-footer");
		whoTop = config.getStringList("who.top");
		whoList = config.getStringList("who.list");
		whoSystem = config.getStringList("who.system");
		whoTarget = config.getStringList("who.target-no-faction");
		whoSender = config.getStringList("who.sender-no-faction");
		
	}
	
	private static void createConfig() {
		
		configF = new File(xFactionsCore.getDataFolder(), "messages.yml");
		
		if (!configF.exists()) {
			configF.getParentFile().mkdirs();
			config = YamlConfiguration.loadConfiguration(configF);
			config.createSection("general");
			set("general.header", Arrays.asList("", "&7&m    &8&m    &7&m    &8&m    &7&m     &8&m    &7&m    &8&m    &7&m"));
			set("general.footer", Arrays.asList("", "&7&m    &8&m    &7&m    &8&m    &7&m     &8&m    &7&m    &8&m    &7&m", ""));
			set("general.command-help", Arrays.asList("&8&l&m    »&7 <sub-command> &6<args>", "<description>"));
			set("general.help-menu-extra", "&8Sub-commands");
			
			config.createSection("map");
			set("map.header", "&7&m    &8&m    &7&m    &8&m    &7&m     &8&m    &7&m    &8&m    &7&m");
			set("map.mid-footer", "&7&m    &8&m    &7&m    &8&m    &7&m     &8&m    &7&m    &8&m    &7&m");
			set("map.final-footer", "&7&m    &8&m    &7&m    &8&m    &7&m     &8&m    &7&m    &8&m    &7&m");
			
			config.createSection("who");
			set("who.top", Arrays.asList("&7Name: &6<faction-name>", "&7Desc: &6<faction-desc>", "&7Claim: &6<faction-claimed-land>&7/&6<faction-max-land>", "&7Members online: &6<faction-members-online>&7/&6<faction-members-total>", "&7Leader: &6<faction-leader>"));
			set("who.groupsList", Arrays.asList("&7<faction-group-name>: &6<faction-group-members>"));
			set("who.system", Arrays.asList("&7Name: <faction-color><faction-name>", "&7Desc: &6<faction-desc>"));
			set("who.target-no-faction", Arrays.asList("&7They are not in a faction."));
			set("who.sender-no-faction", Arrays.asList("&7You are not in a faction."));
			
			//xFactionsCore.saveResource("messages.yml", false);
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
	
	private static void set(String s, Object ob) {
		config.set(s, ob);
	}
	
	public static ArrayList<String> getHeader() {
		return colorConv(header);
	}
	
	private static ArrayList<String> colorConv(List<String> s) {
		ArrayList<String> temp = new ArrayList<>();
		for (String st : s) {
			temp.add(StringUtility.conv(st));
		}
		return temp;
	}
	
	public static ArrayList<String> getFooter() {
		return colorConv(footer);
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
	
	public static String getMapMidFooter() {
		return StringUtility.conv(mapMidFooter);
	}
	
	public static List<String> getWhoList(XRank rank) {
		ArrayList<String> temp = new ArrayList<>();
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
	
}
