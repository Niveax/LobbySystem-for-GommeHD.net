package de.niveax.navigator;

import de.niveax.main.Main;
import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * @author Niveax
 * @version 1.0
 * <p>
 * Copyright © 2021. All rights reserved.
 * <p>
 */
public class InventoryClickListener implements Listener {


    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        try {
            if(e.getInventory().getName().equalsIgnoreCase("§8» §4Navigator §8- §7Menü")) {
                e.setCancelled(true);
                // Ask is material is right and what should happen
                if (e.getCurrentItem().getType() == Material.MAGMA_CREAM) {
                    File file = new File("plugins//LobbySystem//Spawn.yml");
                    YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
                    String w = cfg.getString("Spawn1.WeltName");
                    double x = cfg.getDouble("Spawn1.X");
                    double y = cfg.getDouble("Spawn1.Y");
                    double z = cfg.getDouble("Spawn1.Z");
                    double yaw = cfg.getDouble("Spawn1.Yaw");
                    double pitch = cfg.getDouble("Spawn1.Pitch");
                    Location loc = new Location(Bukkit.getWorld(w), x, y, z);
                    loc.setYaw((float) yaw);
                    loc.setPitch((float) pitch);
                    p.teleport(loc);
                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 3, 3);
                    p.sendMessage(Main.Prefix + "§7Du wurdest zum §e§lSpawn §7teleportiert.");

                } else if (e.getCurrentItem().getType() == Material.ARMOR_STAND) {
                    File file = new File("plugins//LobbySystem//Spawn.yml");
                    YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
                    String w = cfg.getString("Spawn2.WeltName");
                    double x = cfg.getDouble("Spawn2.X");
                    double y = cfg.getDouble("Spawn2.Y");
                    double z = cfg.getDouble("Spawn2.Z");
                    double yaw = cfg.getDouble("Spawn2.Yaw");
                    double pitch = cfg.getDouble("Spawn2.Pitch");
                    Location loc = new Location(Bukkit.getWorld(w), x, y, z);
                    loc.setYaw((float) yaw);
                    loc.setPitch((float) pitch);
                    p.teleport(loc);
                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 3, 3);
                    p.sendMessage(Main.Prefix + "§7Du wurdest zum §c§lTraining §7teleportiert.");

                } else if (e.getCurrentItem().getType() == Material.GRASS) {
                    File file = new File("plugins//LobbySystem//Spawn.yml");
                    YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
                    String w = cfg.getString("Spawn3.WeltName");
                    double x = cfg.getDouble("Spawn3.X");
                    double y = cfg.getDouble("Spawn3.Y");
                    double z = cfg.getDouble("Spawn3.Z");
                    double yaw = cfg.getDouble("Spawn3.Yaw");
                    double pitch = cfg.getDouble("Spawn3.Pitch");
                    Location loc = new Location(Bukkit.getWorld(w), x, y, z);
                    loc.setYaw((float) yaw);
                    loc.setPitch((float) pitch);
                    p.teleport(loc);
                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 3, 3);
                    p.sendMessage(Main.Prefix + "§7Du wurdest zu §a§lSkyWars §7teleportiert.");
                } else {
                    p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 3, 3);
                    p.closeInventory();
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
