package de.niveax.listener;

import de.niveax.utils.ItemClass;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;


/**
 * @author Niveax
 * @version 1.0
 * <p>
 * Copyright © 2021. All rights reserved.
 * <p>
 */
public class PlayerHider implements Listener {


    Inventory inv = Bukkit.createInventory(null,  9*3, "§8» §a§lSpieler Sichtbarkeit");

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        try {
            // What should happen when player get this display names
            if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a§lAlle Spieler sichtbar §7(Rechtsklick)") || 
                    (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lKeine Spieler sichtbar §7(Rechtsklick)")
                        || e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§5§lNur VIPs sichtbar §7(Rechtsklick)"))) {
                    openPlayerHiderInventory(p);
                }
            } catch (Exception ex) {
        }
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        try {
            // Ask for every display name - set settings if it was clicked
            if (e.getInventory().getName().equalsIgnoreCase("§8» §a§lSpieler Sichtbarkeit")) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a§lAlle Spieler sichtbar")) {
                    p.getInventory().setItem(4, ItemClass.createItem(Material.BLAZE_ROD, (byte) 0, "§a§lAlle Spieler sichtbar §7(Rechtsklick)", "§7§oStelle die Spielersichtbarkeit ein"));
                    p.closeInventory();
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        p.showPlayer(all);
                    }
                } else {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lKeine Spieler sichtbar")) {
                        p.getInventory().setItem(4, ItemClass.createItem(Material.BLAZE_ROD, (byte) 0, "§c§lKeine Spieler sichtbar §7(Rechtsklick)", "§7§oStelle die Spielersichtbarkeit ein"));
                        p.closeInventory();
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            p.hidePlayer(all);
                        }
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§5§lNur VIPs sichtbar")) {
                        p.getInventory().setItem(4, ItemClass.createItem(Material.BLAZE_ROD, (byte) 0, "§5§lNur VIPs sichtbar §7(Rechtsklick)", "§7§oStelle die Spielersichtbarkeit ein"));
                        p.closeInventory();
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (!all.hasPermission("lobbysystem.vip")) {
                                p.hidePlayer(all);
                            } else {
                                p.showPlayer(all);
                            }
                        }
                    } else {
                        p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 3, 3);
                        p.closeInventory();
                    }
                }
            }
        } catch (Exception ex) {

        }
    }

    // Method where player hider inventory get open (set items for inv)
    public void openPlayerHiderInventory(Player p) {
        for(int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, ItemClass.createItem(Material.getMaterial(160), (byte) 0 , "§fPlatzhalter", null));
        }
        inv.setItem(11, ItemClass.createItem(Material.STAINED_GLASS_PANE, (byte) 5, "§a§lAlle Spieler sichtbar", "§7§oLasse dir alle Spieler anzeigen"));
        inv.setItem(13, ItemClass.createItem(Material.STAINED_GLASS_PANE, (byte) 14, "§c§lKeine Spieler sichtbar", "§7§oLasse dir keinen Spieler anzeigen"));
        inv.setItem(15, ItemClass.createItem(Material.STAINED_GLASS_PANE, (byte) 10, "§5§lNur VIPs sichtbar", "§7§oLasse dir nur bestimmte Spieler anzeigen"));
        p.openInventory(inv);
    }

}
