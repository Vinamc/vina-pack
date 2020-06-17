package dev.iqux.vina.services.commands.enchance;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.iqux.vina.app.enchance.resources.Protector;

public class SetProtector extends SetCommand {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (! this.validateSender(sender)) {
            return false;
        }

        Player p = (Player) sender;

        p.getInventory().setItemInMainHand(
            Protector.setItem(p.getInventory().getItemInMainHand())
        );

        return false;
    }

    @Override
    protected String permission() {
        return "vina.command.enchance.setprotector";
    }
}