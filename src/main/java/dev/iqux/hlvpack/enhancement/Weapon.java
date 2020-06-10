package dev.iqux.hlvpack.enhancement;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import de.tr7zw.nbtapi.NBTCompoundList;
import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.NBTListCompound;
import dev.iqux.hlvpack.Enhancement;
import dev.iqux.hlvpack.utils.Config;
import dev.iqux.hlvpack.utils.Utils;


public class Weapon extends Enhancement
{
    public static ItemStack enhanceSuccess(ItemStack item)
    {
        int itemLevel      = getIntItemKey(item, "level") + 1;
        int increaseDamage = Config.getInt(
            "level.".concat(Integer.toString(itemLevel)).concat(".weapon_increase")
        );

        int defaultlDamage = getIntItemKey(item, "default_damage");
        int customDamage   = getIntItemKey(item, "custom_damage");

        NBTItem nbti = Utils.toNBTItem(item);

        nbti.setInteger("level", itemLevel);
        nbti.setInteger("default_damage", defaultlDamage + increaseDamage);

        NBTCompoundList attribute = nbti.getCompoundList("AttributeModifiers");
        attribute.clear();

        NBTListCompound damage = attribute.addCompound();

        damage.setInteger("Amount", defaultlDamage + customDamage + increaseDamage + getDefaultDamage(item));
        damage.setString("AttributeName", "generic.attackDamage");
        damage.setString("Name", "generic.attackDamage");
        damage.setInteger("Operation", 0);
        damage.setInteger("UUIDLeast", 59664);
        damage.setInteger("UUIDMost", 31453);
        damage.setString("Slot", "mainhand");

        return setItemNameLevel(nbti.getItem(), itemLevel);
    }

    public static ItemStack enhanceFailed(ItemStack item)
    {
        int itemLevel      = getIntItemKey(item, "level");
        int decreaseDamage = - Config.getInt(
            "level.".concat(Integer.toString(itemLevel)).concat(".weapon_increase")
        );

        int defaultlDamage = getIntItemKey(item, "default_damage");
        int customDamage   = getIntItemKey(item, "custom_damage");

        NBTItem nbti = Utils.toNBTItem(item);

        nbti.setInteger("level", itemLevel - 1);
        nbti.setInteger("default_damage", defaultlDamage + decreaseDamage);

        NBTCompoundList attribute = nbti.getCompoundList("AttributeModifiers");
        attribute.clear();

        NBTListCompound damage = attribute.addCompound();

        damage.setInteger("Amount", defaultlDamage + customDamage + decreaseDamage + getDefaultDamage(item));
        damage.setString("AttributeName", "generic.attackDamage");
        damage.setString("Name", "generic.attackDamage");
        damage.setInteger("Operation", 0);
        damage.setInteger("UUIDLeast", 59664);
        damage.setInteger("UUIDMost", 31453);
        damage.setString("Slot", "mainhand");

        return setItemNameLevel(nbti.getItem(), itemLevel -1);
    }

    public static int getDefaultDamage(ItemStack item)
    {
        if (isMaterial(item, Material.DIAMOND_SWORD)) {
            return 7;
        } else if (isMaterial(item, Material.GOLD_SWORD)) {
            return 4;
        } else if (isMaterial(item, Material.IRON_SWORD)) {
            return 6;
        } else if (isMaterial(item, Material.STONE_SWORD)) {
            return 5;
        } else if (isMaterial(item, Material.WOOD_SWORD)) {
            return 4;
        } else if (isMaterial(item, Material.DIAMOND_AXE)) {
            return 9;
        } else if (isMaterial(item, Material.GOLD_AXE)) {
            return 7;
        } else if (isMaterial(item, Material.IRON_AXE)) {
            return 9;
        } else if (isMaterial(item, Material.STONE_AXE)) {
            return 9;
        } else if (isMaterial(item, Material.WOOD_AXE)) {
            return 7;
        }

        return 1;
    }

}