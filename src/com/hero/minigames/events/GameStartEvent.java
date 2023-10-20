package com.hero.minigames.events;

import com.hero.minigames.Arena;

public class GameStartEvent {
	
	private Arena arena;
	
	public GameStartEvent(Arena arena) {
		this.arena = arena;
	}

	public Arena getArena() {
		return arena;
	}

	public void setMinigame(Arena arena) {
		this.arena = arena;
	}
	
}
