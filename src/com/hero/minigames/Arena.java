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

public class Arena {
	private Map<String, Team> teams;
	private Group players;
	private String title, prefix;
	private int minPlayers, maxPlayers, gamemode, state;
	private Timer timer;
	private boolean countDown;
	private EventManager eManager;
	private List<Block> blocksChanged;
	private JavaPlugin plugin;
	
	public Arena(EventManager eManager, String title, String chatPrefix, int minPlayers, int maxPlayers, int gamemode, JavaPlugin plugin) {
		this.eManager = eManager;
		this.teams = new HashMap<String, Team>();
		this.players = new Group(new ArrayList<Player>());
		this.title = title;
		this.minPlayers = minPlayers;
		this.maxPlayers = maxPlayers;
		this.gamemode = gamemode;
		this.state = 0;
		this.prefix = chatPrefix;
		this.blocksChanged = new ArrayList<Block>();
		this.plugin = plugin;
		this.timer = new Timer(this);
		this.plugin.getServer().getPluginManager().registerEvents(new EventListener(this.plugin, this), this.plugin);
	}
	
	public void setEventManager(EventManager manager) {
        this.eManager = manager;
    }
	
	public void start() {
		eManager.gameStart(new GameStartEvent(this));
		timer.start();
	}
	
	public void stop() {
		eManager.gameEnd(new GameEndEvent(this));
		for (Team t : this.teams.values()) {
			t.clear();
		}
		players.clear();
		timer.stop();
	}
	
	public void annnouncePlayers(String message) {
		for (Player p : this.getPlayers()) {
			p.sendMessage(message);
		}
	}
	
	public void annnounceTeam(String team, String message) {
		Team t = teams.get(team);
		for (Player p : t.getMembers()) {
			p.sendMessage(message);
		}
	}
	
	public void teleportTeam(String team, Location location) {
		Team t = teams.get(team);
		for (Player p : t.getMembers()) {
			p.teleport(location);
		}
	}
	
	public void teleportPlayers(Location location) {
		for (Player p : this.getPlayers()) {
			p.teleport(location);
		}
	}
	
	public List<Block> getBlocksChanged() {
		return blocksChanged;
	}

	public void setBlocksChanged(List<Block> blocksChanged) {
		this.blocksChanged = blocksChanged;
	}
	
	public void addBlockChanged(Block b) {
		if (!this.blocksChanged.contains(b)) {
			this.blocksChanged.add(b);
		}
	}
	
	public void removeBlockChanged(Block b) {
		if (this.blocksChanged.contains(b)) {
			this.blocksChanged.remove(b);
		}
	}

	public void addPlayerToTeam(Player player, String team) {
		Team t = this.getTeams().get(team);
		t.addMember(player);
	}
	
	protected void playerQuit(Player player) {
		for (Team t : teams.values()) {
			t.removeMember(player);
		}
		this.removePlayer(player);
	}
	
	public void removePlayer(Player player) {
		this.players.removeMember(player);
	}
	
	public EventManager getEventManager() {
		return eManager;
	}

	public JavaPlugin getPlugin() {
		return plugin;
	}

	public void setPlayers(Group players) {
		this.players = players;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setPlugin(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	public Map<String, Team> getTeams() {
		return teams;
	}

	public void setTeams(Map<String, Team> teams) {
		this.teams = teams;
	}

	public List<Player> getPlayers() {
		return players.getMembers();
	}

	public void setPlayers(List<Player> players) {
		this.players.setMembers(players);
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

	public int getMinPlayers() {
		return minPlayers;
	}

	public void setMinPlayers(int minPlayers) {
		this.minPlayers = minPlayers;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public int getGamemode() {
		return gamemode;
	}

	public void setGamemode(int gamemode) {
		this.gamemode = gamemode;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public boolean isCountDown() {
		return countDown;
	}

	public void setCountDown(boolean countDown) {
		this.countDown = countDown;
	}
}
