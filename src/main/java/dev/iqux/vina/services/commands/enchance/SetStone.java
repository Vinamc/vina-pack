package dev.iqux.vina.services.commands.enchance;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.iqux.vina.app.enchance.resources.Stone;

public class SetStone extends SetCommand {

    public static final String name = "setstone";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (! this.validateSender(sender)) {
            return false;
        }

        Player p = (Player) sender;

        p.getInventory().setItemInMainHand(
            Stone.setItem(p.getInventory().getItemInMainHand())
        );

        return false;
    }

    @Override
    protected String permission() {
        return "vina.command.enchance.setstone";
    }
}