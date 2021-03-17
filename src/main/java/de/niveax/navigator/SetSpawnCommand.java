package de.niveax.navigator;

import de.niveax.main.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

/**
 * @author Niveax
 * @version 1.0
 * <p>
 * Copyright © 2021. All rights reserved.
 * <p>
 */
public class SetSpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(p.hasPermission("lobbysystem.setspawn")) {
            if(args.length == 1) {
                // Ask if spawn == 1...
                if(args[0].equalsIgnoreCase("1")) {
                    setSpawnConfig(args[0], p.getLocation());
                    p.sendMessage(Main.Prefix + "§aDu hast den Spawn §e" + args[0] + " §agesetzt");
                } else  if(args[0].equalsIgnoreCase("2")) {
                    setSpawnConfig(args[0], p.getLocation());
                    p.sendMessage(Main.Prefix + "§aDu hast den Spawn §e" + args[0] + " §agesetzt");
                } else  if(args[0].equalsIgnoreCase("3")) {
                    setSpawnConfig(args[0], p.getLocation());
                    p.sendMessage(Main.Prefix + "§aDu hast den Spawn §e" + args[0] + " §agesetzt");
                } else  if(args[0].equalsIgnoreCase("4")) {
                    setSpawnConfig(args[0], p.getLocation());
                    p.sendMessage(Main.Prefix + "§aDu hast den Spawn §e" + args[0] + " §agesetzt");
                } else  if(args[0].equalsIgnoreCase("5")) {
                    setSpawnConfig(args[0], p.getLocation());
                    p.sendMessage(Main.Prefix + "§aDu hast den Spawn §e" + args[0] + " §agesetzt");
                } else  if(args[0].equalsIgnoreCase("6")) {
                    setSpawnConfig(args[0], p.getLocation());
                    p.sendMessage(Main.Prefix + "§aDu hast den Spawn §e" + args[0] + " §agesetzt");
                } else  if(args[0].equalsIgnoreCase("7")) {
                    setSpawnConfig(args[0], p.getLocation());
                    p.sendMessage(Main.Prefix + "§aDu hast den Spawn §e" + args[0] + " §agesetzt");
                } else {
                    p.sendMessage(Main.Prefix + "§c/Setspawn [1,2,3,4,5,6,7]");
                }
            }
        } else {
            p.sendMessage(Main.Prefix + "§cDazu hast du keine Rechte");
        }
        return false;
    }

    // Method to set values in config
    public void setSpawnConfig(String args, Location location) {
        File file = new File("plugins//LobbySystem//Spawn.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        cfg.set("Spawn" + args + ".X", location.getX());
        cfg.set("Spawn" + args +  ".Y", location.getY());
        cfg.set("Spawn" + args +  ".Z", location.getZ());
        cfg.set("Spawn" + args +  ".Yaw", location.getYaw());
        cfg.set("Spawn" + args +  ".Pitch", location.getPitch());
        cfg.set("Spawn" + args + ".WeltName", location.getWorld().getName());
        try {
            cfg.save(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
