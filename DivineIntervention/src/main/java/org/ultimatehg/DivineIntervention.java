package org.ultimatehg;

import org.bukkit.plugin.java.JavaPlugin;

public class DivineIntervention extends JavaPlugin {
	
	private PluginStartHandler pshandler = new PluginStartHandler();
	
	@Override
	public void onEnable() {
		getCommand("startdivineintervention").setExecutor(pshandler);
		getCommand("stopdivineintervention").setExecutor(pshandler);
	}
	
	@Override
	public void onDisable() {
		
	}
	
}
