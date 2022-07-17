package me.steep.universalpipes.events.PlayerChunkEvents;

import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.world.ChunkEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerChunkLeaveEvent extends ChunkEvent {

    private final Player player;

    public PlayerChunkLeaveEvent(@NotNull Player player, @NotNull Chunk chunk) {
        super(chunk);
        this.player = player;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return new HandlerList();
    }

    public Player getPlayer() {
        return player;
    }
}
