package pw.xwy.Factions.objects;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class XRank {
	
	public int order;
	private String name;
	private ArrayList<UUID> users = new ArrayList<>();
	private ArrayList<String> perms = new ArrayList<>();
	private XFactionConfig config;
	private ArrayList<XRank> lower = new ArrayList<>();
	private XFaction faction;
	
	//load from config
	public XRank(int num, XFaction faction) {
		this.config = faction.factionConfig;
		List<String> ranks;
		ranks = config.getStringList("ranks.list");
		name = config.getString("ranks.rank." + ranks.get(num) + ".name");
		List<String> uuids = config.getStringList("ranks.rank." + name + ".members");
		for (String s : uuids) {
			users.add(UUID.fromString(s));
		}
		perms = (ArrayList<String>) config.getStringList("ranks.rank." + name + ".perms");
		order = num;
	}
	
	public XRank(String name, int order, XFaction faction) {
		this.name = name;
		this.order = order;
		this.config = faction.factionConfig;
	}
	
	public XRank(XFaction faction) {
		this.config = faction.factionConfig;
		name = "Recruit";
		
	}
	
	private void setName() {
		config.set("ranks.rank." + name + ".name", name);
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
	
	public boolean hasPerm(String s, boolean deep) {
		if (deep) {
			updateLower();
			for (XRank rank : lower) {
				if (rank.hasPerm(s, false)) {
					return true;
				}
			}
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
