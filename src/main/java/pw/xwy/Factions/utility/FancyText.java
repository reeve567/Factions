package pw.xwy.Factions.utility;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.util.CraftChatMessage;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;

public class FancyText {
	
	
	LinkedHashMap<String, MessageComponent> message = new LinkedHashMap<>();
	
	
	public FancyText addText(String text) {
		this.message.put(text, new MessageComponent(text, null, null));
		return this;
	}
	
	public FancyText addClickableLink(String text, String link) {
		this.message.put(link, new MessageComponent(text, link, ChatClickable.EnumClickAction.OPEN_URL));
		return null;
		
		
	}
	
	public FancyText addRunnableCommand(String text, String command) {
		
		this.message.put(text, new MessageComponent(text, command, ChatClickable.EnumClickAction.RUN_COMMAND));
		return this;
	}
	
	public FancyText addChatSuggestion(String text, String suggestion) {
		
		this.message.put(text, new MessageComponent(text, suggestion, ChatClickable.EnumClickAction.SUGGEST_COMMAND));
		
		return this;
	}
	
	
	public FancyText addHoverEvent(String text, String hover) {
		
		this.message.put(text, new MessageComponent(text, hover, ChatHoverable.EnumHoverAction.SHOW_TEXT));
		
		return this;
	}
	
	
	public void sendToPlayer(Player player){
		CraftPlayer cp = (CraftPlayer)player;
		EntityPlayer ep = cp.getHandle();
		for (String text: message.keySet()){
			for (IChatBaseComponent m: message.get(text).compile()) {
				ChatBaseComponent.a(m);
				ep.playerConnection.sendPacket(new PacketPlayOutChat(m));
			}
		}
	}
	
	public void sendToAllPlayers() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			this.sendToPlayer(player);
		}
	}
	
	public class MessageComponent {
		
		Enum<?> e;
		String data;
		String text;
		IChatBaseComponent[] chat;
		
		public MessageComponent(String text, String data, Enum<?> e) {
			this.e = e;
			this.text = text;
			this.data = data;
			chat = CraftChatMessage.fromString(text);
		}
		
		
		public IChatBaseComponent[] compile() {
			for (IChatBaseComponent c : chat) {
				if (data == null || e == null) {
					
					return chat;
				}
				if (e instanceof ChatClickable.EnumClickAction) {
					c.getChatModifier().setChatClickable(new ChatClickable((ChatClickable.EnumClickAction) e, data));
				} else if (e instanceof ChatHoverable.EnumHoverAction) {
					c.getChatModifier().setChatHoverable((new ChatHoverable((ChatHoverable.EnumHoverAction) e, new ChatComponentText(data))));
				}
			}
			
			return chat;
		}
		
	}
	
	
}
