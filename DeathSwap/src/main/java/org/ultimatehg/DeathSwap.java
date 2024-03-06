package org.ultimatehg;

import org.bukkit.plugin.java.JavaPlugin;

public class DeathSwap extends JavaPlugin {
	
	private CommandExecute commandExecutor = new CommandExecute(this);
	
	@Override
	public void onEnable() {
		getCommand("deathswap").setExecutor(commandExecutor);
		getCommand("stopdeathswap").setExecutor(commandExecutor);
	}
	
	@Override
	public void onDisable() {
		
	}
	
}
