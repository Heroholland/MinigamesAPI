package com.hero.minigames;

import java.util.List;

import org.bukkit.entity.Player;

public class Team extends Group {
	private String name;
	
	public Team(String name) {
		super();
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
