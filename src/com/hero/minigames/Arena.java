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
	private Location arenaMin, arenaMax;
	private Map<Player, Integer> deaths;
	private HashMap<Player, Kit> kits;
	private Map<String, List<Player>> teamQueue;
	
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
		this.kits = new HashMap<Player, Kit>();
		this.deaths = new HashMap<Player, Integer>();
		this.teamQueue = new HashMap<>();
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
	
	public void AnnounceAllExcept(String message, Player exception) {
		for (Player player : this.players.getMembers()) {
			if (player != exception) {
				player.sendMessage(message);
			}
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
	
	public void shuffleTeams() {
        for (Map.Entry<String, List<Player>> entry : teamQueue.entrySet()) {
            String teamName = entry.getKey();
            List<Player> queuedPlayers = entry.getValue();

            Team team = teams.get(teamName);
            for (Player player : queuedPlayers) {
                team.addMember(player);
                players.removeMember(player);
            }
        }
        teamQueue.clear();

        while (!players.getMembers().isEmpty()) {
            Player player = players.getMembers().get(0);

            Team leastFilledTeam = null;
            int minSize = Integer.MAX_VALUE;
            for (Team t : teams.values()) {
                if (t.getMembers().size() < minSize) {
                    minSize = t.getMembers().size();
                    leastFilledTeam = t;
                }
            }

            if (leastFilledTeam != null) {
                leastFilledTeam.addMember(player);
                players.removeMember(player);
            }
        }
    }
	
	public void addToTeamQueue(Player player, String teamName) {
        teamQueue.putIfAbsent(teamName, new ArrayList<>());
        teamQueue.get(teamName).add(player);
    }
	
	public void removeFromTeamQueue(Player player, String teamName) {
        if (teamQueue.containsKey(teamName)) {
            teamQueue.get(teamName).remove(player);
        }
    }
	
	 public List<Player> getTeamQueue(String teamName) {
        return teamQueue.getOrDefault(teamName, new ArrayList<>());
	 }
	
	public Map<Player, Integer> getDeaths() {
		return deaths;
	}

	public void setDeaths(Map<Player, Integer> deaths) {
		this.deaths = deaths;
	}

	public Team getPlayersTeam(Player player) {
		for (Team t : this.teams.values()) {
			if (t.getMembers().contains(player)) {
				return t;
			}
		}
		return null;
	}
	
	public int getPlayersDeaths(Player player) {
		if (this.deaths.containsKey(player)) {
			return this.deaths.get(player);
		}
		return 0;
	}
	
	public void setPlayersDeaths(Player player, int amount) {
		if (this.deaths.containsKey(player)) {
			this.deaths.put(player, amount);
		}
	}
	
	public void setPlayersKit(Player player, Kit kit) {
		if (kits.containsKey(player)) {
			removePlayersKit(player, kit);
		}
		kits.putIfAbsent(player, kit);
	}
	
	public void removePlayersKit(Player player, Kit kit) {
		if (kits.containsKey(player)) {
			kits.remove(player);
		}
	}
	
	public void addPlayerDeaths(Player player) {
		if (this.deaths.containsKey(player)) {
			this.deaths.put(player, this.getDeaths().get(player) + 1);
		} else {
			this.deaths.put(player, 1);
		}
	}
	
	public String getPlayersKitTitle(Player player) {
		if (this.getKits().containsKey(player)) {
			return this.getKits().get(player).getClass().getSimpleName();
		}
		return "";
	}
	
	public Kit getPlayersKit(Player player) {
		if (this.getKits().containsKey(player)) {
			return this.getKits().get(player);
		}
		return null;
	}
	
	public Location getArenaMin() {
		return arenaMin;
	}

	public void setArenaMin(Location arenaMin) {
		this.arenaMin = arenaMin;
	}

	public Location getArenaMax() {
		return arenaMax;
	}

	public void setArenaMax(Location arenaMax) {
		this.arenaMax = arenaMax;
	}

	public HashMap<Player, Kit> getKits() {
		return kits;
	}

	public void setKits(HashMap<Player, Kit> kits) {
		this.kits = kits;
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
