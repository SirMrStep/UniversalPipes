package me.steep.universalpipes.utils;

import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.Pair;
import com.comphenix.protocol.wrappers.WrappedAttribute;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

public record PacketBuilder(PacketContainer packet) {

    public PacketBuilder writeInt(int index, int value) {
        packet.getIntegers().write(index, value);
        return this;
    }

    public PacketBuilder writeIntList(int index, List<Integer> intList) {
        packet.getIntLists().write(index, intList);
        return this;
    }

    public PacketBuilder writeEntityType(EntityType type) {
        packet.getEntityTypeModifier().write(0, type);
        return this;
    }

    public PacketBuilder writeDouble(int index, double value) {
        packet.getDoubles().write(index, value);
        return this;
    }

    public PacketBuilder writeEntityCoords(double x, double y, double z) {
        return this.writeDouble(0, x).writeDouble(1, y).writeDouble(2, z);
    }

    public PacketBuilder writeEntityID(int id) {
        return this.writeInt(0, id);
    }

    public PacketBuilder writeUUID(UUID uuid) {
        packet.getUUIDs().write(0, uuid);
        return this;
    }

    public PacketBuilder writeSlotStackPairList(List<Pair<EnumWrappers.ItemSlot, ItemStack>> list) {
        packet.getSlotStackPairLists().write(0, list);
        return this;
    }

    public PacketBuilder writeSlotStackPairList(Pair<EnumWrappers.ItemSlot, ItemStack> pair) {
        packet.getSlotStackPairLists().write(0, List.of(pair));
        return this;
    }

    public PacketBuilder addSlotStackPairList(List<Pair<EnumWrappers.ItemSlot, ItemStack>> list) {
        packet.getSlotStackPairLists().read(0).addAll(list); //TODO check if this works
        return this;
    }

    public PacketBuilder addSlotStackPairList(Pair<EnumWrappers.ItemSlot, ItemStack> pair) {
        packet.getSlotStackPairLists().read(0).add(pair); //TODO check if this works
        return this;
    }

    public PacketBuilder writeWatchableCollectionModifier(WrappedDataWatcher watcher) {
        packet.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects());
        return this;
    }
}
