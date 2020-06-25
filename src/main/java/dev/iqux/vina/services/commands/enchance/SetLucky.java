package dev.iqux.vina.services.commands.enchance;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.iqux.vina.app.enchance.resources.Lucky;
import dev.iqux.vina.utils.Config;

public class SetLucky extends SetCommand {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (! this.validateSender(sender)) {
            return false;
        }

        if (args.length < 1) {
            this.alertInvalidCommand(sender);
            return false;
        }

        Double chance = Double.parseDouble(args[0]);

        if (chance < 0) {
            Config.message("number_must_positive");
            return false;
        }

        Player p = (Player) sender;

        p.getInventory().setItemInMainHand(
            Lucky.setItem(p.getInventory().getItemInMainHand(), chance)
        );

        return false;
    }

    @Override
    protected String permission() {
        return "vina.command.enchance.setlucky";
    }

    @Override
    public String getName() {
        return "setlucky";
    }
}