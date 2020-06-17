package dev.iqux.vina.app.enchance.resources;

import org.bukkit.inventory.ItemStack;

import de.tr7zw.nbtapi.NBTItem;
import dev.iqux.vina.contracts.Resource;
import dev.iqux.vina.utils.Utils;

abstract public class EnhanceResource implements Resource {
    protected static String TYPE = "";

    public static boolean isValidItem(ItemStack item) {
        NBTItem nbti = Utils.toNBTItem(item);

        if (nbti.getString("type") != null) {
            return nbti.getString("type").equals(TYPE);
        }

        return false;
    }
}