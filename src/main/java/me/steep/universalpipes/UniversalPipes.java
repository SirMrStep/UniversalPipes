package me.steep.universalpipes;

import me.steep.universalpipes.commands.PipeCommand;
import me.steep.universalpipes.handlers.DataHandler;
import me.steep.universalpipes.listeners.PipeListener;
import me.steep.universalpipes.listeners.PlayerChunkListener;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class UniversalPipes extends JavaPlugin {

    private static NamespacedKey pipeKey;
    private static NamespacedKey chunkKey;
    private static NamespacedKey entityKey;

    @Override
    public void onEnable() {
        this.initialize();
    }

    private void initialize() {

        this.initializeCommands();
        this.initializeListeners(Bukkit.getPluginManager(), this);
        this.initializeKeys(this);

        DataHandler.register(this);

    }

    private void initializeListeners(PluginManager pm, UniversalPipes instance) {
        pm.registerEvents(new PipeListener(), instance);
        pm.registerEvents(new PlayerChunkListener(), instance);
    }

    private void initializeCommands() {
        getCommand("getpipes").setExecutor(new PipeCommand());
    }

    private void initializeKeys(UniversalPipes instance) {
        pipeKey = new NamespacedKey(instance, "pipe");
        chunkKey = new NamespacedKey(instance, "pipe_area");
        entityKey = new NamespacedKey(instance, "entities");
    }

    private static UniversalPipes instance;

    @Override
    public void onLoad() {
        instance = this;
    }

    public static UniversalPipes getInst() {
        return instance;
    }

    public static NamespacedKey getPipeKey() {
        return pipeKey;
    }

    public static NamespacedKey getChunkKey() {
        return chunkKey;
    }

    public static NamespacedKey getEntityKey() {
        return entityKey;
    }
}
