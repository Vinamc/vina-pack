package dev.iqux.rpgpack.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Utils {
    
    public static String chat(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    /**
     * Broadcast message to all player
     * 
     * @param msg
     */
    public static void bc(String msg) {
        Bukkit.broadcastMessage(Utils.chat(msg));
    }
}