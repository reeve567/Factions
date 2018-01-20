package pw.xwy.Factions.commands.factions.subcommands;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.utility.Configurations.Messages;
import pw.xwy.Factions.utility.Configurations.Spawners;
import pw.xwy.Factions.utility.StringUtil;
import pw.xwy.Factions.utility.managers.FactionManager;

import java.util.ArrayList;

public class Top extends SubCommand {
	public Top() {
		super("top", "[page]", "Displays the top factions based on their value.");
	}
	
	@Override
	public void run(Player p, String[] args) {
		if (args.length == 2) {
			display(p, Integer.parseInt(args[1]));
		} else {
			display(p, 1);
		}
	}
	
	private void display(Player player, int page) {
		ArrayList<XFaction> factions = FactionManager.getMostValueble();
		Messages.sendMessages(player,Messages.getHeader());
		for (XFaction f : factions) {
			sendToPlayer(player, f);
		}
		int maxpages = factions.size()/5;
		if (factions.size()%5!=0) {
			maxpages++;
		}
		Messages.sendMessages(player,Messages.getHelpMenuExtraBottom(page,maxpages));
		Messages.sendMessages(player,Messages.getFooter());
		
		
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
	
	private String JSONTempSpawners(EntityType e, XFaction faction) {
		String temp = ",{\"text\":\"\n&6&lcontent &8&l» &7&lnumber\"}";
		temp = temp.replace("number", String.valueOf(faction.spawners.get(e)));
		if (!e.getName().equalsIgnoreCase("VillagerGolem")) {
			temp = temp.replace("content", e.getName());
		}
		else {
			temp = temp.replace("content","IronGolem");
		}
		temp = StringUtil.chatColorConv(temp);
		return temp;
	}
	
}
