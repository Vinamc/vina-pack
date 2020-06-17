package dev.iqux.vina.services.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import dev.iqux.vina.contracts.command.CommandInterface;
import dev.iqux.vina.services.commands.enchance.SetStone;
import dev.iqux.vina.utils.Config;
import dev.iqux.vina.utils.Utils;

public class CommandHandler implements CommandExecutor {

    private static HashMap<String, CommandInterface> commands = new HashMap<String, CommandInterface>();

    protected static void register(String name, CommandInterface cmd) {
        commands.put(name, cmd);
    }

    public static boolean exists(String name) {
        return commands.containsKey(name);
    }

    public static CommandInterface getExcutor(String name) {
        return commands.get(name);
    }

    public static List<String> getListSubCommand() {
        List<String> subCommands = new ArrayList<String>();

        Set<String> keySet = commands.keySet();

        for (String cmd : keySet.toArray(new String[keySet.size()])) {
            subCommands.add(cmd);
        }

        return subCommands;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length < 1) {
            return false;
        }

        Utils.bc(commands.toString());

        label = args[0];

        if (! exists(label)) {
            sender.sendMessage(Config.message("command_doesnt_exists"));
            return false;
        }

        String[] newArgs = new String[args.length -1];

        for (int i = 1; i < args.length; i++) {
            newArgs[i] = args[i];
        }

        return getExcutor(args[0]).onCommand(sender, command, label, newArgs);
    }

    public void registerCommands() {
        register(SetStone.name, new SetStone());
    }
}