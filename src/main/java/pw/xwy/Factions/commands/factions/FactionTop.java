package pw.xwy.Factions.commands.factions;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import pw.xwy.Factions.commands.MainCommand;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.utility.Configurations.Messages;
import pw.xwy.Factions.utility.Configurations.Spawners;
import pw.xwy.Factions.utility.StringUtility;
import pw.xwy.Factions.utility.managers.FactionManager;

import java.util.ArrayList;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class FactionTop extends MainCommand {
	public FactionTop() {
		super("ftop", "factions.top");
	}
	
	private String JSONTempSpawners(EntityType e, XFaction faction) {
		String temp = ",{\"text\":\"\n&6&lcontent &8&l» &7&lnumber\"}";
		temp = temp.replace("number", String.valueOf(faction.spawners.get(e)));
		if (!e.getName().equalsIgnoreCase("VillagerGolem")) {
			temp = temp.replace("content", e.getName());
		} else {
			temp = temp.replace("content", "IronGolem");
		}
		temp = StringUtility.conv(temp);
		return temp;
	}
	
	private void display(Player player, int page) {
		ArrayList<XFaction> factions = FactionManager.getMostValueble();
		Messages.sendMessages(player, Messages.getHeader());
		for (XFaction f : factions) {
			sendToPlayer(player, f);
		}
		int maxpages = factions.size() / 5;
		if (factions.size() % 5 != 0) {
			maxpages++;
		}
		Messages.sendMessages(player, Messages.getHelpMenuExtraBottom(page, maxpages));
		Messages.sendMessages(player, Messages.getFooter());
	}
	
	private void sendToPlayer(Player player, XFaction faction) {
		IChatBaseComponent chat = IChatBaseComponent.ChatSerializer.a(" [\"\",{\"text\":\"" + faction.getName() + " \",\"color\":\"gold\"},{\"text\":\"» \",\"color\":\"gray\"},{\"text\":\"" + faction.getValue() + "\",\"color\":\"gold\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Spawners:\",\"color\":\"red\",\"bold\":true}" + spawnersString(faction) + "]}}}]");
		CraftPlayer p = (CraftPlayer) player;
		PacketPlayOutChat packet = new PacketPlayOutChat(chat);
		p.getHandle().playerConnection.sendPacket(packet);
		
	}
	
	private String spawnersString(XFaction faction) {
		String s = "";
		for (EntityType e : Spawners.getInstance().arrayList) {
			s += JSONTempSpawners(e, faction);
		}
		return s;
	}
	
	@Override
	public void init() {
	
	}
	
	@Override
	public void run(Player p, String[] args) {
		if (args.length == 2) {
			display(p, Integer.parseInt(args[1]));
		} else {
			display(p, 1);
		}
	}
	
}
