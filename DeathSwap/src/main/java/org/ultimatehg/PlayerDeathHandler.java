package org.ultimatehg;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathHandler implements Listener {
	
	private CountdownTimer timer;
	
	private UUID player1 = null, player2 = null;
	
	public PlayerDeathHandler(UUID player1, UUID player2, CountdownTimer timer) {
		this.player1 = player1;
		this.player2 = player2;
		this.timer = timer;
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		Player player = (Player) e.getEntity();
		if(CommandExecute.hasStarted && player.getName().equals(Bukkit.getPlayer(player1).getName())) {
			timer.stopTimer(player2);
		}
		if(CommandExecute.hasStarted && player.getName().equals(Bukkit.getPlayer(player2).getName())) {
			timer.stopTimer(player1);
		}
	}
	
}
