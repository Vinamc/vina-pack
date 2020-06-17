package dev.iqux.vina;

import org.bukkit.plugin.java.JavaPlugin;

import dev.iqux.vina.services.commands.CommandHandler;
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
        this.registerCommands();

        getLogger().info("Enable RPGBag Success");
    }

    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
    }

    private void registerCommands() {
        CommandHandler handler = new CommandHandler();

        this.getCommand("vina").setExecutor(handler);

        handler.registerCommands();
    }


    private void bindPluginStatic() {
        Plugin.bind(this);
    }
}
