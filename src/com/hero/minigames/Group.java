package com.hero.minigames;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Group {
	
	private List<Player> members;
	
	public Group() {
		this.members = new ArrayList<Player>();
	}
	
	public Group(List<Player> members) {
		this.members = members;
	}
	
	public List<Player> getMembers() {
		return this.members;
	}
	
	public void addMember(Player member) {
		if (!this.members.contains(member)) {
			this.members.add(member);
		}
	}
	
	public void removeMember(Entity member) {
		if (this.members.contains(member)) {
			this.members.remove(member);
		}
	}

	public void setMembers(List<Player> members) {
		this.members = members;
	}
	
	public void clear() {
		this.members.clear();
	}
	
}
