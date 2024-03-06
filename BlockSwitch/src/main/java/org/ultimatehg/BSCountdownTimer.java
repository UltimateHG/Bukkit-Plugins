package org.ultimatehg;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.HandlerList;

public class BSCountdownTimer implements Runnable {
	
	Listeners listener;
	
	private int seconds;
	private int secondsLeft;
	
	public BSCountdownTimer(Listeners listener, int seconds) {
		this.listener = listener;
		this.seconds = seconds;
		this.secondsLeft = seconds;
	}
	
	@Override
	public void run() {
		if(secondsLeft < 1) {
			listener.endSequence();
			Bukkit.getScheduler().cancelTasks(blockswitch.getPlugin(blockswitch.class));
			HandlerList.unregisterAll();
		}
		
		if(secondsLeft < 11 && secondsLeft > 0) {
			Bukkit.getServer().broadcastMessage(ChatColor.YELLOW+""+ChatColor.ITALIC+secondsLeft+" seconds left to stand on the block");
		}
		
		secondsLeft--;
	}
	
	public void scheduleTimer() {
		secondsLeft = seconds;
		Bukkit.getScheduler().scheduleSyncRepeatingTask(blockswitch.getPlugin(blockswitch.class), this, 0L, 20L);
	}
	
	public void stopTimer() {
		Bukkit.getScheduler().cancelTasks(blockswitch.getPlugin(blockswitch.class));
	}
}
