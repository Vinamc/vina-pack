package dev.iqux.hlvpack.utils;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin {

    public static JavaPlugin plugin;

    public static void bind(JavaPlugin plugin) {
        Plugin.plugin = plugin;
    }

    public static void registerEvent(Listener event) {
        Bukkit.getPluginManager().registerEvents(event, Plugin.plugin);
    }
}