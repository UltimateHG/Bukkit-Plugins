package com.ultimatehg;

//import org.bukkit.Bukkit;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class MonsterDeathHandler implements Listener {
	
	@EventHandler
	public void onCreatureDeath(EntityDeathEvent e) {
		if((e.getEntity().getKiller() instanceof Player) && (e.getEntity() instanceof Monster)) {
			MonsterSpawnHandler.HEALTH_INCREASE += 1.5f;
			MonsterDamageHandler.DAMAGE_INCREASE += 0.5f;
			//Bukkit.getServer().getLogger().info("monster killed");
		}
	}
	
}
