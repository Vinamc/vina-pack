package dev.iqux.hlvpack.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import dev.iqux.hlvpack.ui.enhancement.EnhanceUI;
import dev.iqux.hlvpack.utils.Plugin;

public class InventoryClose implements Listener {

    public InventoryClose() {
        Plugin.registerEvent(this);
    }

    @EventHandler
    public void onClick(InventoryCloseEvent e) {
        EnhanceUI.onClose(e);
    }
}