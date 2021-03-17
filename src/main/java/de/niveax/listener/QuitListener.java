package de.niveax.listener;

import de.niveax.MySQL.MySQL;
import de.niveax.MySQL.SQLManager;
import de.niveax.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

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
public class QuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        ResultSet set;
        set = MySQL.getResult("SELECT * FROM " + SQLManager.serverTable + " WHERE ServerIP= '" + p.getServer().getIp() + "';");
        try {
            assert set != null;
            if(set.next()) {
                boolean quitboolean = set.getBoolean("Quitmessage");
                // Asking if quit boolean is true - then send a message
                if(quitboolean) {
                    e.setQuitMessage(Main.Prefix + "§7Der Spieler §6" + p.getName() + " §7hat den Server verlassen.");;
                } else {
                    e.setQuitMessage(null);
                }
            } else {
                e.setQuitMessage(Main.Prefix + "§7Der Spieler §6" + p.getName() + " §7hat den Server verlassen.");

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
