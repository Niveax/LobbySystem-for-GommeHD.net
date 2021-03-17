package de.niveax.commands;

import de.niveax.listener.Joinlistener;
import de.niveax.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author Niveax
 * @version 1.0
 * <p>
 * Copyright © 2021. All rights reserved.
 * <p>
 */
public class BuildCommand implements CommandExecutor {

    public static ArrayList<UUID> buildmode = new ArrayList<>();

    private Joinlistener joinlistener;


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(p.hasPermission("lobbysystem.build")) {
            if(args.length == 0) {
                // Adding player to ArrayList (build)
                if(!buildmode.contains(p.getUniqueId())) {
                    buildmode.add(p.getUniqueId());
                    p.sendMessage(Main.Prefix + "§aDu bist nun im Baumodus");
                } else {
                    // Remove player from ArrayList (build)
                    this.joinlistener = new Joinlistener();
                    joinlistener.setItemsForPlayer(p);
                    buildmode.remove(p.getUniqueId());
                    p.sendMessage(Main.Prefix + "§cDu bist nun nicht mehr im Baumodus");
                }
            } else {
                p.sendMessage(Main.Prefix + "§c/Build");
            }
        } else {
            p.sendMessage(Main.Prefix + "§cDazu hast du keine Rechte");
        }
        return false;
    }

}
