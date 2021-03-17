package de.niveax.commands;

import de.niveax.main.Main;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Niveax
 * @version 1.0
 * <p>
 * Copyright © 2021. All rights reserved.
 * <p>
 */
public class GamemodeCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(p.hasPermission("lobbysystem.gamemode")) {
            if(args.length == 1) {
                // Ask for every args[0] if it euquals 0,1,2,3
                    if (args[0].equalsIgnoreCase("0")) {
                        p.setGameMode(GameMode.SURVIVAL);
                        p.sendMessage(Main.Prefix + "§7Du wurdest in den §e§l" + p.getGameMode() + "-MODUS §7gesetzt");
                    } else if (args[0].equalsIgnoreCase("1")) {
                        p.setGameMode(GameMode.CREATIVE);
                        p.sendMessage(Main.Prefix + "§7Du wurdest in den §e§l" + p.getGameMode() + "-MODUS §7gesetzt");
                    } else if (args[0].equalsIgnoreCase("2")) {
                        p.setGameMode(GameMode.ADVENTURE);
                        p.sendMessage(Main.Prefix + "§7Du wurdest in den §e§l" + p.getGameMode() + "-MODUS §7gesetzt");
                    } else if (args[0].equalsIgnoreCase("3")) {
                        p.setGameMode(GameMode.SPECTATOR);
                        p.sendMessage(Main.Prefix + "§7Du wurdest in den §e§l" + p.getGameMode() + "-MODUS §7gesetzt");
                    } else {
                        p.sendMessage(Main.Prefix + "§c/gm [0,1,2,3]");
                    }
            } else {
                p.sendMessage(Main.Prefix + "§c/gm [0,1,2,3]");
            }
        } else {
            p.sendMessage(Main.Prefix + "§cDazu hast du keine Rechte");
        }
        return false;
    }
}
