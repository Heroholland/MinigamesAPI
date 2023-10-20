package com.hero.minigames.events;

import com.hero.minigames.Arena;
import com.hero.minigames.Timer;

public class GameUpdateEvent {

	private Timer timer;
	private Arena arena;
	
	public GameUpdateEvent(Timer timer) {
		this.timer = timer;
		this.arena = timer.getArena();
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public Arena getArena() {
		return arena;
	}

	public void setArena(Arena arena) {
		this.arena = arena;
	}
	
}
