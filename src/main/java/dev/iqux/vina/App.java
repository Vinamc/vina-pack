package dev.iqux.vina;

import java.text.DecimalFormat;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.plugin.java.JavaPlugin;

import dev.iqux.vina.services.commands.CommandHandler;
import dev.iqux.vina.services.listeners.EntityDamageByEntity;
import dev.iqux.vina.services.listeners.InventoryClick;
import dev.iqux.vina.services.listeners.InventoryClose;
import dev.iqux.vina.utils.Plugin;

import be.maximvdw.placeholderapi.PlaceholderAPI;
import be.maximvdw.placeholderapi.PlaceholderReplacer;
import be.maximvdw.placeholderapi.PlaceholderReplaceEvent;

public class App extends JavaPlugin
{
    public static String version = "2.0.7";

    @Override
    public void onEnable() {

        saveDefaultConfig();

        this.bindPluginStatic();
        this.registerCommands();
        this.registerListeners();
        this.registerPlaceholder();

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

    private void registerPlaceholder() {
        if (! Bukkit.getPluginManager().isPluginEnabled("MVdWPlaceholderAPI")) return;

        PlaceholderReplacer maxHealth = new PlaceholderReplacer(){
            @Override
            public String onPlaceholderReplace(PlaceholderReplaceEvent event) {

                DecimalFormat formatter = new DecimalFormat("#,###");

                return formatter.format(
                    event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()
                );
            }
        };

        PlaceholderReplacer health = new PlaceholderReplacer(){
            @Override
            public String onPlaceholderReplace(PlaceholderReplaceEvent event) {

                DecimalFormat formatter = new DecimalFormat("#,###");

                return formatter.format(event.getPlayer().getHealth());
            }
        };

        PlaceholderAPI.registerPlaceholder(this, "player_max_health", maxHealth);
        PlaceholderAPI.registerPlaceholder(this, "player_health", health);
    }
}
