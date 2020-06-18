package dev.iqux.vina.app.enchance.resources;

import org.bukkit.inventory.ItemStack;

import de.tr7zw.nbtapi.NBTItem;
import dev.iqux.vina.utils.Utils;

public class Stone extends EnhanceResource {

    protected static final String TYPE = "STONE";

    public static ItemStack setItem(ItemStack item) {
        NBTItem nbti = Utils.toNBTItem(item);

        nbti.setString("type", TYPE);

        return nbti.getItem();
    }

    public static boolean isValidItem(ItemStack item) {
        return isCurrentType(item, TYPE);
    }
}