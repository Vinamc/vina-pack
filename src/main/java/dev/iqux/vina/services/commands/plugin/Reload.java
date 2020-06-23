package dev.iqux.vina.services.commands.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import dev.iqux.vina.services.commands.BaseCommand;
import dev.iqux.vina.utils.Config;

public class Reload extends BaseCommand {

    public static String name = "reload";

    protected JavaPlugin plugin;

    public Reload(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (! this.hasPermission(sender)) {
            this.alertPermissionRequired(sender);
            return false;
        }

        this.plugin.reloadConfig();
        sender.sendMessage(Config.message("config_reloaded"));

        return true;
    }

    @Override
    protected String permission() {
        return "vina.command.plugin.reload";
    }
    
}