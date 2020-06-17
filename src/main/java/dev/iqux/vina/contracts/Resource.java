package dev.iqux.vina.contracts;

import org.bukkit.inventory.ItemStack;

public interface Resource {
    public static ItemStack setItem(ItemStack item) {
        return item;
    }

    public static boolean isValidItem(ItemStack item) {
        return false;
    }
}