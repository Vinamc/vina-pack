package dev.iqux.vina.services.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import dev.iqux.vina.app.enchance.EnhancementUI;
import dev.iqux.vina.utils.Plugin;

public class InventoryClick implements Listener {
    public InventoryClick() {
        Plugin.registerEvent(this);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        if (e.getCurrentItem() == null) {
            return;
        }

        EnhancementUI.onClick(e);
    }
}