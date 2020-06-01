package dev.iqux.rpgpack.commands.enhancement;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CreateStone implements CommandExecutor {

    public CreateStone(JavaPlugin plugin) {
        plugin.getCommand("createstone").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (! (sender instanceof Player)) {
            sender.sendMessage("Can't excute this command in console");
            return false;
        }

        Player player = (Player) sender;

        if (! (player.hasPermission("rpgpack.admin.createstone"))) {
            player.sendMessage("You dont have permission!");
            return false;
        }

        // @todo the rest of command create stone

        return true;
    }
}