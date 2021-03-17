package de.niveax.navigator;

import de.niveax.utils.ItemClass;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;


/**
 * @author Niveax
 * @version 1.0
 * <p>
 * Copyright © 2021. All rights reserved.
 * <p>
 */
public class InteractListener implements Listener {

    private Inventory inv = Bukkit.createInventory(null, 3 * 9, "§8» §4Navigator §8- §7Menü");

    @EventHandler
    public void onItemClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        try {
            if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§4§lNavigator §7(Rechtsklick)")) {
                for (int i = 0; i < inv.getSize(); i++) {
                    inv.setItem(i, ItemClass.createItem(Material.getMaterial(160), (byte) 0, "§fPlatzhalter", null));
                }
                setItemsInNavInv();
                p.openInventory(inv);
            }
        } catch (Exception ex) {
        }
    }

    // Method where nav items get set
    public void setItemsInNavInv() {
        inv.setItem(13, ItemClass.createItem(Material.MAGMA_CREAM, (byte) 0, "§6§lSpawn", "§7Zum Spawn"));
        inv.setItem(11, ItemClass.createItem(Material.ARMOR_STAND, (byte) 0, "§c§lTraining", "§7Verbessere dich im Training"));
        inv.setItem(15, ItemClass.createItem(Material.GRASS, (byte) 0, "§a§lSkyWars", "§7Zurzeit der beliebteste Modus"));

    }
}
