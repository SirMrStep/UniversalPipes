package me.steep.universalpipes.utils;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.Pair;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class PacketEntityBuilder {
    private final Map<String, PacketContainer> packets = new HashMap<>();
    private final int entityID;

    public PacketEntityBuilder(EntityType type, Location location) {
        this.entityID = new Random().nextInt(Integer.MAX_VALUE / 2, Integer.MAX_VALUE);
        this.packets.put("spawn", new PacketBuilder(ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.SPAWN_ENTITY))
                .writeEntityType(type)
                .writeEntityID(this.entityID)
                .writeUUID(UUID.randomUUID())
                .writeEntityCoords(location.getX(), location.getY(), location.getZ())
                .packet());
    }

    public PacketEntityBuilder(EntityType type, double x, double y, double z) {
        this.entityID = new Random().nextInt(Integer.MAX_VALUE / 2, Integer.MAX_VALUE);
        this.packets.put("spawn", new PacketBuilder(ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.SPAWN_ENTITY))
                .writeEntityType(type)
                .writeEntityID(this.entityID)
                .writeUUID(UUID.randomUUID())
                .writeEntityCoords(x, y, z)
                .packet());
    }

    public PacketEntityBuilder setMetaData(WrappedDataWatcher watcher) {
        this.packets.put("meta", new PacketBuilder(ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.ENTITY_METADATA))
                .writeEntityID(this.entityID)
                .writeWatchableCollectionModifier(watcher)
                .packet());
        return this;
    }

    public PacketEntityBuilder addMetaData(WrappedDataWatcher watcher) {

        if(this.packets.containsKey("meta")) {
            this.packets.put("meta", new PacketBuilder(this.packets.get("meta"))
                    .writeWatchableCollectionModifier(watcher)
                    .packet());
            return this;
        }

        return setMetaData(watcher);
    }

    public PacketEntityBuilder setEquipment(List<Pair<EnumWrappers.ItemSlot, ItemStack>> list) {
        this.packets.put("equipment", new PacketBuilder(ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.ENTITY_EQUIPMENT))
                .writeEntityID(this.entityID)
                .writeSlotStackPairList(list)
                .packet());
        return this;
    }

    public PacketEntityBuilder setEquipment(EnumWrappers.ItemSlot slot, ItemStack item) {
        this.packets.put("equipment", new PacketBuilder(ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.ENTITY_EQUIPMENT))
                .writeEntityID(this.entityID)
                .writeSlotStackPairList(new Pair<>(slot, item))
                .packet());
        return this;
    }

    public PacketEntityBuilder addEquipment(List<Pair<EnumWrappers.ItemSlot, ItemStack>> list) {

        if(this.packets.containsKey("equipment")) {
            this.packets.put("equipment", new PacketBuilder(this.packets.get("equipment"))
                    .addSlotStackPairList(list)
                    .packet());
            return this;
        }

        return setEquipment(list);
    }


    public PacketEntityBuilder addEquipment(EnumWrappers.ItemSlot slot, ItemStack item) {

        if(this.packets.containsKey("equipment")) {
            this.packets.put("equipment", new PacketBuilder(this.packets.get("equipment"))
                    .addSlotStackPairList(new Pair<>(slot, item))
                    .packet());
            return this;
        }

        return setEquipment(slot, item);
    }

    public int spawn(Player player) {
        PacketUtils.sendServerPackets(player, packets.values());
        return this.entityID;
    }

}
