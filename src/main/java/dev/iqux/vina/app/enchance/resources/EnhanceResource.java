package dev.iqux.vina.app.enchance.resources;

import org.bukkit.inventory.ItemStack;

import de.tr7zw.nbtapi.NBTItem;
import dev.iqux.vina.contracts.Resource;
import dev.iqux.vina.utils.Utils;

abstract public class EnhanceResource implements Resource {
    protected static String TYPE = "";

    protected static boolean isCurrentType(ItemStack item, String type) {

        if (Utils.isAirItem(item)) {
            return false;
        }

        NBTItem nbti = Utils.toNBTItem(item);

        if (nbti.getString("type") != null) {
            return nbti.getString("type").equals(type);
        }

        return false;
    }
}