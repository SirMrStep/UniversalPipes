package me.steep.universalpipes;

import me.steep.universalpipes.commands.PipeCommand;
import me.steep.universalpipes.handlers.DataHandler;
import me.steep.universalpipes.listeners.PipeListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class UniversalPipes extends JavaPlugin {

    @Override
    public void onEnable() {
        this.initialize();
    }

    private void initialize() {

        this.initializeCommands();
        this.initializeListeners(Bukkit.getPluginManager(), this);

        DataHandler.register(this);

    }

    private void initializeListeners(PluginManager pm, UniversalPipes instance) {
        pm.registerEvents(new PipeListener(), instance);
    }

    private void initializeCommands() {
        getCommand("getpipes").setExecutor(new PipeCommand());
    }

}
