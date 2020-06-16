package dev.iqux.vina;

import org.bukkit.plugin.java.JavaPlugin;

import dev.iqux.vina.utils.Plugin;

/**
 * Hello world!
 *
 */
public class App extends JavaPlugin
{
    @Override
    public void onEnable() {

        saveDefaultConfig();

        this.bindPluginStatic();

        getLogger().info("Enable RPGBag Success");
    }

    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
    }


    private void bindPluginStatic() {
        Plugin.bind(this);
    }
}
