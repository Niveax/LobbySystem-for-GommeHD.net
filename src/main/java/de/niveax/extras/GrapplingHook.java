package de.niveax.extras;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Fish;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.util.Vector;

/**
 * @author Niveax
 * @version 1.0
 * <p>
 * Copyright © 2021. All rights reserved.
 * <p>
 */
public class GrapplingHook implements Listener {


    @EventHandler
    public void onFish(PlayerFishEvent e) {
        Player player = e.getPlayer();
        Fish h = e.getHook();
        // Query what could happen (grappling hook)
        if (((e.getState() == PlayerFishEvent.State.IN_GROUND) ||
                    (e.getState().equals(PlayerFishEvent.State.CAUGHT_ENTITY)) ||
                    (e.getState().equals(PlayerFishEvent.State.FAILED_ATTEMPT))) &&
                    (org.bukkit.Bukkit.getWorld(e.getPlayer().getWorld().getName()).getBlockAt(h.getLocation().getBlockX(),
                            h.getLocation().getBlockY() - 1, h.getLocation().getBlockZ())
                            .getType() != Material.AIR)) {
                Location lc = player.getLocation();
                Location to = e.getHook().getLocation();
                lc.setY(lc.getY() + 0.5D);
                player.teleport(lc);
                player.playSound(player.getLocation(), org.bukkit.Sound.ENDERDRAGON_WINGS, 1.0F, 1.0F);

                double g = -0.08D;
                double d = to.distance(lc);
                double t = d;
                double v_x = (1.0D + 0.07D * t) * (to.getX() - lc.getX()) / t;
                double v_y = (1.0D + 0.03D * t) * (to.getY() - lc.getY()) / t - 0.5D * g * t;
                double v_z = (1.0D + 0.07D * t) * (to.getZ() - lc.getZ()) / t;

                Vector v = player.getVelocity();
                v.setX(v_x);
                v.setY(v_y);
                v.setZ(v_z);
                player.setVelocity(v);
            }
        }
    }
