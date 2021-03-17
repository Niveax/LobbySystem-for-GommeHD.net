package de.niveax.extras;

import de.niveax.main.Main;
import de.niveax.utils.ItemClass;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * @author Niveax
 * @version 1.0
 * <p>
 * Copyright © 2021. All rights reserved.
 * <p>
 */
public class BootsInventory implements Listener {

    Inventory inv = Bukkit.createInventory(null, 9*1, "§8» §5Schuhe §8- §7Menü");

    Extras extras;

    // Set items to boots inventory
    public void setBootsInInv(Player p) {
        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, ItemClass.createItem(Material.getMaterial(160), (byte) 0 , "§fPlatzhalter", null));
        }
        inv.setItem(0, ItemClass.createItem(Material.ARROW, (byte) 0 , "§7Zurück", "§7§oKehre zurück zur Hauptseite"));
        inv.setItem(1,  ItemClass.createBootsItem(Material.LEATHER_BOOTS, Color.ORANGE,"§6Double Jump", "§7§o§nSchuhe mit viel Liebe"));
        inv.setItem(3,  ItemClass.createBootsItem(Material.LEATHER_BOOTS, Color.RED,"§4Love", "§7§o§nSchuhe mit viel Liebe"));
        inv.setItem(5,  ItemClass.createBootsItem(Material.LEATHER_BOOTS, Color.GRAY,"§8Cloud", "§7§o§nLaufe über Wolken"));
        inv.setItem(7,  ItemClass.createBootsItem(Material.LEATHER_BOOTS, Color.fromBGR(233, 255, 0),"§bFly", "§7§o§nFliege wohin du willst"));
        inv.setItem(8, ItemClass.createItem(Material.BARRIER, (byte) 0 , "§cSchuhe entfernen", "§7§oEntferne deine Schuhe"));
        p.openInventory(inv);
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        this.extras = new Extras();
        Player p = (Player) e.getWhoClicked();
        if(e.getInventory().getName().equalsIgnoreCase("§8» §5Schuhe §8- §7Menü")) {
            // Ask for every display name - set settings if it was clicked
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Zurück")) {
                p.setAllowFlight(false);
                p.openInventory(extras.inv);
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Double Jump")) {
                ItemStack doubleJumpBoots = ItemClass.createBootsItem(Material.LEATHER_BOOTS, Color.ORANGE,"§6Double Jump", "§7§o§nSpringe doppelt");
                p.getInventory().setBoots(doubleJumpBoots);
                BootsListener.LoveEffects.remove(p.getUniqueId());
                BootsListener.CloudEffects.remove(p.getUniqueId());
                p.closeInventory();
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§4Love")) {
                if(p.hasPermission("lobbysystem.loveboots")) {
                    ItemStack loveBoots = ItemClass.createBootsItem(Material.LEATHER_BOOTS, Color.RED, "§4Love", "§7§o§nSchuhe mit viel Liebe");
                    p.getInventory().setBoots(loveBoots);
                    BootsListener.LoveEffects.add(p.getUniqueId());
                    BootsListener.CloudEffects.remove(p.getUniqueId());
                    p.closeInventory();
                } else {
                    p.sendMessage(Main.Prefix + "§eDu benötigst eine §6Premium§e-Rang, um das Gadget zu verwenden");
                }
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8Cloud")) {
                if(p.hasPermission("lobbysystem.loveboots")) {
                    ItemStack CloudBoots = ItemClass.createBootsItem(Material.LEATHER_BOOTS, Color.GRAY, "§8Cloud", "§7§o§nLaufe über Wolken");
                    p.getInventory().setBoots(CloudBoots);
                    BootsListener.CloudEffects.add(p.getUniqueId());
                    BootsListener.LoveEffects.remove(p.getUniqueId());
                    p.closeInventory();
                } else {
                    p.sendMessage(Main.Prefix + "§eDu benötigst eine §6Premium§e-Rang, um das Gadget zu verwenden");
                }
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bFly")) {
                if (p.hasPermission("lobbysystem.loveboots")) {
                    ItemStack FlyBoots = ItemClass.createBootsItem(Material.LEATHER_BOOTS, Color.fromBGR(233, 255, 0), "§bFly", "§7§o§nFliege wohin du willst");
                    p.getInventory().setBoots(FlyBoots);
                    BootsListener.LoveEffects.remove(p.getUniqueId());
                    BootsListener.CloudEffects.remove(p.getUniqueId());
                    p.closeInventory();
                } else {
                    p.sendMessage(Main.Prefix + "§eDu benötigst eine §6Premium§e-Rang, um das Gadget zu verwenden");
                }
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cSchuhe entfernen")) {
                if (p.getInventory().getBoots() != null) {
                    ItemStack RemoveBoots = new ItemStack(Material.AIR, 1);
                    p.getInventory().setBoots(RemoveBoots);
                    p.sendMessage(Main.Prefix + "§cDeine Schuhe wurden entfernt");
                    p.closeInventory();
                } else {
                    p.closeInventory();
                }
            } else {
                p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 3 ,3);
                p.closeInventory();
            }
        }
    }
}
