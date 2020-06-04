package dev.iqux.rpgpack.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class User {

    public static ItemStack getPlayerHandItem(Player p) {
        ItemStack item = p.getInventory().getItemInMainHand();

        if (item != null && item.getType().name().equals(Material.AIR.name())) {
            return null;
        }

        return item;
    }
}