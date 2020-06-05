package dev.iqux.rpgpack.utils;

import org.bukkit.Material;
import org.bukkit.Sound;
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

    public static void giveBackItem(Player p, ItemStack item) {

        if (Utils.isAirItem(item)) {
            return;
        }

        if (p.getInventory().firstEmpty() != -1) {
            p.getInventory().addItem(item);
        } else {
            p.getWorld().dropItem(p.getLocation(), item);
        }
    }

    public static void playSound(Player p, Sound sound) {
        p.getWorld().playSound(p.getLocation(), sound, 5, 1);
    }
}