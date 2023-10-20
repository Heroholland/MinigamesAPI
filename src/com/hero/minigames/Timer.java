package com.hero.minigames;

import org.bukkit.scheduler.BukkitRunnable;

import com.hero.minigames.events.GameUpdateEvent;
import com.hero.minigames.events.TimerSecondEvent;

public class Timer extends BukkitRunnable {

	private Arena arena;
	private Timeline timeline;
	private int time;
	
	public Timer(Arena arena) {
		this.arena = arena;
		this.time = 0;
		this.timeline = null;
	}

	@Override
	public void run() {
		//+1 SECOND TO TIMER
		this.time += 1;
		arena.getEventManager().timerSecond(new TimerSecondEvent(this));
		if (this.timeline != null) {
			for (String title : timeline.getTimeline().keySet()) {
				int et = timeline.getEventTime(title);
				if (time == et) {
					arena.getEventManager().timelineUpdate(new GameUpdateEvent(this));
				}
			}
		}
	}
	
	public void start() {
		this.runTaskTimer(arena.getPlugin(), 0L, 20L);
	}
	
	public void stop() {
		resetTime();
		this.cancel();
	}
	
	public Arena getArena() {
		return arena;
	}

	public void setArena(Arena arena) {
		this.arena = arena;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int timer) {
		this.time = timer;
	}

	public void resetTime() {
		this.time = 0;
	}

	public Timeline getTimeline() {
		return timeline;
	}

	public void setTimeline(Timeline timeline) {
		this.timeline = timeline;
	}

}
