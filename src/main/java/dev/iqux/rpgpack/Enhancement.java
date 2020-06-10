package dev.iqux.rpgpack;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import de.tr7zw.nbtapi.NBTItem;
import dev.iqux.rpgpack.utils.Config;
import dev.iqux.rpgpack.utils.Utils;

public class Enhancement {
    public static final String TYPE_ARMOR           = "ARMOR";
    public static final String TYPE_WEAPON          = "WEAPON";
    public static final String ENHANCE_STONE        = "STONE";
    public static final String ENHANCE_CHARM        = "CHARM";
    public static final String ENHANCE_STONE_RANDOM = "STONE_RANDOM";
    public static final String ENHANCE_LUCKY_POWDER = "LUCKY_POWDER";

    public static String[] getTypeEnhanceStone() {
        String[] type = {TYPE_WEAPON, TYPE_ARMOR};

        return type;
    }

    public static ItemStack setCharm(ItemStack item) {
        NBTItem nbti = new NBTItem(item);

        nbti.setString("type", ENHANCE_CHARM);

        return nbti.getItem();
    }

    public static ItemStack setStone(ItemStack item, String apply) {
        NBTItem nbti = new NBTItem(item);

        nbti.setString("type", ENHANCE_STONE);
        nbti.setString("apply", apply); // should apply stone for weapon or armor

        return nbti.getItem();
    }

    public static ItemStack setLuckyPowder(ItemStack item, Float increateRate) {
        NBTItem nbti = new NBTItem(item);

        nbti.setString("type", ENHANCE_LUCKY_POWDER);
        nbti.setFloat("rate", increateRate);

        return nbti.getItem();
    }

    public static boolean isStone(ItemStack item) {
        return is(item, "type", ENHANCE_STONE) || is(item, "type", ENHANCE_STONE_RANDOM);
    }

    public static boolean isCharm(ItemStack item) {
        return is(item, "type", ENHANCE_CHARM);
    }

    public static boolean isPowder(ItemStack item) {
        return is(item, "type", ENHANCE_LUCKY_POWDER);
    }

    protected static boolean is(ItemStack item, String key, String type) {

        if (item == null || item.getType().name().equals(Material.AIR.name())) {
            return false;
        }

        String itemType = Utils.toNBTItem(item).getString(key);

        if (itemType != null) {
            return itemType.equals(type);
        }

        return false;
    }

    public static int getIntItemKey(ItemStack item, String key) {
        if (Utils.toNBTItem(item).getInteger(key) != null) {
            return Utils.toNBTItem(item).getInteger(key);
        }

        return 0;
    }

    public static boolean isArmor(ItemStack item) {
        String type = item.getType().name();

        if (type.endsWith("_HELMET") ||
            type.endsWith("_CHESTPLATE") ||
            type.endsWith("_LEGGINGS") ||
            type.endsWith("_BOOTS")) {
            return true;
        }

        return false;
    }

    protected static ItemStack setItemNameLevel(ItemStack item, int level)
    {
        if (level < 0) {
            return item;
        }

        String levelColor = Utils.color(Config.getString("level.color")).concat(Integer.toString(level));

        if (!item.getItemMeta().hasDisplayName()) {

            return Utils.setItemName(item,
                "".concat(Utils.color("&r"))
                .concat(Utils.getItemName(item))
                .concat(" ").concat(levelColor)
            );
        }

        
        String[] name   = Utils.getItemName(item).split(" ");
        String lastName = name[name.length -1];

        if (ChatColor.stripColor(lastName).matches("^([0-9+\\[\\]])+$")) {
            name[name.length -1] = "";

            String itemName = String.join(" ", name)
            .concat(Utils.color("&r["))
            .concat(levelColor)
            .concat(Utils.color("&r]"));

            return Utils.setItemName(item, itemName);
        }

        return Utils.setItemName(item,
            Utils.getItemName(item).concat(" ").concat(levelColor)
        );
    }

    protected static boolean isMaterial(ItemStack item, Material material) {
        return Utils.isMaterial(item, material);
    }
}