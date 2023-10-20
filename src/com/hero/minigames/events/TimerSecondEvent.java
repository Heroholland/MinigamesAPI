package com.hero.minigames.events;

import com.hero.minigames.Timer;

public class TimerSecondEvent{
	
	Timer timer;

	public TimerSecondEvent(Timer timer) {
		this.timer = timer;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

}
