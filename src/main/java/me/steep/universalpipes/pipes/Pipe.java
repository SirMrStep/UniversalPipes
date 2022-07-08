package me.steep.universalpipes.pipes;

import me.steep.universalpipes.utils.CustomSkull;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("all")
public class Pipe {

    Block block;

    public Pipe(Block block) {
        this.block = block;
    }

    public static <V extends Pipe> V createPipe(Block block, PipeType type) {

        if(type.get().equals(ItemPipe.class)) {

            return (V) new ItemPipe(block);

        }

        if(type.get().equals(LiquidPipe.class)) {

            return (V) new LiquidPipe(block);

        }

        return (V) new ItemPipe(block);

    }

    public void suck(Block block) {

    }

    public void spit(Block block) {

    }

    @Nullable
    public Block getBlock() {
        return this.block;
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

        HORIZONTAL("horizontal"),
        VERTICAL("vertical"),
        ELBOW_VERTICAL("elbow_vertical"),
        ELBOW_HORIZONTAL("elbow_horizontal");

        private ItemStack item;

        PipeTexture(String type) {

            switch (type) {

                case "horizontal" -> {
                    this.item = CustomSkull.getCustomSkull("940aa41ee6a6de25e13aad3ff1f431da9630fe39ffecf93f21d1f0c43b65773f");
                }

                 case "vertical" -> {
                    this.item = CustomSkull.getCustomSkull("444b3665b9a34137f65f2007ea6f5c4bd80b6a478956edc957fdc57ec975cf18");
                 }

                 case "elbow_vertical" -> {
                    this.item = CustomSkull.getCustomSkull("bcfbbce517b6fa15064d82100877428184c033b98820fe7526715a7d7636024");
                 }

                 case "elbow_horizontal" -> {
                    this.item = CustomSkull.getCustomSkull("a54a7c98414717da231f946660d44aad8203db6d51dfd7102a32b264fa4f14e4");
                 }

            }

        }

        ItemStack get() {
            return item != null ? item : new ItemStack(Material.AIR);
        }

    }

}
