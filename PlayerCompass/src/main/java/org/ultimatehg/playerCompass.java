package org.ultimatehg;

import org.bukkit.plugin.java.JavaPlugin;

public class playerCompass extends JavaPlugin {
	
	private PCompassCmdExecutor executor = new PCompassCmdExecutor();
	
	@Override
	public void onEnable() {
		getCommand("playercompass").setExecutor(executor);
		getCommand("delcompass").setExecutor(executor);
	}
	
	@Override
	public void onDisable() {
		
	}
	
}
