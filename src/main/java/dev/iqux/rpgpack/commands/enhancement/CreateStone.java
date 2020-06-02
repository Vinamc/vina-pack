package dev.iqux.rpgpack.commands.enhancement;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import de.tr7zw.nbtapi.NBTItem;
import dev.iqux.rpgpack.utils.Config;

public class CreateStone implements CommandExecutor {

    public CreateStone(JavaPlugin plugin) {
        plugin.getCommand("createstone").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (! (sender instanceof Player)) {
            sender.sendMessage("Can't excute this command in console");
            return false;
        }

        Player player   = (Player) sender;
        ItemStack stone = this.getPlayerHandItem(player);

        if (! (player.hasPermission("rpgpack.admin.createstone"))) {
            player.sendMessage(Config.message("missing_permission"));
            return false;
        }

        if (stone == null) {
            player.sendMessage(Config.message("hand_empty"));
            return false;
        }

        if (args.length < 2) {
            player.sendMessage(Config.message("command.create_stone_missing_args"));
            return false;
        }

        player.getInventory().setItemInMainHand(makeStone(stone, args[0], args[1]));

        return true;
    }

    protected ItemStack getPlayerHandItem(Player p) {
        ItemStack item = p.getInventory().getItemInMainHand();

        if (item != null && item.getType().name().equals(Material.AIR.name())) {
            return null;
        }

        return item;
    }

    protected ItemStack makeStone(ItemStack stone, String type, String action) {
        NBTItem nbti = new NBTItem(stone);

        nbti.setString("type", type);
        nbti.setString("action", action);

        return nbti.getItem();
    }
}