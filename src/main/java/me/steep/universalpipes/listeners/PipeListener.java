package me.steep.universalpipes.listeners;

import com.jeff_media.morepersistentdatatypes.DataType;
import me.steep.universalpipes.handlers.DataHandler;
import me.steep.universalpipes.pipes.Pipe;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("all")
public class PipeListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {

        if(e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if(isAirOrNull(e.getItem())) return;
        if(!DataHandler.hasData(e.getItem(), "pipe", DataType.STRING)) return;

        Block block = e.getClickedBlock().getRelative(e.getBlockFace());

        if(block.getType() != Material.AIR) return;

        Pipe.createPipe(block, Pipe.PipeType.valueOf(DataHandler.getDataString(e.getItem(), "pipe").toUpperCase()));

    }

    private boolean isAirOrNull(ItemStack item) {
        return item == null || item.getType() == Material.AIR;
    }

}