package dev.iqux.vina.commands.enhancement;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import dev.iqux.vina.ui.enhancement.EnhanceUI;

public class ShowEnhance implements CommandExecutor {

    public ShowEnhance(JavaPlugin plugin) {
        plugin.getCommand("showenhance").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (! (sender instanceof Player)) {
            sender.sendMessage("Can't excute this command in console");
            return false;
        }

        Player player = (Player) sender;

        if (! (player.hasPermission("rpgpack.use.gui"))) {
            player.sendMessage("You dont have permission!");
            return false;
        }

        player.openInventory(EnhanceUI.GUI(player));

        return true;
    }
    
}