package dev.iqux.vina.contracts.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface CommandInterface {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args);

    public String getName();

    public String[] getAlias();
}