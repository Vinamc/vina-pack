package dev.iqux.rpgpack;

import org.bukkit.plugin.java.JavaPlugin;

import dev.iqux.rpgpack.commands.enhancement.CreateStone;
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

        this.bindCommand();

        getLogger().info("Enable RPGBag Success");
    }

    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
    }

    private void bindCommand() {
        new CreateStone(this);
    }

    private void bindPluginStatic() {
        Plugin.bind(this);
    }
}
