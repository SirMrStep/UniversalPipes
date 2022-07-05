package me.steep.universalpipes.pipes;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class Pipe {

    private final Block block;

    public Pipe(Block block) {
        this.block = block;
    }

    public void suck(Block block) {
        if(block.getType() == Material.AIR) return;
    }

    public Block getBlock() {
        return this.block;
    }

}
