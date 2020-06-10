package dev.iqux.vina.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import dev.iqux.vina.ui.enhancement.EnhanceUI;
import dev.iqux.vina.utils.Plugin;

public class InventoryClose implements Listener {

    public InventoryClose() {
        Plugin.registerEvent(this);
    }

    @EventHandler
    public void onClick(InventoryCloseEvent e) {
        EnhanceUI.onClose(e);
    }
}