package com.hero.minigames;

import org.bukkit.entity.Player;

public abstract class Kit {

    protected Player player;
    protected Main plugin;

    public Kit(Player player, Main main) {
        this.player = player;
        this.plugin = main;
    }

    //Method to be overridden by specific kit classes
    public abstract void giveItems();

    //Method to be overridden by specific kit classes
    public abstract void activateAbility();
}
