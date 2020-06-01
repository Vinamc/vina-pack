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

    public static Inventory createInventory(InventoryHolder owner, int numRow, String name) {
        return Bukkit.createInventory(owner, numRow, name);
    }
}