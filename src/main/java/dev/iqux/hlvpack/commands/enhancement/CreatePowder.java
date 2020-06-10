package dev.iqux.hlvpack.commands.enhancement;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import dev.iqux.hlvpack.Enhancement;
import dev.iqux.hlvpack.utils.Config;
import dev.iqux.hlvpack.utils.User;

public class CreatePowder implements CommandExecutor {

    public CreatePowder(JavaPlugin plugin) {
        plugin.getCommand("setpowder").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (! (sender instanceof Player)) {
            sender.sendMessage("Can't excute this command in console");
            return false;
        }

        Float rate;
        Player player   = (Player) sender;
        ItemStack powder = User.getPlayerHandItem(player);

        if (! (player.hasPermission("rpgpack.admin.createpowder"))) {
            player.sendMessage(Config.message("missing_permission"));
            return false;
        }

        if (powder == null) {
            player.sendMessage(Config.message("hand_empty"));
            return false;
        }

        if (args.length < 1) {
            player.sendMessage(Config.message("command.create_powder"));
            return false;
        }

        try {
            rate = Float.parseFloat(args[0]);
        } catch (NumberFormatException e){
            player.sendMessage(Config.message("invalid_data_type"));
            return false;
        }

        if (rate < 0) {
            player.sendMessage(Config.message("number_must_positive"));
            return false;
        }

        player.getInventory().setItemInMainHand(Enhancement.setLuckyPowder(powder, rate));

        return true;
    }
}