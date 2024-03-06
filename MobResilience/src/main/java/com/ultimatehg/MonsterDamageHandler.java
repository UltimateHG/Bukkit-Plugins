package com.ultimatehg;

import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class MonsterDamageHandler implements Listener {
	
	protected static double DAMAGE_INCREASE = 0;
	
	@EventHandler
	public void onDmg(EntityDamageByEntityEvent e) {
		if((e.getDamager() instanceof Monster) && !(e.getDamager() instanceof EnderDragon)) {
			e.setDamage(e.getDamage()+DAMAGE_INCREASE);
		}
	}
	
}
