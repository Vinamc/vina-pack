package dev.iqux.vina.app.enchance;

import org.bukkit.inventory.ItemStack;

import de.tr7zw.nbtapi.NBTItem;
import dev.iqux.vina.utils.Utils;

public class Enhancement {

    public static boolean isArmor(ItemStack item) {
        if (Utils.isAirItem(item)) {
            return false;
        }

        String type = item.getType().name();

        if (type.endsWith("_HELMET")     ||
            type.endsWith("_CHESTPLATE") ||
            type.endsWith("_LEGGINGS")   ||
            type.endsWith("_BOOTS")) {
            return true;
        }

        return false;
    }

    public static int getItemLevel(ItemStack item) {
        NBTItem nbti = Utils.toNBTItem(item);

        return nbti.getInteger("level");
    }

    public static ItemStack setItemLevel(ItemStack item, int level) {
        NBTItem nbti = Utils.toNBTItem(item);

        nbti.setInteger("level", level);

        return nbti.getItem();
    }
}