package dev.iqux.vina.services.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.iqux.vina.contracts.command.CommandInterface;
import dev.iqux.vina.utils.Config;

abstract public class BaseCommand implements CommandInterface, CommandExecutor {

    protected String[] alias = {};

    abstract protected String permission();

    protected boolean validateSender(CommandSender sender) {
        if (!this.hasPermission(sender)) {
            this.alertPermissionRequired(sender);
            return false;
        }

        if (this.isSendFromConsole(sender)) {
            this.alertExcuteInGameOnly(sender);
            return false;
        }

        return true;
    }

    protected boolean hasPermission(CommandSender sender) {
        return sender.hasPermission(this.permission());
    }

    protected void alertInvalidCommand(CommandSender sender) {
        sender.sendMessage(Config.message("command_invalid"));
    }

    protected void alertPermissionRequired(CommandSender sender) {
        sender.sendMessage(Config.message("missing_permission"));
    }

    protected void alertExcuteInGameOnly(CommandSender sender) {
        sender.sendMessage(Config.message("command_must_send_ingame"));
    }

    protected boolean isPlayer(CommandSender sender) {
        return sender instanceof Player;
    }

    protected boolean isSendFromConsole(CommandSender sender) {
        return !this.isPlayer(sender);
    }

    public String[] getAlias() {

        return alias;
    }
}