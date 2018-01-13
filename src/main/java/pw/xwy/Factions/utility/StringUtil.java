package pw.xwy.Factions.utility;
// made by reeve
// on 3:18 PM

import org.bukkit.ChatColor;

import java.util.ArrayList;

public class StringUtil {
	
	public static boolean chatColorString(String one, String two) {
		return one.equals(ChatColor.translateAlternateColorCodes('&', two));
	}
	
	public static ArrayList<String> getStringList(char seperator, String list) {
		ArrayList<String> strings = new ArrayList<>();
		do {
			if (list.contains(String.valueOf(seperator))) {
				strings.add(list.substring(0, list.indexOf(seperator)));
				list = list.substring(list.indexOf(seperator) + 1);
			} else {
				if (list.length() > 0) {
					strings.add(list);
				}
			}
		}
		while (list.contains(String.valueOf(seperator)));
		
		return strings;
	}
	
	public static boolean hasNext(char seperator, String list) {
		return list.contains(String.valueOf(seperator));
	}
	
	public static String chatColorConv(String string) {
		return ChatColor.translateAlternateColorCodes('&', string);
	}
	
}
