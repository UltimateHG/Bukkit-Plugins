package org.ultimatehg;

import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BSCommandExecute implements CommandExecutor {
	
	blockswitch main;
	
	private UUID player1, player2;
	
	public BSCommandExecute(blockswitch main) {
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
		if(cmd.getName().equalsIgnoreCase("startblockswitch")) {
			Material material1 = null;
			Material material2 = null;
			//Material material3 = null;
			String selectedMat = null;
			Random random = new Random();
			
			//*escape cases*//
			//no username
			if(args.length < 2) {
				sender.sendMessage("Please input the usernames of both players!");
				return false;
			}
			//more than 2 usernames
			if(args.length > 2) {
				sender.sendMessage("Please input only 2 usernames!");
				return false;
			}
			//invalid usernames
			if(getPlayer(args[0]) == null || getPlayer(args[1]) == null) {
				sender.sendMessage("Either one or both the players inputted are not online or are invalid!");
				return false;
			}
			//same input for both usernames
			if(getPlayer(args[0]).getName().equals(getPlayer(args[1]).getName())) {
				sender.sendMessage("Both players cannot be the same!");
				return false;
			}

			/*do the thing for the plugin*/
			player1 = getPlayer(args[0]).getUniqueId();
			player2 = getPlayer(args[1]).getUniqueId();
			//player3 = getPlayer(args[2]).getUniqueId();
			
			selectedMat = Listeners.blockList[random.nextInt(Listeners.blockList.length)];
			material1 = Material.getMaterial(selectedMat);
			
			selectedMat = Listeners.blockList[random.nextInt(Listeners.blockList.length)];
			material2 = Material.getMaterial(selectedMat);

			//selectedMat = Listeners.blockList[random.nextInt(Listeners.blockList.length)];
			//material3 = Material.getMaterial(selectedMat);
			
			main.printChat(ChatColor.GREEN+Bukkit.getPlayer(player1).getName()+" step on "+material1.toString().replace('_', ' '));
			main.printChat(ChatColor.GREEN+Bukkit.getPlayer(player2).getName()+" step on "+material2.toString().replace('_', ' '));
			//main.printChat(ChatColor.GREEN+Bukkit.getPlayer(player3).getName()+" step on "+material3.toString().replace('_', ' '));
			
			main.getServer().getPluginManager().registerEvents(new Listeners(material1, material2, player1, player2, main), main);
			//main.getServer().getPluginManager().registerEvents(new Listeners(material1, material2, material3, player1, player2, player3, main), main);
		}
		return false;
	}
	
}
