package dev.iqux.vina;

import org.bukkit.plugin.java.JavaPlugin;

import dev.iqux.vina.services.commands.CommandHandler;
import dev.iqux.vina.services.listeners.EntityDamageByEntity;
import dev.iqux.vina.services.listeners.InventoryClick;
import dev.iqux.vina.services.listeners.InventoryClose;
import dev.iqux.vina.utils.Plugin;

/**
 * Hello world!
 *
 */
public class App extends JavaPlugin
{
    public static String version = "2.0.5";

    @Override
    public void onEnable() {

        saveDefaultConfig();

        this.bindPluginStatic();
        this.registerCommands();
        this.registerListeners();

        getLogger().info("Enable RPGBag Success");
    }

    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
    }

    private void registerCommands() {
        CommandHandler handler = new CommandHandler(this);

        this.getCommand("vina").setExecutor(handler);

        handler.registerCommands();
    }


    private void bindPluginStatic() {
        Plugin.bind(this);
    }

    private void registerListeners() {
        new InventoryClick();
        new InventoryClose();
        new EntityDamageByEntity();
    }
}
