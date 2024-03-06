package org.ultimatehg;

import org.bukkit.plugin.java.JavaPlugin;

public final class blockswitch extends JavaPlugin {
	
	@Override
	public void onEnable() {
		getCommand("startblockswitch").setExecutor(new BSCommandExecute(this));
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public void printChat(String str) {
		getServer().broadcastMessage(str);
	}
	
}
