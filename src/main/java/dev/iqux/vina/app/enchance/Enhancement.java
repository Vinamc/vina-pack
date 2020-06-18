package dev.iqux.vina.app.enchance;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.inventory.ItemStack;

import de.tr7zw.nbtapi.NBTItem;
import dev.iqux.vina.utils.Config;
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

    public static ItemStack setNameItemLevel(ItemStack item, int level) {

        level = level < 0 ? level * -1 : level;
        String prefixValue = level < 0 ? "-" : "+";
        String itemName = "";

        if (! item.getItemMeta().hasDisplayName() || ! Utils.getItemName(item).matches(getDisplayNamePattern()) ) {
            itemName = Utils.getItemName(item);
        } else {
            Matcher matcher = Pattern.compile(getDisplayNamePattern()).matcher(Utils.getItemName(item));
            if (matcher.find()) {
                itemName = matcher.group(1);
            }
        }

        return Utils.setItemName(item, Utils.color(
            replaceDisplayName(itemName, prefixValue, Integer.toString(level))
        ));
    }

    protected static ItemStack setItemLevel(ItemStack item, int level) {
        NBTItem nbti = Utils.toNBTItem(item);

        nbti.setInteger("level", level);

        return nbti.getItem();
    }

    protected static Double getStats(ItemStack item, String key) {
        NBTItem nbti = Utils.toNBTItem(item);

        return nbti.getDouble(key);
    }

    protected static ItemStack setStats(ItemStack item, String key, Double stats) {
        NBTItem nbti = Utils.toNBTItem(item);

        nbti.setDouble(key, stats);

        return nbti.getItem();
    }

    private static String getDisplayNamePattern() {
        String s = Config.getString("enhancement.item.name");
        s = Utils.color(s);
        s = Utils.pregQuote(s);

        return s.replace("%item_name%", "(.*?)")
        .replace("%item_level_prefix%", "(\\+|\\-)")
        .replace("%item_level%", "([0-9]+)").concat("$");
    }

    private static String replaceDisplayName(String itemName, String valuePrefix, String value) {
        String s = Config.getString("enhancement.item.name");

        return s.replace("%item_name%", itemName)
        .replace("%item_level_prefix%", valuePrefix)
        .replace("%item_level%", value);
    }
}