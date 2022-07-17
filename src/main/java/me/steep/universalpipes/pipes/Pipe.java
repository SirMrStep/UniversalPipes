package me.steep.universalpipes.pipes;

import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import com.jeff_media.customblockdata.CustomBlockData;
import com.jeff_media.morepersistentdatatypes.DataType;
import me.steep.universalpipes.UniversalPipes;
import me.steep.universalpipes.utils.CustomSkull;
import me.steep.universalpipes.utils.PacketEntityBuilder;
import me.steep.universalpipes.utils.WrappedDataWatcherBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("all")
public class Pipe {

    Block block;

    public Pipe(Block block) {
        this.block = block;
    }

    public void suck(Block block) {

    }

    public void spit(Block block) {

    }

    @Nullable
    public Block getBlock() {
        return this.block;
    }

    public static <V extends Pipe> V createPipe(Block block, PipeType type) {

        V pipe = null;
        String start = null;

        if(type == PipeType.ITEM_PIPE) {

            pipe = (V) new ItemPipe(block);
            start = "ITEM_";

        }

        if(type == PipeType.LIQUID_PIPE) {

            pipe = (V) new LiquidPipe(block);
            start = "LIQUID_";

        }

        Location entityLoc = block.getLocation();

        // DataWatcher for Entity Metadata packet
        WrappedDataWatcher watcher = new WrappedDataWatcherBuilder(new WrappedDataWatcher())
                .setInvisibility()
                .setMarker()
                .watcher();

        int radius = Bukkit.getViewDistance() * 16;

        NamespacedKey entKey = UniversalPipes.getEntityKey();
        CustomBlockData blockData = new CustomBlockData(block, UniversalPipes.getInst());

        block.setType(Material.BARRIER);

        block.getWorld().getNearbyEntities(block.getLocation(), radius, radius, radius, entity -> entity instanceof Player)
                .forEach(ent -> {

                    Player p = (Player) ent;

                    // Save entity's onto player who is viewing them
                    String entities = blockData.has(entKey, DataType.STRING) ? p.getPersistentDataContainer().get(entKey, DataType.STRING) : "";
                    blockData.set(entKey, DataType.STRING,

                            String.join(", ",

                                    (entities.length() > 0 ? ", " : "") +

                                    new PacketEntityBuilder(EntityType.ARMOR_STAND, entityLoc.getX() + 0.5, entityLoc.getY() - 1.25, entityLoc.getZ() + 0.32)
                                            .addEquipment(EnumWrappers.ItemSlot.HEAD, PipeTexture.ITEM_HORIZONTAL.get())
                                            .addMetaData(watcher)
                                            .spawn(p) + "",

                                    new PacketEntityBuilder(EntityType.ARMOR_STAND, entityLoc.getX() + 0.5, entityLoc.getY() - 1.25, entityLoc.getZ() + 0.68)
                                            .addEquipment(EnumWrappers.ItemSlot.HEAD, PipeTexture.ITEM_HORIZONTAL.get())
                                            .addMetaData(watcher)
                                            .spawn(p) + ""

                            ));

                    blockData.set(UniversalPipes.getPipeKey(), DataType.STRING, type.toString());

                });

        return pipe;

    }

    public enum PipeType {

        ITEM_PIPE(ItemPipe.class),
        LIQUID_PIPE(LiquidPipe.class);

        private final Class<? extends Pipe> clazz;

        PipeType(Class<? extends Pipe> c) {
            this.clazz = c;
        }

        Class<? extends Pipe> get() {
            return this.clazz;
        }

    }

    public enum PipeTexture {

        ITEM_HORIZONTAL("item_horizontal"),
        ITEM_VERTICAL("item_vertical"),
        ITEM_ONE_SIDED("item_one_sided");

        private ItemStack item;

        PipeTexture(String type) {

            switch (type) {

                case "item_horizontal" -> {
                    this.item = CustomSkull.getCustomSkull("940aa41ee6a6de25e13aad3ff1f431da9630fe39ffecf93f21d1f0c43b65773f");
                }

                 case "item_vertical" -> {
                    this.item = CustomSkull.getCustomSkull("444b3665b9a34137f65f2007ea6f5c4bd80b6a478956edc957fdc57ec975cf18");
                 }

                 case "item_one_sided" -> {
                    this.item = CustomSkull.getCustomSkull("44aa4a7c3ed090ad75becf85168739c155961b84ea45b3d2c1f62fc1df27e092");
                 }

            }

        }

        ItemStack get() {
            return item != null ? item : new ItemStack(Material.AIR);
        }

    }

}
