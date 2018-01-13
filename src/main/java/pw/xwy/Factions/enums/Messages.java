package pw.xwy.Factions.enums;

import pw.xwy.Factions.utility.StringUtility;

public enum Messages {
	PREFIX("&8&l&m    »&7 "),
	HEADER("&7&m    &8&m    &7&m    &8&m    &7&m     &8&m    &7&m    &8&m    &7&m    ")
	
	
	
	;
	
	private String content;
	
	Messages(String content) {
		this.content = content;
	}
	
	public String get() {
		return StringUtility.conv(content);
	}
	
	@Override
	public String toString() {
		return StringUtility.conv(content);
	}
}
