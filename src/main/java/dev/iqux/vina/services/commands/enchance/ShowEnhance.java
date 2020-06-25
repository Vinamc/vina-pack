package dev.iqux.vina.services.commands.enchance;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.iqux.vina.app.enchance.EnhancementUI;
import dev.iqux.vina.services.commands.BaseCommand;

public class ShowEnhance extends BaseCommand {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!this.validateSender(sender)) {
            return false;
        }

        ((Player) sender).openInventory(EnhancementUI.GUI());

        return true;
    }

    @Override
    protected String permission() {
        return "vina.command.enchance.show";
    }

    @Override
    public String getName() {
        return "showenhance";
    }
}