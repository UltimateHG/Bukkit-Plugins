package org.ultimatehg;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.HandlerList;

public class CountdownTimer implements Runnable {
	
	private DeathSwap main;
	
	private int seconds;
	private int secondsLeft;
	
	private UUID player1, player2;
	
	public CountdownTimer(UUID player1, UUID player2, int seconds, DeathSwap main) {
		this.seconds = seconds;
		this.secondsLeft = seconds;
		this.player1 = player1;
		this.player2 = player2;
		this.main = main;
	}
	
	@Override
	public void run() {
		if(secondsLeft < 1) {
			Location loc1 = Bukkit.getPlayer(player1).getLocation();
			Bukkit.getPlayer(player1).teleport(Bukkit.getPlayer(player2).getLocation());
			Bukkit.getPlayer(player2).teleport(loc1);
			Bukkit.getServer().broadcastMessage(ChatColor.DARK_RED+""+ChatColor.BOLD+"Swapped!");
			secondsLeft = seconds;
		}
		
		if(secondsLeft < 11 && secondsLeft > 0) {
			Bukkit.getServer().broadcastMessage(ChatColor.RED+""+ChatColor.BOLD+secondsLeft+" seconds before swap!");
		}
		
		secondsLeft--;
	}
	
	public void scheduleTimer() {
		secondsLeft = seconds;
		Bukkit.getServer().broadcastMessage(ChatColor.GREEN+""+ChatColor.BOLD+"Deathswap has begun! Good luck "+Bukkit.getPlayer(player1).getName()+" and "+Bukkit.getPlayer(player2).getName()+", may the best player win.");
		CommandExecute.hasStarted = true;
		Bukkit.getPluginManager().registerEvents(new PlayerDeathHandler(player1, player2, this), main);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(DeathSwap.getPlugin(DeathSwap.class), this, 0L, 20L);
	}
	
	public void stopTimer() {
		CommandExecute.hasStarted = false;
		Bukkit.getServer().broadcastMessage("Deathswap has been cancelled.");
		Bukkit.getScheduler().cancelTasks(DeathSwap.getPlugin(DeathSwap.class));
		HandlerList.unregisterAll();
	}
	
	public void stopTimer(UUID winner) {
		CommandExecute.hasStarted = false;
		Bukkit.getServer().broadcastMessage(ChatColor.GREEN+""+ChatColor.BOLD+"Deathswap has ended! "+Bukkit.getPlayer(winner).getName()+" has won!");
		Bukkit.getScheduler().cancelTasks(DeathSwap.getPlugin(DeathSwap.class));
		HandlerList.unregisterAll();
	}

}
