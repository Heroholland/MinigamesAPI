package com.hero.minigames;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.hero.minigames.events.GameEndEvent;
import com.hero.minigames.events.GameStartEvent;

public class Minigame {
	
	private List<Arena> arenas;
	private String title, prefix;
	
	public Minigame(String title) {
		this.title = title;
		this.arenas = new ArrayList<Arena>();
	}

	public List<Arena> getArenas() {
		return arenas;
	}

	public void setArenas(List<Arena> arenas) {
		this.arenas = arenas;
	}
	
	public void addArena(Arena arena) {
		if (!arenas.contains(arena)) {
			arenas.add(arena);
		}
	}
	
	public Arena getArena(String title) {
		for (Arena a : arenas) {
			if (a.getTitle().equals(title)) {
				return a;
			}
		}
		return null;
	}
	
	public void removeArena(Arena arena) {
		if (arenas.contains(arena)) {
			arenas.remove(arena);
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
}
