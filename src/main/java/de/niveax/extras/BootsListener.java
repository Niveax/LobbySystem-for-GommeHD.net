package de.niveax.extras;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.UUID;


/**
 * @author Niveax
 * @version 1.0
 * <p>
 * Copyright © 2021. All rights reserved.
 * <p>
 */
public class BootsListener implements Listener {

    public static ArrayList<UUID> LoveEffects = new ArrayList<>();
    public static ArrayList<UUID> CloudEffects = new ArrayList<>();

    @EventHandler
    public void onFly(PlayerToggleFlightEvent e) {
        Player p = e.getPlayer();
        if(p.getInventory().getBoots() != null) {
            if(p.getInventory().getBoots().getItemMeta().getDisplayName() != null) {
                // set switch case and ask what should happen there
                switch(p.getInventory().getBoots().getItemMeta().getDisplayName()) {
                    case "§6Double Jump":
                        e.setCancelled(true);
                        p.setAllowFlight(false);
                        p.setFlying(false);
                        p.setVelocity(p.getLocation().getDirection().multiply(1).add(new Vector(0,0.5,0)));
                        p.playSound(p.getLocation(), Sound.CAT_HISS, 1, 1);
                        break;
                    case "§bFly":
                        p.setAllowFlight(true);
                        p.setFlying(true);
                        break;
                    }
                }
            }
        }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if(p.getInventory().getBoots() != null) {
            if(p.getInventory().getBoots().getItemMeta().getDisplayName() != null) {
                // Ask for display name and ask what should happen there
                if ("§6Double Jump".equals(p.getInventory().getBoots().getItemMeta().getDisplayName())) {
                    if (p.getLocation().add(0, -0.5, 0).getBlock().getType() != Material.AIR) {
                        p.setAllowFlight(true);
                        p.setFlying(false);
                    }
                } else if("§4Love".equals(p.getInventory().getBoots().getItemMeta().getDisplayName())) {
                    if (LoveEffects.contains(p.getUniqueId())) {
                        p.setAllowFlight(false);
                        p.playEffect(p.getLocation(), Effect.HEART, 3);
                    }
                    } else if("§8Cloud".equals(p.getInventory().getBoots().getItemMeta().getDisplayName())) {
                    if (CloudEffects.contains(p.getUniqueId())) {
                        p.setAllowFlight(false);
                        p.playEffect(p.getLocation(), Effect.CLOUD, 8);
                    }
                } else if("§bFly".equals(p.getInventory().getBoots().getItemMeta().getDisplayName())) {
                    p.setAllowFlight(true);
                }
            }
        }
    }
}
