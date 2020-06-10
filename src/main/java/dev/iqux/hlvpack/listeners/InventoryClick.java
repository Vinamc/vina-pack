package dev.iqux.hlvpack.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import dev.iqux.hlvpack.ui.enhancement.EnhanceUI;
import dev.iqux.hlvpack.utils.Plugin;

public class InventoryClick implements Listener
{
    public InventoryClick() {
        Plugin.registerEvent(this);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        if (e.getCurrentItem() == null) {
            return;
        }

        EnhanceUI.onClick(e);
    }
}