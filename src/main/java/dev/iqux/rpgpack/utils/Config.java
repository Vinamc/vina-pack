package dev.iqux.rpgpack.utils;

public class Config {

    public static String getString(String key) {
        return Plugin.plugin.getConfig().getString(key);
    }

    public static boolean getBoolean(String key) {
        return Plugin.plugin.getConfig().getBoolean(key);
    }

    public static int getInt(String key) {
        return Plugin.plugin.getConfig().getInt(key);
    }

    public static long getLong(String key) {
        return Plugin.plugin.getConfig().getLong(key);
    }

    public static String message(String key) {
        return Utils.color(getString("message.".concat(key)));
    }
}