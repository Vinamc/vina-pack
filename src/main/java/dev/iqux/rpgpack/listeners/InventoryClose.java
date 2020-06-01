package dev.iqux.rpgpack.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import dev.iqux.rpgpack.ui.enhancement.EnhanceUI;
import dev.iqux.rpgpack.utils.Plugin;

public class InventoryClose implements Listener {

    public InventoryClose() {
        Plugin.registerEvent(this);
    }

    @EventHandler
    public void onClick(InventoryCloseEvent e) {
        EnhanceUI.onClose(e);
    }
}