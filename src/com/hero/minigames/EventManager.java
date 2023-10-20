package com.hero.minigames;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.hero.minigames.events.CustomEvent;
import com.hero.minigames.events.GameEndEvent;
import com.hero.minigames.events.GameStartEvent;
import com.hero.minigames.events.GameUpdateEvent;
import com.hero.minigames.events.TimerSecondEvent;

public class EventManager {
	
	public void gameStart(GameStartEvent e) {
		
	}
	
	public void gameEnd(GameEndEvent e) {
		
	}
	
	public void gameUpdate(GameUpdateEvent e) {
		
	}
	
	public void timelineUpdate(GameUpdateEvent e) {
		
	}
	
	public void customEvent(CustomEvent e) {
		
	}
	
	public void timerSecond(TimerSecondEvent e) {
		
	}
	
	public void playerJoin(PlayerJoinEvent e) {
		
	}
	
	public void playerQuit(PlayerQuitEvent e) {
		
	}
	
	public void playerDeath(PlayerDeathEvent e) {
		
	}
	
	public boolean playerPlaceBlock(BlockPlaceEvent e) {
		return false; //CANCELLED
	}
	
	public boolean playerBreakBlock(BlockBreakEvent e) {
		return false; //CANCELLED
	}
	
	public boolean playerCommand(PlayerCommandPreprocessEvent e) {
		return false; //CANCELLED
	}
	
	public boolean playerChat(AsyncPlayerChatEvent e) {
		return false; //CANCELLED
	}
	
	public boolean playerMove(PlayerMoveEvent e) {
		return false; //CANCELLED
	}
	
	public boolean playerInteract(PlayerInteractEvent e) {
		return false; //CANCELLED
	}
}
