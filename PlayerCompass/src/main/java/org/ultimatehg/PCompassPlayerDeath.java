package org.ultimatehg;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PCompassPlayerDeath implements Listener {

	private UUID killer;
	
	public PCompassPlayerDeath(UUID killer) {
		this.killer = killer;
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		if(e.getPlayer().getUniqueId().equals(killer)) {
			ItemStack compass = new ItemStack(Material.COMPASS);
			ItemMeta compassMeta = compass.getItemMeta();
			compassMeta.setDisplayName("Nigga finder");
			List<String> metatags = new ArrayList<String>();
			metatags.add("Player Compass");
			compassMeta.setLore(metatags);
			compass.setItemMeta(compassMeta);
			e.getPlayer().getInventory().addItem(compass);
		}
	}
	
}
