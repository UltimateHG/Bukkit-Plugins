package org.ultimatehg;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PCompassListener implements Listener {
	
	private UUID target, killer;
	
	public PCompassListener(UUID target, UUID killer) {
		this.target = target;
		this.killer = killer;
	}
	
	@EventHandler
	public void onRightClick(PlayerInteractEvent event) {
		if((Bukkit.getServer().getPlayer(killer).equals(event.getPlayer())) && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && event.getPlayer().getInventory().getItemInMainHand().getType()==Material.COMPASS && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore().get(0).equals("Player Compass")) {
			event.getPlayer().setCompassTarget(Bukkit.getServer().getPlayer(target).getLocation());
			event.getPlayer().sendMessage(ChatColor.GREEN+"Tracked "+Bukkit.getServer().getPlayer(target).getName()+"'s last seen location!");
		}
	}
	
}
