package dev.iqux.vina.app.enchance;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import dev.iqux.vina.app.enchance.resources.Lucky;
import dev.iqux.vina.app.enchance.resources.Protector;
import dev.iqux.vina.app.enchance.resources.Stone;
import dev.iqux.vina.utils.Config;
import dev.iqux.vina.utils.Random;
import dev.iqux.vina.utils.User;
import dev.iqux.vina.utils.Utils;

public class EnhancementUI {
    public static String   name = "EnhanceUI";
    protected static int   rows = 6*9;
    protected static int[] allowModifySlots = {13, 15, 30, 32, 34};

    public static Inventory GUI() {
        Inventory inv = Utils.createInventory(null, rows, Utils.color(name));

        for (int slot = 1; slot <= rows; slot++) {
            if (! inArrayInt(allowModifySlots, slot)) {
                Utils.createItemByte(inv, Material.STAINED_GLASS_PANE.name(), 1, 15, slot, "");
            }
        }

        ItemStack action = Utils.createItem(inv, Material.ANVIL.name(), 1, 50, Config.getString("gui.enhance_action.name"));
        ItemStack helper = Utils.createItem(inv, Material.PAPER.name(), 1, 54, Config.getString("gui.enhance_helper.name"));

        ItemMeta actionMeta = action.getItemMeta();
        ItemMeta helperMeta = helper.getItemMeta();

        actionMeta.setLore(Config.getStringList("gui.enhance_action.lore"));
        helperMeta.setLore(Config.getStringList("gui.enhance_helper.lore"));

        action.setItemMeta(actionMeta);
        helper.setItemMeta(helperMeta);

        return inv;
    }

    public static void onClick(InventoryClickEvent e) {
        if (! e.getInventory().getName().equals(name)) {
            return;
        }

        Inventory inv       = e.getInventory();
        ItemStack actionItem = Utils.isAirItem(e.getCursor()) ? e.getCurrentItem() : e.getCursor();
        int slotClicked     = e.getSlot() + 1;

        if (isPlayerInventory(e)) {
            return;
        }

        boolean notValid =  (slotClicked == 30 && !Stone.isValidItem(actionItem))     ||
                            (slotClicked == 32 && !Lucky.isValidItem(actionItem))     ||
                            (slotClicked == 34 && !Protector.isValidItem(actionItem)) ||
                            ! inArrayInt(allowModifySlots, slotClicked);

        if (notValid) {
            e.setCancelled(true);
        }

        if (actionItem.getType().name().equals(Material.ANVIL.name()) && !Utils.isAirItem(getItem(inv))) {
            enhance(e);
        }
    }

    public static void onClose(InventoryCloseEvent e) {

        if (! e.getInventory().getName().equals(name)) {
            return;
        }

        ItemStack returnItem;
        Inventory inv = e.getInventory();
        Player player = (Player) e.getPlayer();

        for (int slot : allowModifySlots) {
            returnItem = getItemInSlot(inv, slot);

            User.giveBackItem(player, returnItem);
        }
    }

    protected static void enhance(InventoryClickEvent e) {

        Player player    = (Player) e.getWhoClicked();
        Inventory inv    = e.getInventory();
        ItemStack item   = getItem(inv);
        ItemStack stone  = getStone(inv);
        ItemStack charm  = getCharm(inv);
        ItemStack powder = getPowder(inv);

        if (Utils.isAirItem(item) || !Stone.isValidItem(stone)) {
            return;
        }

        User.playSound(player, Sound.BLOCK_ANVIL_USE);

        int currentLevel = Enhancement.getItemLevel(item);
        Double chance = Config.getDouble(
            "level.".concat(Integer.toString(currentLevel + 1)).concat(".success_chance")
        );

        if (Lucky.isValidItem(powder)) {
            Double powderRate = (Lucky.getChance(powder) / 100) * chance;
            chance += powderRate;
        }

        if (Enhancement.isArmor(item)) {
            enhanceArmor(player, item, currentLevel, inv, charm, chance);
        } else {
            enhanceWeapon(player, item, currentLevel, inv, charm, chance);
        }
    }

    protected static void enhanceWeapon(Player player, ItemStack item, int currentLevel, Inventory inv, ItemStack charm, Double chance) {
        if (Random.canSuccess(chance)) {
            item = Weapon.enhanceSuccess(item.clone());
            player.sendMessage(Config.message("enhance_success"));
            clearItemModify(inv, player);
            inv.setItem(15 -1, item);

            if (currentLevel + 1 >= Config.getInt("broadcast_success_after_level")) {
                Utils.bc(
                    Config.message("broadcast_success_message")
                    .replace("%player_name%", player.getDisplayName())
                    .replace("%item_name%", Utils.getItemName(item))
                    .replace("%item_level%", Integer.toString(currentLevel + 1))
                );
            }
        } else {
            if (! Protector.isValidItem(charm)) {
                item = Weapon.enhanceFailed(item.clone());
            }

            player.sendMessage(Config.message("enhance_failed"));
            clearItemModify(inv, player);
            inv.setItem(15 -1, item);
        }
    }

    protected static void enhanceArmor(Player player, ItemStack item, int currentLevel, Inventory inv, ItemStack charm, Double chance) {
        if (Random.canSuccess(chance)) {
            item = Armor.enhanceSuccess(item.clone());
            player.sendMessage(Config.message("enhance_success"));
            clearItemModify(inv, player);
            inv.setItem(15 -1, item);

            if (currentLevel + 1 >= Config.getInt("broadcast_success_after_level")) {
                 Utils.bc(
                    Config.message("broadcast_success_message")
                    .replace("%player_name%", player.getDisplayName())
                    .replace("%item_name%", Utils.getItemName(item))
                    .replace("%item_level%", Integer.toString(currentLevel + 1))
                );
            }
        } else {
            if (! Protector.isValidItem(charm)) {
                item = Armor.enhanceFailed(item.clone());
            }

            player.sendMessage(Config.message("enhance_failed"));
            clearItemModify(inv, player);
            inv.setItem(15 -1, item);
        }
    }

    protected static void clearItemModify(Inventory inv, Player p) {
        ItemStack item;

        for (int slot : allowModifySlots) {
            item = inv.getItem(slot -1);

            if (slot == 15) {
                User.giveBackItem(p, item);
            }
            else if (slot == 13) {
                inv.clear(slot - 1);
            }
            else if (!Utils.isAirItem(item) && item.getAmount() > 1) {
                item.setAmount(item.getAmount() -1);
            }
            else {
                inv.clear(slot - 1);
            }
        }
    }

    protected static boolean isPlayerInventory(InventoryClickEvent e) {
        return e.getSlot() != e.getRawSlot();
    }

    protected static boolean notPlayerInventory(InventoryClickEvent e) {
        return e.getSlot() == e.getRawSlot();
    }

    protected static ItemStack getItemInSlot(Inventory inv, int slot) {
        return inv.getContents()[slot - 1];
    }

    private static boolean inArrayInt(int[] arrayInt, int num) {
        for (int i : arrayInt) {
            if (i == num) {
                return true;
            }
        }

        return false;
    }

    protected static ItemStack getItem(Inventory inv) {
        return inv.getItem(13 -1);
    }

    protected static ItemStack getStone(Inventory inv) {
        return inv.getItem(30 - 1);
    }

    protected static ItemStack getCharm(Inventory inv) {
        return inv.getItem(34 - 1);
    }

    protected static ItemStack getPowder(Inventory inv) {
        return inv.getItem(32 - 1);
    }
}