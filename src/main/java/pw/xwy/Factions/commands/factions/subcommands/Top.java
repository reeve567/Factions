package pw.xwy.Factions.commands.factions.subcommands;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.entity.Player;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.utility.FancyText;
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
			display(p,1);
		}
	}
	
	private void display(Player player, int page) {
		ArrayList<XFaction> factions = FactionManager.getMostValueble();
		
		for (XFaction f : factions) {
			FancyText fancyText = new FancyText();
			fancyText.addText(StringUtil.chatColorConv("&6" + f.getName() + " » "));
			fancyText.addHoverEvent(String.valueOf(f.getValue()),"test");
			fancyText.sendToPlayer(player);
		}
		
		
	}
	
	
	private void sendToPlayer() {
	
	}
	
}
