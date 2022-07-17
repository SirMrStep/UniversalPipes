package me.steep.universalpipes.listeners;

import com.jeff_media.morepersistentdatatypes.DataType;
import me.steep.universalpipes.UniversalPipes;
import me.steep.universalpipes.events.PlayerChunkEvents.PlayerChunkEnterEvent;
import me.steep.universalpipes.handlers.DataHandler;
import me.steep.universalpipes.pipes.Pipe;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import javax.xml.crypto.Data;

@SuppressWarnings("all")
public class PipeListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {

        if(e.getHand() != EquipmentSlot.HAND) return; // why it launch twice
        if(e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if(isAirOrNull(e.getItem())) return;
        if(!DataHandler.hasData(e.getItem(), "pipe", DataType.STRING)) return;

        Block block = e.getClickedBlock().getRelative(e.getBlockFace());

        if(block.getType() != Material.AIR) return;

        Pipe.createPipe(block, Pipe.PipeType.valueOf(DataHandler.getDataString(e.getItem(), "pipe").toUpperCase()));

    }

    @EventHandler
    public void onChunkEnter(PlayerChunkEnterEvent e) {

        if(!e.getChunk().getPersistentDataContainer().has(UniversalPipes.getChunkKey(), DataType.LOCATION)) return;

        // TODO: check if chunk has data (already done above

    }

    private boolean isAirOrNull(ItemStack item) {
        return item == null || item.getType() == Material.AIR;
    }

}
