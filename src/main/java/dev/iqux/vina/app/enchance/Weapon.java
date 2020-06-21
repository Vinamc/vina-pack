package dev.iqux.vina.app.enchance;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;

import dev.iqux.vina.utils.Config;
import dev.iqux.vina.utils.Utils;

public class Weapon extends Enhancement {

    protected static String loreDamage = Config.getString("enhancement.item.lore.stats_damage");

    public static ItemStack enhanceSuccess(ItemStack item) {
        int nextLevel = getItemLevel(item) + 1;

        Double increaseDamage = Config.getDouble(
            "level.".concat(Integer.toString(nextLevel)).concat(".armor_increase")
        );

        item = setDamage(item, getBasicDamage(item), getEnhanceDamage(item) + increaseDamage);
        item = setNameItemLevel(item, nextLevel);
        item = setItemLevel(item, nextLevel);

        return item;
    }

    public static ItemStack enhanceFailed(ItemStack item) {
        int level     = getItemLevel(item);
        int downLevel = level - 1;

        Double decreaseDamage = -Config.getDouble(
            "level.".concat(Integer.toString(level)).concat(".armor_increase")
        );

        item = setDamage(item, getBasicDamage(item), getEnhanceDamage(item) + decreaseDamage);
        item = setNameItemLevel(item, downLevel);
        item = setItemLevel(item, downLevel);

        return item;
    }

    public static ItemStack setDamage(ItemStack item, Double basicValue, Double enhanceValue) {
        item = setStats(item, "damage.basic", basicValue);
        item = setStats(item, "damage.enhance", enhanceValue);

        List<String> lores = Utils.getItemLore(item);

        if (lores == null) {
            lores = new ArrayList<String>();
            lores.add(replaceLore(Utils.color(loreDamage), basicValue, enhanceValue));
            return Utils.setItemLore(item, lores);
        }

        int i;
        String regexMatch   = replaceLorePattern(loreDamage);
        Boolean hasMatchLore = false;

        for (i = 0; i < lores.size(); i++) {
            if (lores.get(i).matches(regexMatch)) {
                hasMatchLore = true; break;
            }
        }

        if (!hasMatchLore) {
            lores.add(replaceLore(Utils.color(loreDamage), basicValue, enhanceValue));
            return Utils.setItemLore(item, lores);
        }

        lores.set(i, replaceLore(Utils.color(loreDamage), basicValue, enhanceValue));

        return Utils.setItemLore(item, lores);
    }

    public static Double getTotalDamage(ItemStack item) {
        return getBasicDamage(item) + getEnhanceDamage(item);
    }

    protected static Double getBasicDamage(ItemStack item) {
        return getStats(item, "damage.basic");
    }

    protected static Double getEnhanceDamage(ItemStack item) {
        return getStats(item, "damage.enhance");
    }

    private static String replaceLorePattern(String lore) {
        lore = Utils.color(lore);
        lore = Utils.pregQuote(lore);

        return lore
        .replace("%basic_damage%", "([0-9.]+)")
        .replace("%enhance_damage%", "([0-9.]+)");
    }

    private static String replaceLore(String lore, Double basicValue, Double enhanceValue) {
        return lore
        .replace("%basic_damage%", Double.toString(basicValue))
        .replace("%enhance_damage%", Double.toString(enhanceValue));
    }
}