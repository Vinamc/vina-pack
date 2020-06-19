package dev.iqux.vina.services.commands.enchance;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.iqux.vina.app.enchance.Armor;
import dev.iqux.vina.utils.Config;
import dev.iqux.vina.utils.User;

public class SetBasicHealth extends SetCommand {

    public static final String name = "setbasichealth";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (! this.validateSender(sender)) {
            return false;
        }

        if (args.length < 1) {
            this.alertInvalidCommand(sender);
            return false;
        }

        Double stats = Double.parseDouble(args[0]);

        if (stats < 0) {
            Config.message("number_must_positive");
            return false;
        }

        Player p = (Player) sender;

        p.getInventory().setItemInMainHand(
            Armor.setHealth(User.getPlayerHandItem(p), stats, 0.0)
        );

        return false;
    }

    @Override
    protected String permission() {
        return "vina.command.enchance.setbasichealth";
    }
}