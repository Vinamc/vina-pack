package dev.iqux.vina;

import org.bukkit.plugin.java.JavaPlugin;

import dev.iqux.vina.commands.enhancement.*;
import dev.iqux.vina.listeners.InventoryClick;
import dev.iqux.vina.listeners.InventoryClose;
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
