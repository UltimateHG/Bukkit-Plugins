package com.ultimatehg;

import org.bukkit.plugin.java.JavaPlugin;

public final class mobResilience extends JavaPlugin {
	
	@Override
	public void onEnable() {
		MonsterSpawnHandler.HEALTH_INCREASE = 0;
		MonsterDamageHandler.DAMAGE_INCREASE = 0;
		getServer().getPluginManager().registerEvents(new MonsterDeathHandler(), this);
		getServer().getPluginManager().registerEvents(new MonsterSpawnHandler(), this);
		getServer().getPluginManager().registerEvents(new MonsterDamageHandler(), this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
}
