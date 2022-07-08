package me.steep.universalpipes.utils;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.List;

public class PacketUtils {

    public static void sendServerPackets(Player player, PacketContainer... packets) {
        List.of(packets).forEach(packet -> ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet));
    }

    public static void sendServerPackets(Player player, List<PacketContainer> packets) {
        packets.forEach(packet -> ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet));
    }

    public static void sendServerPackets(Player player, Collection<PacketContainer> packets) {
        packets.forEach(packet -> ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet));
    }



    /*
    public static int spawnEntity(Player player, EntityType type, double x, double y, double z) {
        int entityID = new Random().nextInt(Integer.MAX_VALUE / 2, Integer.MAX_VALUE);
        ProtocolLibrary.getProtocolManager().sendServerPacket(player, new PacketBuilder(ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.SPAWN_ENTITY))
                .writeEntityType(type)
                .writeEntityID(entityID)
                .writeUUID(UUID.randomUUID())
                .writeEntityCoords(x, y, z)
                .packet());
        return entityID;
    }

    public static int spawnEntity(Player player, EntityType type, Location location) {
        int entityID = new Random().nextInt(Integer.MAX_VALUE / 2, Integer.MAX_VALUE);
        ProtocolLibrary.getProtocolManager().sendServerPacket(player, new PacketBuilder(ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.SPAWN_ENTITY))
                .writeEntityType(type)
                .writeEntityID(entityID)
                .writeUUID(UUID.randomUUID())
                .writeEntityCoords(location.getX(), location.getY(), location.getZ())
                .packet());
        return entityID;
    }

    public static int spawnEntity(Player player, Entity)

    public static PacketContainer createSpawnEntityPacket(EntityType type, double x, double y, double z) {
        return new PacketBuilder(ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.SPAWN_ENTITY))
                .writeEntityType(type)
                .writeEntityID(new Random().nextInt(Integer.MAX_VALUE / 2, Integer.MAX_VALUE))
                .writeUUID(UUID.randomUUID())
                .writeEntityCoords(x, y, z)
                .packet();
    }

    //public static WrappedDataWatcher

    public static PacketContainer createEntityMetaDataPacket(int entityID, WrappedDataWatcher watcher) {
        return new PacketBuilder(ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.ENTITY_METADATA))
                .writeEntityID(entityID)
                .writeWatchableCollectionModifier(watcher)
                .packet();
    }


     */
}
