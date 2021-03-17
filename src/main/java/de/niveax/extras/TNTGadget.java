package de.niveax.extras;

import de.niveax.main.Main;
import de.niveax.utils.ItemClass;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

/**
 * @author Niveax
 * @version 1.0
 * <p>
 * Copyright © 2021. All rights reserved.
 * <p>
 */
public class TNTGadget implements Listener {


    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if(e.getBlock().getType() == Material.TNT) {
            e.getBlock().setType(Material.AIR);
            TNTPrimed tnt = (TNTPrimed)e.getBlock().getWorld().spawn(e.getBlock().getLocation(), TNTPrimed.class);
            tnt.setFuseTicks(40);

            // Item while scheduler is running
            p.getInventory().setItem(7, ItemClass.createItem(Material.BARRIER, (byte) 0, "§cWarte...", null));
            p.setGameMode(GameMode.ADVENTURE);

            Bukkit.getScheduler().runTaskLater(Main.getPlugin(), new Runnable() {
                @Override
                public void run() {
                    p.getInventory().setItem(7,  ItemClass.createItem(Material.TNT, (byte) 0, "§cTNT", null));
                    p.setGameMode(GameMode.SURVIVAL);
                    p.updateInventory();
                }
            }, 20 * 3);
        }
    }
    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e) {
        e.blockList().clear();
    }
}
