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

}
