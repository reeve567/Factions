package pw.xwy.Factions.utility.tasks;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.scheduler.BukkitRunnable;
import pw.xwy.Factions.objects.faction.XChunk;
import pw.xwy.Factions.objects.faction.XPlayerFaction;
import pw.xwy.Factions.utility.Configurations.Spawners;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class ChunkValueTask extends BukkitRunnable {
	
	private XPlayerFaction faction;
	private boolean b;
	private Chunk c;
	private int value;
	private XChunk chunk;
	private int i = 0;
	private Spawners spawners;
	
	public ChunkValueTask(XChunk c, boolean b) {
		this.faction = (XPlayerFaction) c.xFaction;
		this.c = c.chunk;
		this.b = b;
		chunk = c;
		spawners = Spawners.getInstance();
	}
	
	@Override
	public void run() {
		if (i < 16) {
			if (c != null && faction != null) {
				for (int y = 0; y < 255; y++) {
					for (int z = 0; z < 16; z++) {
						if (c.getBlock(i, y, z).getType() == Material.MOB_SPAWNER) {
							Block b = c.getBlock(i, y, z);
							if (b.getState() instanceof CreatureSpawner) {
								CreatureSpawner creatureSpawner = (CreatureSpawner) b.getState();
								if (spawners.arrayList.contains(creatureSpawner.getSpawnedType())) {
									if (this.b) {
										faction.spawners.put(creatureSpawner.getSpawnedType(), faction.spawners.getOrDefault(creatureSpawner.getSpawnedType(), 0) + 1);
										value += spawners.getPrice(creatureSpawner.getSpawnedType());
									} else {
										faction.spawners.put(creatureSpawner.getSpawnedType(), faction.spawners.getOrDefault(creatureSpawner.getSpawnedType(), 1) - 1);
									}
								}
							}
						}
					}
				}
			}
		} else {
			chunk.value = value;
			cancel();
		}
		i++;
	}
}
