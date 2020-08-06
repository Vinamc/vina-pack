package dev.iqux.vina.app.enchance;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;

import dev.iqux.vina.utils.Config;
import dev.iqux.vina.utils.Utils;
import net.md_5.bungee.api.ChatColor;

public class Armor extends Enhancement {

    protected static String loreArmor = Config.getString("enhancement.item.lore.stats_armor");
    protected static String loreHealth = Config.getString("enhancement.item.lore.stats_health");

    public static ItemStack enhanceSuccess(ItemStack item) {
        int nextLevel = getItemLevel(item) + 1;

        Double increaseArmor = Config.getDouble(
            "level.".concat(Integer.toString(nextLevel)).concat(".armor_increase")
        );

        Double increaseHealth = Config.getDouble(
            "level.".concat(Integer.toString(nextLevel)).concat(".health_increase")
        );

        if (shouldEnhanceStats("armor.health")) {
            item = setHealth(item, getBasicHealth(item), getEnhanceHealth(item) + increaseHealth);
        }

        if (shouldEnhanceStats("armor.armor")) {
            item = setArmor(item, getBasicArmor(item), getEnhanceArmor(item) + increaseArmor);
        }

        item = setNameItemLevel(item, nextLevel);
        item = setItemLevel(item, nextLevel);

        return item;
    }

    public static ItemStack enhanceFailed(ItemStack item) {
        int level     = getItemLevel(item);
        int downLevel = level - 1;

        Double decreaseArmor = -Config.getDouble(
            "level.".concat(Integer.toString(level)).concat(".armor_increase")
        );

        Double decreaseHealth = -Config.getDouble(
            "level.".concat(Integer.toString(level)).concat(".health_increase")
        );

        if (shouldEnhanceStats("armor.health")) {
            item = setHealth(item, getBasicHealth(item), getEnhanceHealth(item) + decreaseHealth);
        }

        if (shouldEnhanceStats("armor.armor")) {
            item = setArmor(item, getBasicArmor(item), getEnhanceArmor(item) + decreaseArmor);
        }

        item = setNameItemLevel(item, downLevel);
        item = setItemLevel(item, downLevel);

        return item;
    }

    public static Double getBasicHealth(ItemStack item) {
        return getStats(item, "health.basic");
    }

    public static Double getEnhanceHealth(ItemStack item) {
        return getStats(item, "health.enhance");
    }

    public static Double getBasicArmor(ItemStack item) {
        return getStats(item, "armor.basic");
    }

    public static Double getEnhanceArmor(ItemStack item) {
        return getStats(item, "armor.enhance");
    }

    public static Double getTotalArmor(ItemStack item) {
        return getBasicArmor(item) + getEnhanceArmor(item);
    }

    public static Double getTotalHealth(ItemStack item) {
        return getBasicHealth(item) + getEnhanceHealth(item);
    }

    public static ItemStack setHealth(ItemStack item, Double basicValue, Double enhanceValue) {

        item = setStats(item, "health.basic", basicValue);
        item = setStats(item, "health.enhance", enhanceValue);

        List<String> lores = Utils.getItemLore(item);

        if (lores == null) {
            lores = new ArrayList<String>();
            lores.add(replaceLore(Utils.color(loreHealth), basicValue, enhanceValue));
            return Utils.setItemLore(item, lores);
        }

        int i;
        String regexMatch   = replaceLorePattern(loreHealth);
        Boolean hasMatchLore = false;

        for (i = 0; i < lores.size(); i++) {
            if (ChatColor.stripColor(lores.get(i)).matches(regexMatch)) {
                hasMatchLore = true; break;
            }
        }

        if (!hasMatchLore) {
            lores.add(replaceLore(Utils.color(loreHealth), basicValue, enhanceValue));
            return Utils.setItemLore(item, lores);
        }

        lores.set(i, replaceLore(Utils.color(loreHealth), basicValue, enhanceValue));
        return Utils.setItemLore(item, lores);
    }

    public static ItemStack setArmor(ItemStack item, Double basicValue, Double enhanceValue) {

        item = setStats(item, "armor.basic", basicValue);
        item = setStats(item, "armor.enhance", enhanceValue);

        List<String> lores = Utils.getItemLore(item);

        if (lores == null) {
            lores = new ArrayList<String>();
            lores.add(replaceLore(Utils.color(loreArmor), basicValue, enhanceValue));
            return Utils.setItemLore(item, lores);
        }

        int i;
        String regexMatch   = replaceLorePattern(loreArmor);
        Boolean hasMatchLore = false;

        for (i = 0; i < lores.size(); i++) {
            if (ChatColor.stripColor(lores.get(i)).matches(regexMatch)) {
                hasMatchLore = true; break;
            }
        }

        if (!hasMatchLore) {
            lores.add(replaceLore(Utils.color(loreArmor), basicValue, enhanceValue));
            return Utils.setItemLore(item, lores);
        }

        lores.set(i, replaceLore(Utils.color(loreArmor), basicValue, enhanceValue));

        return Utils.setItemLore(item, lores);
    }

    private static String replaceLorePattern(String lore) {
        lore = ChatColor.stripColor(Utils.color(lore));
        lore = Utils.pregQuote(lore);

        return lore
        .replace("%basic_armor%", "([0-9.]+)")
        .replace("%basic_health%", "([0-9.]+)")
        .replace("%enhance_armor%", "([0-9.]+)")
        .replace("%enhance_health%", "([0-9.]+)");
    }

    private static String replaceLore(String lore, Double basicValue, Double enhanceValue) {
        return lore
        .replace("%basic_armor%", Double.toString(basicValue))
        .replace("%basic_health%", Double.toString(basicValue))
        .replace("%enhance_armor%", Double.toString(enhanceValue))
        .replace("%enhance_health%", Double.toString(enhanceValue));
    }
}