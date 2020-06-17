package dev.iqux.vina.app.enchance.resources;

import org.bukkit.inventory.ItemStack;

import de.tr7zw.nbtapi.NBTItem;
import dev.iqux.vina.utils.Utils;

public class Protector extends EnhanceResource {

    protected static final String TYPE = "PROTECTOR";

    public static ItemStack setItem(ItemStack item) {
        NBTItem nbti = Utils.toNBTItem(item);

        nbti.setString("type", TYPE);

        return nbti.getItem();
    }
}