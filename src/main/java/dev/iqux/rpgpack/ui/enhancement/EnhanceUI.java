package dev.iqux.rpgpack.ui.enhancement;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import dev.iqux.rpgpack.Enhancement;
import dev.iqux.rpgpack.utils.Utils;

public class EnhanceUI {

    public static String   name = "EnhanceUI";
    protected static int   rows = 6*9;
    protected static int[] allowMotifySlots = {13, 15, 30, 32, 34};

    public static Inventory GUI(Player p) {
        Inventory inv = Utils.createInventory(null, rows, Utils.color(name));

        for (int slot = 1; slot <= rows; slot++) {
            if (! inArrayInt(allowMotifySlots, slot)) {
                Utils.createItemByte(inv, Material.STAINED_GLASS_PANE.name(), 1, 15, slot, "");
            }
        }

        Utils.createItem(inv, Material.ANVIL.name(), 1, 50, "Enhance", "Enhance those item");

        return inv;
    }

    public static void onClick(InventoryClickEvent e) {
        if (! e.getInventory().getName().equals(name)) {
            return;
        }

        Player p = (Player) e.getWhoClicked();
        Inventory inv       = e.getInventory();
        ItemStack actionItem = Utils.isAirItem(e.getCursor()) ? e.getCurrentItem() : e.getCursor();
        int slotClicked     = e.getSlot() + 1;

        if (isPlayerInventory(e)) {
            return;
        }

        boolean notValid =  (slotClicked == 30 && !Enhancement.isStone(actionItem))  ||
                            (slotClicked == 32 && !Enhancement.isPowder(actionItem)) ||
                            (slotClicked == 34 && !Enhancement.isCharm(actionItem))  ||
                            ! inArrayInt(allowMotifySlots, slotClicked);

        if (notValid) {
            e.setCancelled(true);
        }

        if (actionItem.getType().name().equals(Material.ANVIL.name()) && !Utils.isAirItem(getStone(inv))) {
            //enhance(e);
        }
    }

    public static void onClose(InventoryCloseEvent e) {

        if (! e.getInventory().getName().equals(name)) {
            return;
        }

        ItemStack returnItem;
        Inventory inv = e.getInventory();
        Player player = (Player) e.getPlayer();

        for (int slot : allowMotifySlots) {
            returnItem = getItemInSlot(inv, slot);

            if (returnItem != null) {
                if (player.getInventory().firstEmpty() != -1) {
                    player.getInventory().addItem(returnItem);
                } else {
                    player.getWorld().dropItem(player.getLocation(), returnItem);
                }
            }
        }
    }

    protected static void enhance(InventoryClickEvent e) {

        Inventory inv = e.getInventory();

        ItemStack item = Enhancement.enhanceWeaponSuccess(getItemEnhance(inv));

        inv.setItem(15 - 1, item);
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

    protected static ItemStack getStone(Inventory inv) {
        return inv.getItem(30 - 1);
    }

    protected static ItemStack getCharm(Inventory inv) {
        return inv.getItem(32 - 1);
    }

    protected static ItemStack getPowder(Inventory inv) {
        return inv.getItem(34 - 1);
    }

    protected static ItemStack getItemEnhance(Inventory inv) {
        return inv.getItem(13 - 1);
    }

}