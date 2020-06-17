package dev.iqux.vina.services.commands.enchance;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import dev.iqux.vina.services.commands.BaseCommand;
import dev.iqux.vina.utils.Config;

abstract public class SetCommand extends BaseCommand {

    protected boolean validateSender(CommandSender sender) {
        if (! super.validateSender(sender)) {
            return false;
        }

        Player p = (Player) sender;

        ItemStack item = p.getInventory().getItemInMainHand();

        if (item == null) {
            p.sendMessage(Config.message("hand_empty"));
            return false;
        }

        return true;
    }
}