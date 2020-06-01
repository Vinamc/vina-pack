package dev.iqux.rpgpack.ui.enhancement;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import dev.iqux.rpgpack.utils.Utils;

public class EnhanceUI {

    public static String   name = "EnhanceUI";
    protected static int   rows = 4*9;
    protected static int[] allowMotifySlots = {14, 20, 26};

    public static Inventory GUI(Player p) {
        Inventory inv = Utils.createInventory(null, rows, Utils.color(name));

        int[] yellowSlot = {10, 11, 12, 19, 21, 28, 29, 30};
        int[] blackSlot  = {13, 15, 22, 23, 24};
        int[] blueSlot   = {16, 17, 18, 25, 27, 34, 35, 36};

        for (int slot = 1; slot <= rows; slot++) {
            if (slot < 10 || inArrayInt(blackSlot, slot)) {
                Utils.createItemByte(inv, Material.STAINED_GLASS_PANE.name(), 1, 15, slot, "Enhance");
            }

            if (inArrayInt(yellowSlot, slot)) {
                Utils.createItemByte(inv, Material.STAINED_GLASS_PANE.name(), 1, 4, slot, "Enhance");
            }

            if (inArrayInt(blueSlot, slot)) {
                Utils.createItemByte(inv, Material.STAINED_GLASS_PANE.name(), 1, 9, slot, "Enhance");
            }
        }

        Utils.createItem(inv, Material.BARRIER.name(), 1, 31, "Cancel", "exit");
        Utils.createItem(inv, Material.ANVIL.name(), 1, 32, "Enhance", "Enhance those item");
        Utils.createItem(inv, Material.BARRIER.name(), 1, 33, "Cancel", "exit");

        return inv;
    }

    public static void onClick(InventoryClickEvent e) {
        if (! e.getInventory().getName().equals(name)) {
            return;
        }

        ItemStack clickItem = e.getCurrentItem();

        if (isPlayerInventory(e)) {
            return;
        }

        if (! inArrayInt(allowMotifySlots, e.getSlot() + 1)) {
            e.setCancelled(true);
        }

        if (clickItem.getType().name().equals(Material.BARRIER.name())) {
            e.getWhoClicked().closeInventory();
        }

        if (clickItem.getType().name().equals(Material.ANVIL.name())) {
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
        // TODO
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
}