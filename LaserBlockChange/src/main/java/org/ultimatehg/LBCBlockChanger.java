package org.ultimatehg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;

public class LBCBlockChanger implements Runnable {
	
	private ArrayList<UUID> players = new ArrayList<UUID>();
	private Map<UUID, Location> lastLookLocation = new HashMap<>();
	
	public LBCBlockChanger(ArrayList<UUID> playerList) {
		players = playerList;
	}
	
	public final Block getTargetBlock(Player player) {
        BlockIterator iter = new BlockIterator(player, 64);
        Block lastBlock = iter.next();
        while (iter.hasNext()) {
            lastBlock = iter.next();
            if (lastBlock.getType() == Material.AIR) {
                continue;
            }
            break;
        }
        return lastBlock;
    }
	
	public final Material getRandomBlock() {
		Material randomBlock = null;
		Random random = new Random();
		while(randomBlock == null) {
			randomBlock = Material.values()[random.nextInt(Material.values().length)];
			if(!randomBlock.isBlock() || randomBlock.name().contains("SIGN") || randomBlock.name().equals("END_CRYSTAL") || randomBlock.name().equals("END_GATEWAY") || randomBlock.name().contains("WALL_") || randomBlock.name().contains("PORTAL") || randomBlock.name().contains("SIGN") || randomBlock.name().contains("CARPET") || randomBlock.name().contains("BANNER") || randomBlock.name().contains("TRIPWIRE") || randomBlock.name().contains("STRUCTURE") || randomBlock.name().contains("COMMAND") || randomBlock.name().contains("PISTON_") || randomBlock.name().contains("_PISTON") || randomBlock.name().contains("BED") || randomBlock.name().contains("DOOR") || randomBlock.name().contains("PRESSURE_PLATE") || randomBlock.name().contains("POTTED") || randomBlock.name().contains("_GRASS") || randomBlock.name().contains("FERN") || randomBlock.name().contains("SEAGRASS") || randomBlock.name().equals("GRASS") || randomBlock.name().contains("CORAL")) {
				randomBlock = null;
			}
		}
		return randomBlock;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(UUID player: players) {
			Block targetBlock = getTargetBlock(Bukkit.getPlayer(player));
			if(lastLookLocation.isEmpty() || !lastLookLocation.containsKey(player)) {
				lastLookLocation.put(player, targetBlock.getLocation());
				continue;
			}
			
			if(lastLookLocation.get(player).getBlock().getType() == Material.AIR || lastLookLocation.get(player).getBlock().getType() == Material.BEDROCK || lastLookLocation.get(player).getBlock().getType().name().contains("PORTAL") || lastLookLocation.get(player).getBlock().getType().name().equals("END_GATEWAY") || lastLookLocation.get(player).getBlock().getType().name().equals("END_CRYSTAL") || lastLookLocation.get(player).getBlock().getType().name().equals("OBSIDIAN") || lastLookLocation.get(player).getBlock().getType().name().contains("_AIR") || lastLookLocation.get(player).getBlock().getType().name().contains("SEAGRASS") || lastLookLocation.get(player).getBlock().getType().name().contains("_GRASS") || lastLookLocation.get(player).getBlock().getType().name().contains("FERN") || lastLookLocation.get(player).getBlock().getType().name().equals("GRASS")) {
				lastLookLocation.put(player, targetBlock.getLocation());
				continue;
			}
			
			if(!(lastLookLocation.get(player).getX()==targetBlock.getX() && lastLookLocation.get(player).getY()==targetBlock.getY() && lastLookLocation.get(player).getZ()==targetBlock.getZ())) {
				lastLookLocation.get(player).getBlock().setType(getRandomBlock());
				lastLookLocation.put(player, targetBlock.getLocation());
			}
		}
	}
	
	public void startBlockChanger() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(LaserBlockChange.getPlugin(LaserBlockChange.class), this, 0L, 1L);
	}

}
