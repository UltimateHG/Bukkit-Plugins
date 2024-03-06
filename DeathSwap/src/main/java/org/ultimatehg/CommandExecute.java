package org.ultimatehg;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandExecute implements CommandExecutor {

	DeathSwap main;
	
	protected static boolean hasStarted = false;
	
	private CountdownTimer timer;
	
	private UUID player1, player2;
	
	public CommandExecute(DeathSwap main) {
		this.main = main;
	}
	
	public Player getPlayer(String name) {
		for(Player player: Bukkit.getOnlinePlayers()) {
			if(player.getName().equalsIgnoreCase(name)) return player;
		}
		return null;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("deathswap")) {
			if(hasStarted) {
				sender.sendMessage("DeathSwap is currently in progress!");
				return false;
			}
			if(!hasStarted) {
				
				if(args.length < 2) {
					sender.sendMessage("Please input the usernames of both players!");
					return false;
				}
				if(args.length > 2) {
					sender.sendMessage("Please input only 2 usernames!");
					return false;
				}
				
				if(getPlayer(args[0]) == null || getPlayer(args[1]) == null) {
					sender.sendMessage("Either one or both the players inputted are not online or are invalid!");
					return false;
				} else {
					if(getPlayer(args[0]).equals(getPlayer(args[1]))) {
						sender.sendMessage("Both players cannot be the same player!");
						return false;
					}
					player1 = getPlayer(args[0]).getUniqueId();
					player2 = getPlayer(args[1]).getUniqueId();
					
					timer = new CountdownTimer(player1, player2, 300, main);
					timer.scheduleTimer();
				}
			}
		}
		
		if(cmd.getName().equalsIgnoreCase("stopdeathswap")) {
			if(!hasStarted) {
				sender.sendMessage("DeathSwap hasn't begun yet!");
				return false;
			}
			if(hasStarted) {
				timer.stopTimer();
			}
		}
		return false;
	}

}
