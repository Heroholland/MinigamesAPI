package com.hero.minigames.events;

import org.bukkit.entity.Player;

import com.hero.minigames.Arena;

public class PlayerEnterEvent {

	private Arena arena;
	private Player player;
	
	public PlayerEnterEvent(Arena arena, Player player) {
		this.arena = arena;
		this.player = player;
	}

	public Arena getArena() {
		return arena;
	}

	public void setArena(Arena arena) {
		this.arena = arena;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
}
