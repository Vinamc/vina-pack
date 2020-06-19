package dev.iqux.vina.app.enchance;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.ItemStack;

import dev.iqux.vina.utils.Utils;

public class EquipArmor {

    public static void handle(InventoryClickEvent e) {
        onEquip(e);
        onDeEquip(e);
    }

    protected static void onEquip(InventoryClickEvent e) {
        Player p       = (Player) e.getWhoClicked();
        ItemStack item = getItem(e);

        if (isEquipArmor(e)) {
            p.setMaxHealth(p.getMaxHealth() + Armor.getTotalHealth(item));
        }
    }

    protected static void onDeEquip(InventoryClickEvent e) {
        Player p       = (Player) e.getWhoClicked();
        ItemStack item = getItem(e);

        if (isDeEquipArmor(e)) {
            p.setMaxHealth(p.getMaxHealth() - Armor.getTotalHealth(item));
        }
    }

    protected static boolean isEquipArmor(InventoryClickEvent e) {

        ItemStack item = e.getCurrentItem();

        if (e.isShiftClick() && e.getSlotType().equals(SlotType.CONTAINER) && !Utils.isAirItem(item)) {
            return true;
        }

        if (e.getSlotType().equals(SlotType.ARMOR) && e.isShiftClick()) {
            return false;
        }

        if (! e.getSlotType().equals(SlotType.ARMOR) || Utils.isAirItem(e.getCursor())) {
            return false;
        }

        return true;
    }

    protected static boolean isDeEquipArmor(InventoryClickEvent e) {

        ItemStack item = e.getCurrentItem();

        if (e.isShiftClick() && e.getSlotType().equals(SlotType.ARMOR) && !Utils.isAirItem(item)) {
            return true;
        }

        if (e.getSlotType().equals(SlotType.CONTAINER) && e.isShiftClick()){
            return false;
        }

        if (! e.getSlotType().equals(SlotType.ARMOR) || Utils.isAirItem(item)) {
            return false;
        }

        return true;
    }

    protected static ItemStack getItem(InventoryClickEvent e) {
        return Utils.isAirItem(e.getCursor()) ? e.getCurrentItem() : e.getCursor();
    }
}