package dev.iqux.vina.commands.enhancement;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import dev.iqux.vina.Enhancement;
import dev.iqux.vina.utils.Config;
import dev.iqux.vina.utils.User;

public class CreateCharm implements CommandExecutor {

    public CreateCharm(JavaPlugin plugin) {
        plugin.getCommand("setcharm").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (! (sender instanceof Player)) {
            sender.sendMessage("Can't excute this command in console");
            return false;
        }

        Player player   = (Player) sender;
        ItemStack charm = User.getPlayerHandItem(player);

        if (! (player.hasPermission("rpgpack.admin.createcharm"))) {
            player.sendMessage(Config.message("missing_permission"));
            return false;
        }

        if (charm == null) {
            player.sendMessage(Config.message("hand_empty"));
            return false;
        }

        player.getInventory().setItemInMainHand(Enhancement.setCharm(charm));

        return true;
    }
}