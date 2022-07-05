package me.steep.universalpipes;

import org.bukkit.plugin.java.JavaPlugin;

public class UniversalPipes extends JavaPlugin {

    @Override
    public void onEnable() {
        this.initialize();
    }

    private void initialize() {



    }

    private static UniversalPipes instance;

    @Override
    public void onLoad() {
        instance = this;
    }

    public static UniversalPipes getInst() {
        return instance;
    }
}
