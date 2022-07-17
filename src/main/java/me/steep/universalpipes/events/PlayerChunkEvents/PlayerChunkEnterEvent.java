package me.steep.universalpipes.events.PlayerChunkEvents;

import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.world.ChunkEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerChunkEnterEvent extends ChunkEvent {

    private final Player player;

    public PlayerChunkEnterEvent(@NotNull Player player, @NotNull Chunk chunk) {
        super(chunk);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return new HandlerList();
    }
}
