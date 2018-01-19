package pw.xwy.Factions.utility.tasks;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.scheduler.BukkitRunnable;
import pw.xwy.Factions.objects.XFaction;
import pw.xwy.Factions.utility.Configurations.Spawners;

public class ChunkValueTask extends BukkitRunnable {
	
	private XFaction faction;
	private Chunk c;
	private Spawners spawners;
	
	public ChunkValueTask(XFaction faction, Chunk c) {
		this.faction = faction;
		this.c = c;
		spawners = Spawners.getInstance();
	}
	
	@Override
	public void run() {
		if (c != null && faction != null) {
			for (int x = 0; x < 16; x++) {
				for (int y = 0; y < 255; y++) {
					for (int z = 0; z < 16; z++) {
						if (c.getBlock(x, y, z).getType() == Material.MOB_SPAWNER) {
							Block b = c.getBlock(x, y, z);
							if (b instanceof CreatureSpawner) {
								CreatureSpawner creatureSpawner = (CreatureSpawner) b;
								if (spawners.prices.keySet().contains(creatureSpawner.getSpawnedType())) {
									faction.setValue(faction.getValue() + spawners.prices.get(creatureSpawner.getSpawnedType()));
								}
							}
						}
					}
				}
			}
		}
	}
}
