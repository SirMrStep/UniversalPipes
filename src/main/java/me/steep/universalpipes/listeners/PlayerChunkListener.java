package me.steep.universalpipes.listeners;

import me.steep.universalpipes.events.PlayerChunkEvents.PlayerChunkEnterEvent;
import me.steep.universalpipes.events.PlayerChunkEvents.PlayerChunkLeaveEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerChunkListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {

        if(e.isCancelled() || e.getTo() == null || e.getFrom().getChunk().equals(e.getTo().getChunk())) return;

        Bukkit.getPluginManager().callEvent(new PlayerChunkLeaveEvent(e.getPlayer(), e.getFrom().getChunk()));
        Bukkit.getPluginManager().callEvent(new PlayerChunkEnterEvent(e.getPlayer(), e.getTo().getChunk()));

    }

}
