package de.niveax.commands;

import de.niveax.main.Main;
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
public class HelpCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;

        p.sendMessage(Main.Prefix + "§cDieses Plugin wurde nur für das GommeHD.net Team programmiert");
        p.sendMessage(Main.Prefix + "§bDas Plugin wurde von Niveax programmiert.");
        p.sendMessage(Main.Prefix + "§cDazu hast du keine Rechte");
        return false;
    }
}
