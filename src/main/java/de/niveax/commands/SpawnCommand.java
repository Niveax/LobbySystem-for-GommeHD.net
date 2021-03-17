package de.niveax.commands;

import de.niveax.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

/**
 * @author Niveax
 * @version 1.0
 * <p>
 * Copyright © 2021. All rights reserved.
 * <p>
 */
public class SpawnCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(args.length == 0) {
            // Get values from config and teleport player
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
            p.sendMessage(Main.Prefix + "§7Du wurdest zum §e§lSpawn §7teleportiert.");
        } else {
            p.sendMessage(Main.Prefix + "§c/Spawn");
        }
        return false;
    }
}
