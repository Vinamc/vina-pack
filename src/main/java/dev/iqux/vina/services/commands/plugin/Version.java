package dev.iqux.vina.services.commands.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import dev.iqux.vina.App;
import dev.iqux.vina.services.commands.BaseCommand;
import dev.iqux.vina.utils.Config;
import dev.iqux.vina.utils.Utils;

public class Version extends BaseCommand {

    protected String[] alias = {"ver"};

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        sender.sendMessage(Config.getString(
            "message_prefix"
            .concat(Utils.color("&2Plugin version &6"))
            .concat(App.version)
        ));

        return true;
    }

    @Override
    protected String permission() {
        return "";
    }

    @Override
    public String getName() {
        return "version";
    }
}
