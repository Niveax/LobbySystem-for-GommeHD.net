package de.niveax.extras;

import de.niveax.main.Main;
import de.niveax.utils.ItemClass;
import org.bukkit.Bukkit;
import org.bukkit.Material;
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
public class Extras implements Listener {

    public Inventory inv;

    BootsInventory bootsInventory;

    // Set items to extras inventory
    public Extras() {
    this.bootsInventory = new BootsInventory();
        inv = Bukkit.createInventory(null, 18, "§8» §6Extras §8- §7Menü");
        for (int i = 0; i < inv.getSize(); i++) {
                inv.setItem(i, ItemClass.createItem(Material.getMaterial(160), (byte) 0 , "§fPlatzhalter", null));
        }
        inv.setItem(0,  ItemClass.createItem(Material.STAINED_GLASS_PANE,(byte) 14,"§cSchliessen", "§7§oSchliesse das Inventar"));
        inv.setItem(2,  ItemClass.createItem(Material.ENDER_PEARL,(byte) 0,"§bEnderperle", "§7§oReite auf einer Enderperle mit"));
        inv.setItem(4,  ItemClass.createItem(Material.FIREWORK,(byte) 0, "§9Feuerwerk", "§7§oLass Silvester bei dir entstehen"));
        inv.setItem(6,  ItemClass.createItem(Material.FISHING_ROD,(byte) 0,"§8Enterhaken", "§7§oAngel dich wohin du willst"));
        inv.setItem(10, ItemClass.createItem(Material.IRON_BOOTS,(byte) 0, "§5Schuhe", "§7§oWähle Schuhe mit Effekten aus"));
        inv.setItem(12,  ItemClass.createItem(Material.TNT,(byte) 0,"§4TNT", "§7§oBOOM"));
        inv.setItem(14,  ItemClass.createItem(Material.NETHER_STAR,(byte) 0,"§cJetpack", "§7§oFühle dich wie Ironman"));
        inv.setItem(17,  ItemClass.createItem(Material.BARRIER,(byte) 0,"§4Gadget entfernen", "§7§oEntferne dein Gadget"));

    }

    @EventHandler
    public void onClick1(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        try {
            if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lExtras §7(Rechtsklick)")) {
                p.openInventory(inv);
            }
        } catch (Exception e1) {
        }
    }


    @EventHandler
    public void onClick(InventoryClickEvent e) {
        try {
            if (e.getInventory().getName().equalsIgnoreCase("§8» §6Extras §8- §7Menü")) {
                Player p = (Player) e.getWhoClicked();
                Material material = e.getCurrentItem().getType();
                // Ask for every material - set settings if it was clicked
                if (material == Material.FISHING_ROD) {
                    p.getInventory().setItem(7, ItemClass.createItem(Material.FISHING_ROD, (byte) 0, "§8Enterhaken", "§7§oAngel dich wohin du willst"));
                    p.closeInventory();
                } else {
                    if (material == Material.ENDER_PEARL) {
                        if (p.hasPermission("lobbysystem.enderperle")) {
                            p.getInventory().setItem(7, ItemClass.createItem(Material.ENDER_PEARL, (byte) 0, "§b§lEnderperle", "§7§oReite auf einer Enderperle mit"));
                            p.closeInventory();
                        } else {
                            p.closeInventory();
                            p.sendMessage(Main.Prefix + "§eDu benötigst eine §6Premium§e-Rang, um das Gadget zu verwenden");
                        }

                    } else {
                        if (material == Material.FIREWORK) {
                            if (p.hasPermission("lobbysystem.feuerwerk")) {
                                p.getInventory().setItem(7, ItemClass.createItem(Material.FIREWORK, (byte) 0, "§9FeuerWerk", "§7§oLass Silvester bei dir entstehen"));
                                p.closeInventory();
                            } else {
                                p.sendMessage(Main.Prefix + "§eDu benötigst eine §6Premium§e-Rang, um das Gadget zu verwenden");
                                p.closeInventory();
                            }

                        } else {
                            if (material == Material.NETHER_STAR) {
                                if (p.hasPermission("lobbysystem.jetpack")) {
                                    p.getInventory().setItem(7, ItemClass.createItem(Material.NETHER_STAR, (byte) 0, "§cJetpack", "§7§oFühle dich wie Ironman"));
                                    p.closeInventory();
                                } else {
                                    p.sendMessage(Main.Prefix + "§eDu benötigst eine §6Premium§e-Rang, um das Gadget zu verwenden");
                                    p.closeInventory();
                                }

                            } else {
                                if (material == Material.TNT) {
                                    if (p.hasPermission("lobbysystem.jetpack")) {
                                        p.getInventory().setItem(7, ItemClass.createItem(Material.TNT, (byte) 0, "§4TNT", "§o§lBOOM"));
                                        p.closeInventory();
                                    } else {
                                        p.sendMessage(Main.Prefix + "§eDu benötigst eine §6Premium§e-Rang, um das Gadget zu verwenden");
                                        p.closeInventory();
                                    }

                                } else if(material == Material.IRON_BOOTS) {
                                  if (p.hasPermission("lobbysystem.boots")) {
                                    bootsInventory.setBootsInInv(p);
                                  } else {
                                      p.sendMessage(Main.Prefix + "§eDu benötigst eine §6Premium§e-Rang, um das Gadget zu verwenden");
                                      p.closeInventory();
                                  }

                                } else if (material == Material.BARRIER) {
                                    if(p.getInventory().getItem(7) == null) {
                                        p.closeInventory();
                                    } else {
                                        p.getInventory().setItem(7, null);
                                        p.sendMessage(Main.Prefix + "§cDu hast nun kein Gadget mehr");
                                        p.closeInventory();
                                    }

                                } else {
                                    p.closeInventory();
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e1) {

        }

    }

}
