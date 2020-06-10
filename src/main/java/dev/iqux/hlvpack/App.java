package dev.iqux.hlvpack;

import org.bukkit.plugin.java.JavaPlugin;

import dev.iqux.hlvpack.commands.enhancement.*;
import dev.iqux.hlvpack.listeners.InventoryClick;
import dev.iqux.hlvpack.listeners.InventoryClose;
import dev.iqux.hlvpack.utils.Plugin;

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
        new CreatePowder(this);
        new CreateStone(this);
        new ShowEnhance(this);
        new CreateCharm(this);
    }

    private void bindEvents() {
        new InventoryClick();
        new InventoryClose();
    }

    private void bindPluginStatic() {
        Plugin.bind(this);
    }
}
