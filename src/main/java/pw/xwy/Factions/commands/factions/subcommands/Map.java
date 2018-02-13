package pw.xwy.Factions.commands.factions.subcommands;

import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.entity.Player;
import pw.xwy.Factions.objects.SubCommand;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.objects.XPlayer;
import pw.xwy.Factions.utility.Configurations.Messages;
import pw.xwy.Factions.utility.StringUtility;
import pw.xwy.Factions.utility.managers.ClaimManager;
import pw.xwy.Factions.utility.managers.PlayerManager;

import java.util.HashMap;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class Map extends SubCommand {
	public Map() {
		super("map", "", "Displays a map showing the claimed land in the area.");
	}
	
	private static String getCardinalDirection(Player player) {
		double rotation = (player.getLocation().getYaw() - 180) % 360;
		if (rotation < 0) {
			rotation += 360.0;
		}
		if (0 <= rotation && rotation < 22.5) {
			return "N";
		} else if (22.5 <= rotation && rotation < 67.5) {
			return "NE";
		} else if (67.5 <= rotation && rotation < 112.5) {
			return "E";
		} else if (112.5 <= rotation && rotation < 157.5) {
			return "SE";
		} else if (157.5 <= rotation && rotation < 202.5) {
			return "S";
		} else if (202.5 <= rotation && rotation < 247.5) {
			return "SW";
		} else if (247.5 <= rotation && rotation < 292.5) {
			return "W";
		} else if (292.5 <= rotation && rotation < 337.5) {
			return "NW";
		} else if (337.5 <= rotation && rotation < 360.0) {
			return "N";
		} else {
			return null;
		}
	}
	
	@Override
	public void run(XPlayer p, String[] args) {
		String top = "&6\\N/";
		String mid = "&6W+E";
		String bot = "&6/S\\";
		if (getCardinalDirection(p).equals("N")) {
			top = "&6\\&cN&6/";
		} else if (getCardinalDirection(p).equals("NE")) {
			top = "&6\\N&c/";
		} else if (getCardinalDirection(p).equals("E")) {
			mid = "&6W+&cE";
		} else if (getCardinalDirection(p).equals("SE")) {
			bot = "&6/S&c\\";
		} else if (getCardinalDirection(p).equals("S")) {
			bot = "&6/&cS&6\\";
		} else if (getCardinalDirection(p).equals("SW")) {
			bot = "&c/&6S\\";
		} else if (getCardinalDirection(p).equals("W")) {
			mid = "&cW&6+E";
		} else if (getCardinalDirection(p).equals("NW")) {
			top = "&c\\&6N/";
		}
		
		//north -x
		//west -z
		
		//12 on each left and right of the player
		//4 above the player
		//4 below
		String[] keys = {"/", "\\", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m"};
		HashMap<XFaction, String> key = new HashMap<>();
		World world = p.getWorld();
		int x = p.getLocation().getChunk().getX();
		int z = p.getLocation().getChunk().getZ();
		int num = 0;
		
		int height = 6;
		int width = 22;
		p.sendMessage(Messages.getMapHeader());
		for (int i = -height; i <= height; i++) {
			String row = "";
			for (int j = width; j >= -width; j--) {
				Chunk c = world.getChunkAt(x - j, z + i);
				XFaction xFaction = ClaimManager.getChunk(c);
				if (i == 0 && j == 0) {
					row += "&b+";
				} else {
					if (j > width - 1 && i < -height + 3) {
						if (i == -height + 2) row += bot;
						else if (i == -height + 1) row += mid;
						else row += top;
					} else if ((j < width - 2 || i > -height + 2)) {
						if (xFaction != null) {
							if (!key.keySet().contains(xFaction)) {
								if (!xFaction.getEveryone().contains(p.getUniqueId())) {
									String ke = "&" + xFaction.color + keys[num++];
									key.put(xFaction, ke);
									row += ke;
								} else {
									key.put(xFaction, "&a$");
									row += "&a$";
								}
								
							} else {
								row += key.get(xFaction);
							}
						} else {
							row += "&7-";
						}
					}
				}
			}
			p.sendMessage(StringUtility.conv(row));
		}
		XFaction xFaction = PlayerManager.getOnlinePlayerFaction(p);
		if (key.keySet().size() > 0) {
			p.sendMessage(Messages.getMapMidFooter());
			if (key.keySet().contains(xFaction)) {
				p.sendMessage(StringUtility.conv("&aYour faction: $"));
			}
			for (XFaction faction : key.keySet()) {
				if (!faction.equals(xFaction)) {
					p.sendMessage(StringUtility.conv("&" + faction.getColor() + faction.getName() + ": " + key.get(faction)));
				}
			}
		}
		p.sendMessage(Messages.getMapFinalFooter());
	}
}
