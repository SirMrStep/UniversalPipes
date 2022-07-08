package me.steep.universalpipes.pipes;

import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import me.steep.universalpipes.utils.PacketEntityBuilder;
import me.steep.universalpipes.utils.WrappedDataWatcherBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class ItemPipe extends Pipe {

    public ItemPipe(Block block) {

        super(block);

        Location entityLoc = block.getLocation();

        // DataWatcher for Entity Metadata packet
        WrappedDataWatcher watcher = new WrappedDataWatcherBuilder(new WrappedDataWatcher())
                .setInvisibility()
                .setMarker()
                .watcher();

        int radius = Bukkit.getViewDistance() * 16;
        block.getWorld().getNearbyEntities(block.getLocation(), radius, radius, radius, entity -> entity instanceof Player)
                        .forEach(ent -> {

                            Player p = (Player) ent;

                            new PacketEntityBuilder(EntityType.ARMOR_STAND, entityLoc.getX() + 0.5, entityLoc.getY() - 1.25, entityLoc.getZ() + 0.32)
                                    .addEquipment(EnumWrappers.ItemSlot.HEAD, PipeTexture.HORIZONTAL.get())
                                    .addMetaData(watcher)
                                    .spawn(p);

                            new PacketEntityBuilder(EntityType.ARMOR_STAND, entityLoc.getX() + 0.5, entityLoc.getY() - 1.25, entityLoc.getZ() + 0.68)
                                    .addEquipment(EnumWrappers.ItemSlot.HEAD, PipeTexture.HORIZONTAL.get())
                                    .addMetaData(watcher)
                                    .spawn(p);

                        });

    }

    @Override
    public void suck(Block block) {

    }

    @Override
    public void spit(Block block) {

    }

}
