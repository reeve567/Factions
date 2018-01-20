package pw.xwy.Factions.objects;

import org.bukkit.entity.Player;
import pw.xwy.Factions.utility.managers.PlayerManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class XRank {
	
	public int order;
	public String name;
	public ArrayList<UUID> users = new ArrayList<>();
	public ArrayList<XRank> lower = new ArrayList<>();
	private ArrayList<String> perms = new ArrayList<>();
	private XFactionConfig config;
	private XFaction faction;
	
	public XRank(String name, int order, XFaction faction, boolean useConfig) {
		this.faction = faction;
		this.name = name;
		this.order = order;
		this.config = faction.factionConfig;
		if (useConfig) {
			this.faction = faction;
			this.config = faction.factionConfig;
			List<String> uuids = config.getStringList("ranks.rank." + name + ".members");
			for (String s : uuids) {
				users.add(UUID.fromString(s));
				faction.players.add(UUID.fromString(s));
			}
			perms = (ArrayList<String>) config.getStringList("ranks.rank." + name + ".perms");
		}
		
	}
	
	public XRank(XFaction faction) {
		this.faction = faction;
		this.config = faction.factionConfig;
		name = "Recruit";
	}
	
	public void save() {
		setUsers();
		setPerms();
	}
	
	private void setUsers() {
		List<String> users = new ArrayList<>();
		for (UUID p : this.users) {
			users.add(p.toString());
		}
		config.set("ranks.rank." + name + ".members", users);
	}
	
	private void setPerms() {
		config.set("ranks.rank." + name + ".perms", perms);
	}
	
	public boolean isIn(UUID uuid) {
		return users.contains(uuid);
	}
	
	public void add(UUID uuid) {
		users.add(uuid);
	}
	
	public String properName() {
		char[] chars = name.toLowerCase().toCharArray();
		chars[0] = String.valueOf(chars[0]).toUpperCase().charAt(0);
		return String.valueOf(chars);
	}
	
	public String memberString() {
		String s = "";
		for (UUID id : users) {
			if (users.indexOf(id) != users.size() - 1) {
				s += PlayerManager.getOfflinePlayer(id).getName() + ", ";
			} else {
				s += PlayerManager.getOfflinePlayer(id).getName();
			}
		}
		return s;
	}
	
	public boolean hasPerm(String s, boolean deep) {
		if (deep) {
			/*
			updateLower();
			for (XRank rank : lower) {
				if (rank.hasPerm(s, false)) {
					return true;
				}
			}
			*/
		}
		return perms.contains(s);
	}
	
	private void updateLower() {
		for (XRank rank : faction.ranks) {
			if (rank.order > order) {
				lower.add(rank);
			}
		}
	}
	
	public boolean isIn(Player p) {
		return users.contains(p.getUniqueId());
	}
	
}
