package me.steep.universalpipes;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.BlockPosition;
import com.jeff_media.customblockdata.CustomBlockData;
import com.jeff_media.morepersistentdatatypes.DataType;
import me.steep.universalpipes.commands.PipeCommand;
import me.steep.universalpipes.handlers.DataHandler;
import me.steep.universalpipes.listeners.PipeListener;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class UniversalPipes extends JavaPlugin {

    private static NamespacedKey pipeKey;

    @Override
    public void onEnable() {
        this.initialize();
    }

    private void initialize() {

        this.initializeCommands();
        this.initializeListeners(Bukkit.getPluginManager(), this);

        DataHandler.register(this);

        pipeKey = new NamespacedKey(this, "pipe");

        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(this, ListenerPriority.NORMAL, PacketType.Play.Server.BLOCK_CHANGE) {

            @Override
            public void onPacketSending(PacketEvent packetEvent) {

                PacketContainer container = packetEvent.getPacket();

                BlockPosition pos = container.getBlockPositionModifier().read(0);

                CustomBlockData data = new CustomBlockData(packetEvent.getPlayer().getWorld().getBlockAt(pos.getX(), pos.getY(), pos.getZ()), getInst());

                if(data.has(pipeKey, DataType.STRING)) {
                    Bukkit.broadcastMessage("x: " + pos.getX() + ", y: " + pos.getY() + ", z: " + pos.getZ());
                }

            }

        });

    }

    private void initializeListeners(PluginManager pm, UniversalPipes instance) {
        pm.registerEvents(new PipeListener(), instance);
    }

    private void initializeCommands() {
        getCommand("getpipes").setExecutor(new PipeCommand());
    }

    private static UniversalPipes instance;

    @Override
    public void onLoad() {
        instance = this;
    }

    public static UniversalPipes getInst() {
        return instance;
    }
}
