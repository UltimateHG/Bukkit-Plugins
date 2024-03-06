package org.ultimatehg;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LBCCommandExecutor implements CommandExecutor {

	private ArrayList<UUID> players;
	private LBCBlockChanger LBCBlockChanger;
	private boolean hasStarted = false;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("startlbc")) {
			if(hasStarted) {
				sender.sendMessage("LBC has already been started!");
				return true;
			}
			players = new ArrayList<UUID>();
			for(Player p: Bukkit.getOnlinePlayers()) {
				players.add(p.getUniqueId());
			}
			LBCBlockChanger = new LBCBlockChanger(players);
			LBCBlockChanger.startBlockChanger();
			hasStarted = true;
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("stoplbc")) {
			if(hasStarted) {
				Bukkit.getScheduler().cancelTasks(LaserBlockChange.getPlugin(LaserBlockChange.class));
				return true;
			}
			if(!hasStarted) {
				sender.sendMessage("LBC has not started yet!");
			}
		}
		return false;
	}

}
