package com.hero.minigames.events;

import com.hero.minigames.Arena;

public class CustomEvent {

	private Arena arena;
	private Object[] data;
		
	public CustomEvent(Arena arena, Object... data) {
		this.arena = arena;
		this.data = data;
	}

	public Arena getArena() {
		return arena;
	}

	public void setArena(Arena arena) {
		this.arena = arena;
	}

	public Object[] getData() {
		return data;
	}

	public void setData(Object[] data) {
		this.data = data;
	}
	
}
