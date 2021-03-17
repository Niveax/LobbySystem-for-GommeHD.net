package de.niveax.listener;

import de.niveax.commands.BuildCommand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

/**
 * @author Niveax
 * @version 1.0
 * <p>
 * Copyright © 2021. All rights reserved.
 * <p>
 */
public class AllSaveEvents implements Listener {

    // Class where every event is happening (to get no damage... )

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onFood(FoodLevelChangeEvent e) {
        e.setFoodLevel(20);
        e.setCancelled(true);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if(!BuildCommand.buildmode.contains(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
     }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if(!BuildCommand.buildmode.contains(e.getPlayer().getUniqueId())) {
                e.setCancelled(true);
            }
        }

    @EventHandler
    public void onItemDrop(PlayerPickupItemEvent e) {
        if(!BuildCommand.buildmode.contains(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if(!BuildCommand.buildmode.contains(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemClick(InventoryClickEvent e) {
        if(!BuildCommand.buildmode.contains(e.getWhoClicked().getUniqueId())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onLevel(PlayerLevelChangeEvent e) {
        e.getPlayer().setLevel(2021);
    }
}
