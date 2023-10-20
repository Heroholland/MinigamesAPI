package com.hero.minigames;

import java.util.HashMap;
import java.util.Map;

public class Timeline {
	//STRING NAME, INT SECONDS
	private Arena arena;
	private Map<String, Integer> tl = new HashMap<String, Integer>();
	
	public Timeline(Arena arena) {
		this.arena = arena;
	}
	
	public void addEvent(String title, int time) {
		tl.put(title, time);
	}
	
	public void removeEvent(String title) {
		tl.remove(title);
	}
	
	public int getEventTime(String title) {
		return tl.get(title);
	}

	public Arena getArena() {
		return arena;
	}

	public void setArena(Arena arena) {
		this.arena = arena;
	}

	public Map<String, Integer> getTimeline() {
		return tl;
	}

	public void setTimeline(Map<String, Integer> timeline) {
		this.tl = timeline;
	}
	
}
