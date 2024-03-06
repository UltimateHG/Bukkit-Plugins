package org.ultimatehg;

import org.bukkit.plugin.java.JavaPlugin;

public class LaserBlockChange extends JavaPlugin {
	
	private LBCCommandExecutor executor = new LBCCommandExecutor();
	
	@Override
	public void onEnable() {
		getCommand("startlbc").setExecutor(executor);
		getCommand("stoplbc").setExecutor(executor);
	}
	
	@Override
	public void onDisable() {
		
	}
	
}
