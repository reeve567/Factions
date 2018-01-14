package pw.xwy.Factions.commands.factions;

import pw.xwy.Factions.commands.MainCommand;
import pw.xwy.Factions.commands.SubCommand;
import pw.xwy.Factions.commands.factions.subcommands.*;

public class Faction extends MainCommand {
	public Faction() {
		super("f", "Factions.use");
	}
	
	@Override
	public void init() {
		add(new Disband());
		add(new Claim());
		add(new Create());
		add(new Group());
		add(new Groups());
		add(new Help(this));
		add(new Invite());
		add(new Join());
		add(new Leave());
		add(new List());
		add(new Map());
		add(new Power());
		//add(new Version());
		add(new Who());
	}
}
