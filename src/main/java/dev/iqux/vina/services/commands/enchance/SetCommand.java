package dev.iqux.vina.services.commands.enchance;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import dev.iqux.vina.services.commands.BaseCommand;
import dev.iqux.vina.utils.Config;
import dev.iqux.vina.utils.Utils;

abstract public class SetCommand extends BaseCommand {

    @Override
    protected boolean validateSender(CommandSender sender) {
        if (! super.validateSender(sender)) {
            return false;
        }

        Player p = (Player) sender;

        ItemStack item = p.getInventory().getItemInMainHand();

        if (Utils.isAirItem(item)) {
            p.sendMessage(Config.message("hand_empty"));
            return false;
        }

        return true;
    }
}