package com.hero.minigames;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class EventListener implements Listener {
	
	JavaPlugin plugin;
	Arena arena;
	EventManager manager;
	
	public EventListener(JavaPlugin plugin, Arena arena) {
		this.plugin = plugin;
		this.arena = arena;
		this.manager = arena.getEventManager();
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		manager.playerJoin(e);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		arena.playerQuit(e.getPlayer());
		manager.playerQuit(e);
	}
	
	@EventHandler
	public void onBreakBlock(BlockBreakEvent e) {
		if (manager.playerBreakBlock(e)) {
			e.setCancelled(true);
		} else {
			arena.addBlockChanged(e.getBlock());
		}
	}
	
	@EventHandler
	public void onBreakPlace(BlockPlaceEvent e) {
		if (manager.playerPlaceBlock(e)) {
			e.setCancelled(true);
		} else {
			arena.addBlockChanged(e.getBlock());
		}
	}
	
	@EventHandler
	public void playerCommand(PlayerCommandPreprocessEvent e) {
		if (manager.playerCommand(e)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void playerChat(AsyncPlayerChatEvent e) {
		if (manager.playerChat(e)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void playerMove(PlayerMoveEvent e) {
		if (manager.playerMove(e)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void playerInteract(PlayerInteractEvent e) {
		if (manager.playerInteract(e)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void playerDeath(PlayerDeathEvent e) {
		manager.playerDeath(e);
	}
}
