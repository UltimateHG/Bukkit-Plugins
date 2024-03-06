package org.ultimatehg;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class PCompassCmdExecutor implements CommandExecutor {
	
	private boolean hasCompass = false;
	private PCompassListener listener;
	private PCompassPlayerDeath listener2;
	
	public Player getPlayer(String name) {
		for(Player player: Bukkit.getOnlinePlayers()) {
			if(player.getName().equalsIgnoreCase(name)) return player;
		}
		return null;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		if(cmd.getName().equalsIgnoreCase("playercompass")) {
			
			if(getPlayer(args[0])==null) {
				sender.sendMessage("Tracked player does not exist or is offline!");
				return true;
			}
			
			if(!(sender instanceof Player)) {
				Bukkit.getServer().broadcastMessage(ChatColor.RED+"Error occured, check server logs.");
				return false;
			}
			
			PlayerInventory inven = ((Player)sender).getInventory();
			hasCompass = false;
			for(ItemStack i: inven) {
				if(i!=null && i.getType()==Material.COMPASS && i.getItemMeta().getLore().get(0).equals("Player Compass")) {
					hasCompass = true;
					break;
				}
			}
			if(hasCompass) {
				sender.sendMessage("You already have a player compass!");
				return true;
			}
			if(!hasCompass) {
				ItemStack compass = new ItemStack(Material.COMPASS);
				ItemMeta compassMeta = compass.getItemMeta();
				compassMeta.setDisplayName("Nigga finder");
				List<String> metatags = new ArrayList<String>();
				metatags.add("Player Compass");
				compassMeta.setLore(metatags);
				compass.setItemMeta(compassMeta);
				inven.addItem(compass);
				((Player)sender).setCompassTarget(getPlayer(args[0]).getLocation());
				sender.sendMessage(ChatColor.GREEN+"Compass given and tracked "+getPlayer(args[0]).getName()+"'s last seen position!");
				listener = new PCompassListener(getPlayer(args[0]).getUniqueId(), ((Player)sender).getUniqueId());
				listener2 = new PCompassPlayerDeath(((Player)sender).getUniqueId());
				Bukkit.getPluginManager().registerEvents(listener, playerCompass.getPlugin(playerCompass.class));
				Bukkit.getPluginManager().registerEvents(listener2, playerCompass.getPlugin(playerCompass.class));
			}
		}
		
		if(cmd.getName().equalsIgnoreCase("delcompass")) {
			if(!(sender instanceof Player)) {
				Bukkit.getServer().broadcastMessage(ChatColor.RED+"Error occured, check server logs.");
				return false;
			}
			
			PlayerInventory inven = ((Player)sender).getInventory();
			hasCompass = false;
			for(ItemStack i: inven) {
				if(i!=null && i.getType()==Material.COMPASS && i.getItemMeta().getLore().get(0).equals("Player Compass")) {
					hasCompass = true;
					break;
				}
			}
			
			if(!hasCompass) {
				sender.sendMessage("You don't have a compass!");
				return true;
			}
			
			if(hasCompass) {
				HandlerList.unregisterAll();
				ItemStack compass = new ItemStack(Material.COMPASS);
				ItemMeta compassMeta = compass.getItemMeta();
				compassMeta.setDisplayName("Nigga finder");
				List<String> metatags = new ArrayList<String>();
				metatags.add("Player Compass");
				compassMeta.setLore(metatags);
				compass.setItemMeta(compassMeta);
				inven.removeItem(compass);
				sender.sendMessage("Deleted!");
			}
		}
		
		return false;
	}

}
