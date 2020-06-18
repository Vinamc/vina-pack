package dev.iqux.vina.services.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import dev.iqux.vina.app.enchance.EnhancementUI;
import dev.iqux.vina.utils.Plugin;

public class InventoryClose implements Listener {
    public InventoryClose() {
        Plugin.registerEvent(this);
    }

    @EventHandler
    public void onClick(InventoryCloseEvent e) {
        EnhancementUI.onClose(e);
    }
}