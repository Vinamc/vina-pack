package dev.iqux.rpgpack.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.tr7zw.nbtapi.NBTItem;

public class Utils {
    
    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    /**
     * Broadcast message to all player
     * 
     * @param msg
     */
    public static void bc(String msg) {
        Bukkit.broadcastMessage(Utils.color(msg));
    }

    public static boolean isAirItem(ItemStack item) {
        return item == null || item.getType().name().equals(Material.AIR.name());
    }

    public static ItemStack createItem(Inventory inv, String material, int amount, int slot, String displayName, String ...loreStrings) {
        ItemStack    item = new ItemStack(Material.matchMaterial(material), amount);
        List<String> lore = new ArrayList <String>();
        ItemMeta     meta = item.getItemMeta();

        for (String s : loreStrings) {
            lore.add(Utils.color(s));
        }

        meta.setLore(lore);
        meta.setDisplayName(Utils.color(displayName));
        item.setItemMeta(meta);
        inv.setItem(slot -1 , item);

        return item;
    }

    public static ItemStack createItemByte(Inventory inv, String material, int amount, int byteId, int slot, String displayName, String ...loreStrings) {
        ItemStack    item = new ItemStack(Material.matchMaterial(material), amount, (short) byteId);
        List<String> lore = new ArrayList <String>();
        ItemMeta     meta = item.getItemMeta();

        for (String s : loreStrings) {
            lore.add(Utils.color(s));
        }

        meta.setLore(lore);
        meta.setDisplayName(Utils.color(displayName));
        item.setItemMeta(meta);
        inv.setItem(slot -1 , item);

        return item;
    }

    public static NBTItem toNBTItem(ItemStack item) {
        return new NBTItem(item);
    }

    public static Inventory createInventory(InventoryHolder owner, int numRow, String name) {
        return Bukkit.createInventory(owner, numRow, name);
    }

    public static int getDefaultDamage(ItemStack item) {

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

    private static Boolean isMaterial(ItemStack item, Material material) {
        return item.getType().name().equals(material.name());
    }
}