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

public class CreateStone implements CommandExecutor {

    public CreateStone(JavaPlugin plugin) {
        plugin.getCommand("setstone").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (! (sender instanceof Player)) {
            sender.sendMessage("Can't excute this command in console");
            return false;
        }

        Player player   = (Player) sender;
        ItemStack stone = User.getPlayerHandItem(player);

        if (! (player.hasPermission("vinapack.admin.createstone"))) {
            player.sendMessage(Config.message("missing_permission"));
            return false;
        }

        if (stone == null) {
            player.sendMessage(Config.message("hand_empty"));
            return false;
        }

        // if (! isValidArgs(args)) {
        //     player.sendMessage(Config.message("command.create_stone"));
        //     return false;
        // }


        player.getInventory().setItemInMainHand(Enhancement.setStone(stone, "ANY"));

        return true;
    }

    protected static boolean isValidArgs(String[] args) {
        if (args.length < 1) {
            return false;
        }

        for (String type : Enhancement.getTypeEnhanceStone()) {
            if (type.equals(args[0])) {
                return true;
            }
        }

        return false;
    }
}
