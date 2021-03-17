package de.niveax.nick;

import de.niveax.MySQL.MySQL;
import de.niveax.MySQL.SQLManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import java.sql.ResultSet;

/**
 * @author Niveax
 * @version 1.0
 * <p>
 * Copyright © 2021. All rights reserved.
 * <p>
 */
public class ChatEvent implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        ResultSet set;
        set = MySQL.getResult("SELECT * FROM " + SQLManager.nickTable + " WHERE UUID= '" + p.getUniqueId() + "';");
        if(p.hasPermission("lobbysystem.nick")) {
            try {
                if (set != null) {
                    if (set.next()) {
                        boolean nicked = set.getBoolean("Nicked");
                        String nickname = set.getString("Nickname");
                        String Playername = set.getString("Playername");
                        // Ask if boolean nicked is true an what should happen
                        if (nicked) {
                            p.setDisplayName(nickname);
                            e.setFormat(p.getDisplayName() + "§7: " + e.getMessage());
                        } else {
                            p.setDisplayName(Playername);
                            // Add other Permissions for each rank later
                            if (p.hasPermission("lobbysystem.premium")) {
                                e.setFormat("§6" + p.getDisplayName() + "§7: " + e.getMessage());
                            } else if(p.hasPermission("lobysystem.youtube")) {
                                e.setFormat("§5" + p.getDisplayName() + "§7: " + e.getMessage());
                            } else {
                                e.setFormat("§a" + p.getDisplayName() + "§7: " + e.getMessage());
                            }
                        }
                    }
                }
             } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
