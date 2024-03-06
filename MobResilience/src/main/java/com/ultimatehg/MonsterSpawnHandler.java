package com.ultimatehg;

//import org.bukkit.Bukkit;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class MonsterSpawnHandler implements Listener {
	
	protected static double HEALTH_INCREASE = 0;
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent e) {
		if((e.getEntity() instanceof Monster) && !(e.getEntity() instanceof EnderDragon)) {
			e.getEntity().resetMaxHealth();
			e.getEntity().setMaxHealth(e.getEntity().getMaxHealth()+HEALTH_INCREASE);
			e.getEntity().setHealth(e.getEntity().getMaxHealth());
			//Bukkit.getServer().getLogger().info("spawned zombie with "+e.getEntity().getHealth()+" health");
		}
		if(e.getEntity() instanceof Creeper) {
			Creeper c = (Creeper)e.getEntity();
			c.setPowered(true);
		}
	}
	
}
