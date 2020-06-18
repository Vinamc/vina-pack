package dev.iqux.vina.services.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import dev.iqux.vina.app.enchance.Armor;
import dev.iqux.vina.app.enchance.Weapon;
import dev.iqux.vina.utils.Plugin;
import dev.iqux.vina.utils.Utils;

public class EntityDamageByEntity implements Listener {
    
    public EntityDamageByEntity() {
        Plugin.registerEvent(this);
    }

    @EventHandler
    public void handle(EntityDamageByEntityEvent e) {
        if (! (e.getDamager() instanceof Player)) {
            return;
        }

        Player damager = (Player) e.getDamager();

        Double totalDamage = e.getDamage() + Weapon.getTotalDamage(
            damager.getInventory().getItemInMainHand()
        );

        if (! (e.getEntity() instanceof Player)) {
            e.setDamage(totalDamage);
            return;
        }

        Player entity = (Player) e.getEntity();

        for (ItemStack item : entity.getInventory().getArmorContents()) {
            if (! Utils.isAirItem(item)) {
                totalDamage -= Armor.getTotalArmor(item);
            }
        }

        e.setDamage(totalDamage);
    }
}