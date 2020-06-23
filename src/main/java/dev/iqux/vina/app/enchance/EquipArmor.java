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

        Double health = p.getMaxHealth() + Armor.getTotalHealth(item);

        if (isEquipArmor(e) && health > 19) {
            p.setMaxHealth(health);
        }
    }

    protected static void onDeEquip(InventoryClickEvent e) {
        Player p       = (Player) e.getWhoClicked();
        ItemStack item = getItem(e);

        Double health = p.getMaxHealth() - Armor.getTotalHealth(item);
        health = health < 21 ? 20 : health;

        if (isDeEquipArmor(e)) {
            p.setMaxHealth(health);
        }
    }

    protected static boolean isEquipArmor(InventoryClickEvent e) {

        ItemStack item = e.getCurrentItem();
        SlotType type  = e.getSlotType();

        if (e.isShiftClick() && (type.equals(SlotType.CONTAINER) || type.equals(SlotType.QUICKBAR)) && !Utils.isAirItem(item)) {
            return true;
        }

        if (type.equals(SlotType.ARMOR) && e.isShiftClick()) {
            return false;
        }

        if (! type.equals(SlotType.ARMOR) || Utils.isAirItem(e.getCursor())) {
            return false;
        }

        return true;
    }

    protected static boolean isDeEquipArmor(InventoryClickEvent e) {

        ItemStack item = e.getCurrentItem();
        SlotType type  = e.getSlotType();

        if (e.isShiftClick() && type.equals(SlotType.ARMOR) && !Utils.isAirItem(item)) {
            return true;
        }

        if ((type.equals(SlotType.CONTAINER) || type.equals(SlotType.QUICKBAR)) && e.isShiftClick()){
            return false;
        }

        if (! type.equals(SlotType.ARMOR) || Utils.isAirItem(item)) {
            return false;
        }

        return true;
    }

    protected static ItemStack getItem(InventoryClickEvent e) {
        return Utils.isAirItem(e.getCursor()) ? e.getCurrentItem() : e.getCursor();
    }
}