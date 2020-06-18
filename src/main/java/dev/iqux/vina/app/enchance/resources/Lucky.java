package dev.iqux.vina.app.enchance.resources;

import org.bukkit.inventory.ItemStack;

import de.tr7zw.nbtapi.NBTItem;
import dev.iqux.vina.utils.Utils;

public class Lucky extends EnhanceResource {

    protected static final String TYPE = "LUCKY";

    public static ItemStack setItem(ItemStack item, Double chance) {
        NBTItem nbti = Utils.toNBTItem(item);

        nbti.setString("type", TYPE);
        nbti.setDouble("chance", chance);

        return nbti.getItem();
    }

    public static Double getChance(ItemStack item) {

        if (! isValidItem(item)) {
            return 0.0;
        }

        NBTItem nbti = Utils.toNBTItem(item);

        return nbti.getDouble("chance");
    }

    public static boolean isValidItem(ItemStack item) {
        return isCurrentType(item, TYPE);
    }
}