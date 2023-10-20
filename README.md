# MinigamesAPI

## A hugely helpful and easy to use library for efficiently crafting Minecraft Spigot minigames with minimal effort.

## Sample Minigame:

## Hot Potato Minigame

A simple minigame using the Minigames API.

### Concept

- Players join the arena.
- Game starts and a random player holds the "Hot Potato".
- Every 5 seconds, the potato holder takes damage.
- Pass the potato by right-clicking another player.
- If health reaches zero, the player is out.
- Last player standing wins.

### Code Implementation

### Main.java

```java
import com.hero.minigames.Main;
import com.hero.minigames.Arena;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private Minigame hotPotatoMinigame;

    @Override
    public void onEnable() {
        hotPotatoMinigame = new Minigame("Hot Potato");
        
        Arena arena = new Arena(new HotPotatoGame(hotPotatoMinigame), "Hot Potato Arena", "HotPotato", 2, 10, 0, this);
        // Note: Setup the arena boundaries, teams, etc. here
        
        hotPotatoMinigame.addArena(arena);
        
        System.out.println("Hot Potato Minigame is now enabled!");
    }
}
```

### HotPotatoGame.java

```java
import com.hero.minigames.Arena;
import com.hero.minigames.events.GameStartEvent;
import com.hero.minigames.events.GameUpdateEvent;
import com.hero.minigames.events.PlayerInteractEvent;
import com.hero.minigames.EventManager;
import com.hero.minigames.Minigame;
import org.bukkit.entity.Player;
import java.util.Random;

public class HotPotatoGame extends EventManager {
    private Minigame minigame;
    private Arena arena;
    private Player potatoHolder;
    private int damageInterval = 5;  // in seconds
    private int elapsedSeconds = 0;
    
    public HotPotatoGame(Minigame minigame) {
        this.minigame = minigame;
    }

    @Override
    public void gameStart(GameStartEvent e) {
        // Randomly choose a player to hold the potato
        Random rand = new Random();
        List<Player> players = arena.getPlayers();
        potatoHolder = players.get(rand.nextInt(players.size()));
        potatoHolder.sendMessage("You have the hot potato! Pass it quickly!");
    }

    @Override
    public void gameUpdate(GameUpdateEvent e) {
        elapsedSeconds++;
        if (elapsedSeconds % damageInterval == 0 && potatoHolder != null) {
            potatoHolder.damage(2.0); // Deal damage to the potato holder
            arena.AnnounceAllExcept(potatoHolder.getName() + " is holding the hot potato!", potatoHolder);
        }
    }

    @Override
    public boolean playerInteract(PlayerInteractEvent e) {
        // Check if the player right-clicks another player with the potato
        if (e.getPlayer() == potatoHolder && e.getClickedEntity() instanceof Player) {
            Player newHolder = (Player) e.getClickedEntity();
            potatoHolder.sendMessage("You passed the hot potato to " + newHolder.getName());
            potatoHolder = newHolder;
            newHolder.sendMessage("You now have the hot potato! Pass it quickly!");
            return true; // Cancel the event
        }
        return false;
    }
}
```
