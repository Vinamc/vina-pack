package dev.iqux.rpgpack.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import dev.iqux.rpgpack.ui.enhancement.EnhanceUI;
import dev.iqux.rpgpack.utils.Plugin;

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