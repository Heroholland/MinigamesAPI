package com.hero.minigames.events;

import com.hero.minigames.Arena;
import com.hero.minigames.Minigame;

public class GameEndEvent {

	private Arena arena;
	
	public GameEndEvent(Arena arena) {
		this.arena = arena;
	}

	public Arena getArena() {
		return arena;
	}

	public void setArena(Arena arena) {
		this.arena = arena;
	}
	
}
