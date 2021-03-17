package de.niveax.nick;

import de.niveax.MySQL.MySQL;
import de.niveax.MySQL.SQLManager;
import de.niveax.main.Main;
import de.niveax.utils.ItemClass;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author Niveax
 * @version 1.0
 * <p>
 * Copyright © 2021. All rights reserved.
 * <p>
 */
public class NickInteract implements Listener {

    NickMethods nickMethods;


    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        this.nickMethods = new NickMethods();
        Player p = e.getPlayer();
        CraftPlayer craftPlayer = (CraftPlayer) e.getPlayer();
        try {
            // Settings for activate nick tool
            if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§5Nick §7: §cDeaktiviert")) {
                p.getInventory().setItem(5, ItemClass.createItem(Material.NAME_TAG, (byte) 0, "§5Nick §7: §aAktiviert", "§7§oDu bist momentan genickt"));
                ResultSet set;
                set = MySQL.getResult("SELECT * FROM " + SQLManager.nickTable + " WHERE UUID= '" + p.getUniqueId() + "';");
                try {
                    if (set != null) {
                        if (set.next()) {
                            nickPlayer(craftPlayer);
                        } else {
                            String Realname = craftPlayer.getName();
                            String[] peoples = {"§aXXXGamingPvPXX", "§aJantrix", "§afrqe32_", "§aKönigXxL", "§aLionking", "§6xxKing06xx", "§aCuzImCool"};
                            List<String> Nicknames = Arrays.asList(peoples);
                            String name = Nicknames.get(new Random().nextInt(Nicknames.size()));
                            if (nickameIsUsed(name)) {
                                // Finally Nick Player
                                nickMethods.nickPlayer(craftPlayer, name);
                                MySQL.update("INSERT INTO " + SQLManager.nickTable + " (UUID, Playername, Nickname, Nicked) VALUES ('" + craftPlayer.getUniqueId() + "','" + Realname + "','" + name + "',true);");
                                p.sendMessage(Main.Prefix + "§aDein Nickname wurde erfolgreich aktiviert");
                                MySQL.update("INSERT INTO " + SQLManager.nicknamesTable + " (UUID, Nickname) VALUES ('" + craftPlayer.getUniqueId() + "','" + name + "');");
                            } else {
                                nickMethods.nickPlayer(craftPlayer, name);
                                MySQL.update("INSERT INTO " + SQLManager.nickTable + " (UUID, Playername, Nickname, Nicked) VALUES ('" + craftPlayer.getUniqueId() + "','" + Realname + "','" + name + "',true);");
                                p.sendMessage(Main.Prefix + "§aDein Nickname wurde erfolgreich aktiviert");
                                MySQL.update("INSERT INTO " + SQLManager.nicknamesTable + " (UUID, Nickname) VALUES ('" + craftPlayer.getUniqueId() + "','" + name + "');");
                            }
                        }
                    } else {
                        String[] peoples = {"§aXXXGamingPvPXX", "§aJantrix", "§afrqe32_", "§aKönigXxL", "§aLionking", "§6xxKing06xx", "§aCuzImCool"};
                        List<String> Nicknames = Arrays.asList(peoples);
                        String name = Nicknames.get(new Random().nextInt(Nicknames.size()));
                        MySQL.update("INSERT INTO " + SQLManager.nicknamesTable + " (UUID, Nickname) VALUES ('" + craftPlayer.getUniqueId() + "','" + name + "');");
                    }
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }

                // Settings for deactivate nick tool
            } else if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§5Nick §7: §aAktiviert")) {
                ResultSet set;
                set = MySQL.getResult("SELECT * FROM " + SQLManager.nickTable + " WHERE UUID= '" + craftPlayer.getUniqueId() + "';");
                try {
                    if (set != null) {
                        if (set.next()) {
                            // Update MySQL Database
                            String playername = set.getString("Playername");
                            //MySQL.update("DELETE FROM " + SQLManager.nicknamesTable + " WHERE Nickname = '" + craftPlayer.getDisplayName() + "';");
                            MySQL.update("UPDATE " + SQLManager.nickTable + " SET Nickname= '***' WHERE UUID= '" + craftPlayer.getUniqueId() + "';");
                            MySQL.update("UPDATE " + SQLManager.nickTable + " SET Nicked= false WHERE UUID= '" + craftPlayer.getUniqueId() + "';");
                            // Unnick Player
                            nickMethods.unnickPlayer(craftPlayer, playername);

                            p.sendMessage(Main.Prefix + "§cDein Nickname wurde erfolgreich deaktiviert");
                            p.getInventory().setItem(5, ItemClass.createItem(Material.NAME_TAG, (byte) 0, "§5Nick §7: §cDeaktiviert", "§7§oÄndere deinen Namen"));
                        }
                    }
                } catch (Exception exception) {
                }
            }
        } catch (Exception ex) {
        }
    }

    public void nickPlayer(CraftPlayer player) {
        String[] peoples = {"§aXXXGamingPvPXX", "§aJantrix", "§afrqe32_", "§aKönigXxL", "§aLionking", "§6xxKing06xx", "§aCuzImCool"};
        List<String> Nicknames = Arrays.asList(peoples);
        String name = Nicknames.get(new Random().nextInt(Nicknames.size()));
        if (nickameIsUsed(name)) {
            nickMethods.nickPlayer(player, name);
            // Update nick settings for each value in database
            MySQL.update("UPDATE " + SQLManager.nickTable + " SET Nickname= '" + name + "' WHERE UUID= '" + player.getUniqueId() + "';");
            MySQL.update("UPDATE " + SQLManager.nickTable + " SET Nicked= true WHERE UUID= '" + player.getUniqueId() + "';");
            player.sendMessage(Main.Prefix + "§aDein Nickname wurde erfolgreich aktiviert");
            MySQL.update("UPDATE " + SQLManager.nicknamesTable + " SET Nickname= '" + name + "' WHERE UUID= '" + player.getUniqueId() + "';");
        } else {
            nickPlayer(player);
            player.getInventory().setItem(5, ItemClass.createItem(Material.NAME_TAG, (byte) 0, "§5Nick §7: §aAktiviert", "§7§oDu bist momentan genickt"));
        }
    }

    // Method for asking if nickname is already in use
    public boolean nickameIsUsed(String nickname) {
        ResultSet nicknames;
        nicknames = MySQL.getResult("SELECT * FROM " + SQLManager.nicknamesTable + " WHERE Nickname= '" + nickname + "';");
        try {
            assert nicknames != null;
            if (nickname != null) {
                if (nicknames.next()) {
                    return false;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
}
