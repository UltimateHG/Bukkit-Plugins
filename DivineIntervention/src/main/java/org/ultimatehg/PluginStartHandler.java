package org.ultimatehg;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PluginStartHandler implements CommandExecutor {

	private boolean distarted = false;
	private ArrayList<UUID> players = new ArrayList<UUID>();
	
	private DITimer timer;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("startdivineintervention")) {
			if(distarted) {
				sender.sendMessage("Divine Intervention is already in effect!");
			}
			if(!distarted) {
				distarted = true;
				Bukkit.getServer().broadcastMessage(ChatColor.RED+""+ChatColor.BOLD+"The gods have begun interfering with your gameplay!");
				for(Player p: Bukkit.getOnlinePlayers()) {
					players.add(p.getUniqueId());
					p.getWorld().strikeLightningEffect(p.getLocation());
				}
				
				timer = new DITimer(180, players);
				timer.scheduleTimer();
			}
		}
		
		if(cmd.getName().equalsIgnoreCase("stopdivineintervention")) {
			if(!distarted) {
				sender.sendMessage("Divine Intervention is not in effect!");
			}
			if(distarted) {
				distarted = false;
				Bukkit.getServer().broadcastMessage(ChatColor.GREEN+""+ChatColor.BOLD+"The gods have stopped interfering with your gameplay!");
				timer.stopTimer();
			}
		}
		return false;
	}

}
