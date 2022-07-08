package me.steep.universalpipes.pipes;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.Pair;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import me.steep.universalpipes.utils.PacketBuilder;
import me.steep.universalpipes.utils.PacketEntityBuilder;
import me.steep.universalpipes.utils.WrappedDataWatcherBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class ItemPipe extends Pipe {

    public ItemPipe(Block block) {

        super(block);

        ProtocolManager manager = ProtocolLibrary.getProtocolManager();

        Location entityLoc = block.getLocation();

        // DataWatcher for Entity Metadata packet
        WrappedDataWatcher watcher = new WrappedDataWatcherBuilder(new WrappedDataWatcher())
                .setObject(0,(byte) 0x20, Byte.class)
                .setObject(15, (byte) 0x10, Byte.class)
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

                            /*
                            // Armor stand spawn packet
                            manager.sendServerPacket(p, new PacketBuilder(manager.createPacket(PacketType.Play.Server.SPAWN_ENTITY))
                                    .writeEntityType(EntityType.ARMOR_STAND)
                                    .writeEntityID(entityID_1)
                                    .writeUUID(UUID.randomUUID())
                                    .writeEntityCoords(entityLoc.getX() + 0.5, entityLoc.getY() - 1.25, entityLoc.getZ() + 0.32)
                                    .packet());

                            manager.sendServerPacket(p, new PacketBuilder(manager.createPacket(PacketType.Play.Server.SPAWN_ENTITY))
                                    .writeEntityType(EntityType.ARMOR_STAND)
                                    .writeEntityID(entityID_2)
                                    .writeUUID(UUID.randomUUID())
                                    .writeEntityCoords(entityLoc.getX() + 0.5, entityLoc.getY() - 1.25, entityLoc.getZ() + 0.68)
                                    .packet());

                            // ArmorEquipment packets
                            manager.sendServerPacket(p, new PacketBuilder(manager.createPacket(PacketType.Play.Server.ENTITY_EQUIPMENT))
                                    .writeEntityID(entityID_1)
                                    .writeSlotStackPairList(list)
                                    .packet());

                            manager.sendServerPacket(p, new PacketBuilder(manager.createPacket(PacketType.Play.Server.ENTITY_EQUIPMENT))
                                    .writeEntityID(entityID_2)
                                    .writeSlotStackPairList(list)
                                    .packet());

                            // Meta packets
                            manager.sendServerPacket(p, new PacketBuilder(manager.createPacket(PacketType.Play.Server.ENTITY_METADATA))
                                    .writeEntityID(entityID_1)
                                    .writeWatchableCollectionModifier(watcher)
                                    .packet());

                            manager.sendServerPacket(p, new PacketBuilder(manager.createPacket(PacketType.Play.Server.ENTITY_METADATA))
                                    .writeEntityID(entityID_2)
                                    .writeWatchableCollectionModifier(watcher)
                                    .packet());


                             */
                        });

    }

    @Override
    public void suck(Block block) {

    }

    @Override
    public void spit(Block block) {

    }

    /*private String getModifier(BlockFace face) {

    }*/

}
