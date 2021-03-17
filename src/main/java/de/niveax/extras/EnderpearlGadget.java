package de.niveax.extras;

import de.niveax.main.Main;
import de.niveax.utils.ItemClass;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;

import java.util.HashMap;

/**
 * @author Niveax
 * @version 1.0
 * <p>
 * Copyright © 2021. All rights reserved.
 * <p>
 */
public class EnderpearlGadget implements Listener {

    private HashMap<Player, EnderPearl> enderpearl = new HashMap<>();


    @EventHandler
    public void onEnderpearlGadget(PlayerInteractEvent e) {
        if(e.getItem() != null && e.getItem().getType() == Material.ENDER_PEARL && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§b§lEnderperle") ) {
                Player p = e.getPlayer();
                e.setCancelled(true);

                // Item while scheduler is running
                p.getInventory().setItem(7, ItemClass.createItem(Material.BARRIER, (byte) 0, "§cWarte..", null));
                p.updateInventory();

                EnderPearl enderPearl = p.launchProjectile(EnderPearl.class);
                enderPearl.setPassenger(p);
                enderpearl.put(p, enderPearl);

                Bukkit.getScheduler().runTaskLater(Main.getPlugin(), () -> {
                    p.getInventory().setItem(7, ItemClass.createItem(Material.ENDER_PEARL, (byte) 0, "§b§lEnderperle", "§7§oReite auf einer Enderperle mit"));
                    p.updateInventory();
                }, 20*5);
            }
        }

    @EventHandler
    public void onVehicleLeave(VehicleExitEvent e) {
        if(e.getExited() instanceof Player) {
            // What should happen if player exit
            if(enderpearl.containsKey(e.getExited())) {
                enderpearl.get(e.getExited()).remove();
            }
        }
    }
}

