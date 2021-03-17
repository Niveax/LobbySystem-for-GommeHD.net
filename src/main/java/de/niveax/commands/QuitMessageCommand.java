package de.niveax.commands;

import de.niveax.MySQL.MySQL;
import de.niveax.MySQL.SQLManager;
import de.niveax.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Niveax
 * @version 1.0
 * <p>
 * Copyright © 2021. All rights reserved.
 * <p>
 * @date 17.03.2021
 */
public class QuitMessageCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;

        if (p.hasPermission("lobbysystem.quitmessage")) {
            ResultSet set;
            set = MySQL.getResult("SELECT * FROM " + SQLManager.serverTable + " WHERE Serverip= '" + p.getServer().getIp() + "';");
            try {
                assert set != null;
                if (set.next()) {
                    boolean quitboolean = set.getBoolean("Quitmessage");
                    // Set settings if join boolean is true
                    if (quitboolean == true) {
                        MySQL.update("UPDATE " + SQLManager.serverTable + " SET Quitmessage= false WHERE Serverip= '" + p.getServer().getIp() + "';");
                        p.sendMessage(Main.Prefix + "§cDu hast die Quitmessage deaktiviert");
                    } else {
                        // Set settings if join boolean is false
                        MySQL.update("UPDATE " + SQLManager.serverTable + " SET Quitmessage= true WHERE Serverip= '" + p.getServer().getIp() + "';");
                        p.sendMessage(Main.Prefix + "§aDu hast die Quitmessage aktiviert");
                    }
                } else {
                    // No set.next()
                    MySQL.update("INSERT INTO " + SQLManager.serverTable + " (Serverip, Quitmessage) VALUES ('" + p.getServer().getIp() + "', false);");
                    p.sendMessage(Main.Prefix + "§cDu hast die Quitmessage deaktiviert");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return false;
    }
}
