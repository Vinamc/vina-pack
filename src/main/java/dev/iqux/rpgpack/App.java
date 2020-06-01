package dev.iqux.rpgpack;

import org.bukkit.plugin.java.JavaPlugin;

import dev.iqux.rpgpack.commands.enhancement.*;
import dev.iqux.rpgpack.listeners.InventoryClick;
import dev.iqux.rpgpack.listeners.InventoryClose;
import dev.iqux.rpgpack.utils.Plugin;

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

        this.bindCommands();
        this.bindEvents();

        getLogger().info("Enable RPGBag Success");
    }

    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
    }

    private void bindCommands() {
        new CreateStone(this);
        new ShowEnhance(this);
    }

    private void bindEvents() {
        new InventoryClick();
        new InventoryClose();
    }

    private void bindPluginStatic() {
        Plugin.bind(this);
    }
}
