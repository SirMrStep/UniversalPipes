package me.steep.universalpipes.commands;

import me.steep.universalpipes.handlers.DataHandler;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PipeCommand implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player p)) return true;

        p.getInventory().addItem(DataHandler.setDataString(new ItemStack(Material.STICK, 64), "pipe", "item_pipe"));

        return true;
    }
}
