package dev.iqux.vina.enhancement;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import de.tr7zw.nbtapi.NBTCompoundList;
import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.NBTListCompound;
import dev.iqux.vina.Enhancement;
import dev.iqux.vina.utils.Config;
import dev.iqux.vina.utils.Utils;

public class Armor extends Enhancement
{
    public static ItemStack enhanceSuccess(ItemStack item) {
        int itemLevel      = getIntItemKey(item, "level") + 1;
        int increaseArmor = Config.getInt(
            "level.".concat(Integer.toString(itemLevel)).concat(".armor_increase")
        );

        int defaultlArmor = getIntItemKey(item, "default_armor");
        int customArmor   = getIntItemKey(item, "custom_armor");

        NBTItem nbti = Utils.toNBTItem(item);

        nbti.setInteger("level", itemLevel);
        nbti.setInteger("default_armor", defaultlArmor + increaseArmor);

        NBTCompoundList attribute = nbti.getCompoundList("AttributeModifiers");
        attribute.clear();

        NBTListCompound armor = attribute.addCompound();

        armor.setInteger("Amount", defaultlArmor + customArmor + increaseArmor + getDefaultArmor(item));
        armor.setString("AttributeName", "generic.armor");
        armor.setString("Name", "generic.armor");
        armor.setInteger("Operation", 0);
        armor.setInteger("UUIDLeast", 59664);
        armor.setInteger("UUIDMost", 31453);
        armor.setString("Slot", getPositionArmor(item));

        return setItemNameLevel(nbti.getItem(), itemLevel);
    }

    public static ItemStack enhanceFailed(ItemStack item) {
        int itemLevel      = getIntItemKey(item, "level");
        int increaseArmor  = - Config.getInt(
            "level.".concat(Integer.toString(itemLevel)).concat(".armor_increase")
        );

        int defaultlArmor = getIntItemKey(item, "default_armor");
        int customArmor   = getIntItemKey(item, "custom_armor");

        NBTItem nbti = Utils.toNBTItem(item);

        nbti.setInteger("level", itemLevel - 1);
        nbti.setInteger("default_armor", defaultlArmor + increaseArmor);

        NBTCompoundList attribute = nbti.getCompoundList("AttributeModifiers");
        attribute.clear();

        NBTListCompound armor = attribute.addCompound();

        armor.setInteger("Amount", defaultlArmor + customArmor + increaseArmor + getDefaultArmor(item));
        armor.setString("AttributeName", "generic.armor");
        armor.setString("Name", "generic.armor");
        armor.setInteger("Operation", 0);
        armor.setInteger("UUIDLeast", 59664);
        armor.setInteger("UUIDMost", 31453);
        armor.setString("Slot", getPositionArmor(item));

        return setItemNameLevel(nbti.getItem(), itemLevel -1);
    }

    protected static String getPositionArmor(ItemStack item) {
        String type = item.getType().name();

        if (type.endsWith("_HELMET")) {
            return "head";
        } else if (type.endsWith("_CHESTPLATE")) {
            return "chest";
        } else if (type.endsWith("_LEGGINGS")) {
            return "legs";
        } else if (type.endsWith("_BOOTS")) {
            return "feet";
        }

        return null;
    }

    protected static int getDefaultArmor(ItemStack item)
    {
        if (isMaterial(item, Material.LEATHER_HELMET)) {
            return 1;
        } else if (isMaterial(item, Material.LEATHER_CHESTPLATE)) {
            return 3;
        } else if (isMaterial(item, Material.LEATHER_LEGGINGS)) {
            return 2;
        } else if (isMaterial(item, Material.LEATHER_BOOTS)) {
            return 1;
        } else if (isMaterial(item, Material.CHAINMAIL_HELMET)) {
            return 2;
        } else if (isMaterial(item, Material.CHAINMAIL_CHESTPLATE)) {
            return 5;
        } else if (isMaterial(item, Material.CHAINMAIL_LEGGINGS)) {
            return 4;
        } else if (isMaterial(item, Material.CHAINMAIL_BOOTS)) {
            return 1;
        } else if (isMaterial(item, Material.IRON_HELMET)) {
            return 2;
        } else if (isMaterial(item, Material.IRON_CHESTPLATE)) {
            return 6;
        } else if (isMaterial(item, Material.IRON_LEGGINGS)) {
            return 5;
        } else if (isMaterial(item, Material.IRON_BOOTS)) {
            return 2;
        } else if (isMaterial(item, Material.DIAMOND_HELMET)) {
            return 3;
        } else if (isMaterial(item, Material.DIAMOND_CHESTPLATE)) {
            return 8;
        } else if (isMaterial(item, Material.DIAMOND_LEGGINGS)) {
            return 6;
        } else if (isMaterial(item, Material.DIAMOND_BOOTS)) {
            return 3;
        } else if (isMaterial(item, Material.GOLD_HELMET)) {
            return 2;
        } else if (isMaterial(item, Material.GOLD_CHESTPLATE)) {
            return 5;
        } else if (isMaterial(item, Material.GOLD_LEGGINGS)) {
            return 3;
        } else if (isMaterial(item, Material.GOLD_BOOTS)) {
            return 1;
        }

        return 0;
    }

    public static int getDefaultArmorToughness(ItemStack item) {
        if (isMaterial(item, Material.DIAMOND_HELMET)) {
            return 2;
        } else if (isMaterial(item, Material.DIAMOND_CHESTPLATE)) {
            return 2;
        } else if (isMaterial(item, Material.DIAMOND_LEGGINGS)) {
            return 2;
        } else if (isMaterial(item, Material.DIAMOND_BOOTS)) {
            return 2;
        }

        return 0;
    }
}