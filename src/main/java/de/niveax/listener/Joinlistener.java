package de.niveax.listener;

import de.niveax.MySQL.MySQL;
import de.niveax.MySQL.SQLManager;
import de.niveax.main.Main;
import de.niveax.nick.NickMethods;
import de.niveax.utils.ItemClass;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * @author Niveax
 * @version 1.0
 * <p>
 * Copyright © 2021. All rights reserved.
 * <p>
 */
public class Joinlistener implements Listener {

    NickMethods nickMethods;

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        // Set different things for player
        setItemsForPlayer(p);
        p.setExp(1);
        p.setLevel(2021);
        p.setHealth(20);
        p.setFoodLevel(20);
        p.setGameMode(GameMode.CREATIVE);


        // Ask for Joinmessageboolean in database
        ResultSet set;
        set = MySQL.getResult("SELECT * FROM " + SQLManager.serverTable + " WHERE ServerIP= '" + p.getServer().getIp() + "';");
        try {
            assert set != null;
            if(set.next()) {
                boolean Joinboolean = set.getBoolean("Joinmessage");
                if(Joinboolean) {
                    e.setJoinMessage(Main.Prefix + "§7Der Spieler §6" + p.getName() + " §7hat den Server betreten.");
                } else {
                    e.setJoinMessage(null);
                }
            } else {
                e.setJoinMessage(Main.Prefix + "§7Der Spieler §6" + p.getName() + " §7hat den Server betreten.");

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        // Ending Asking for Joinmessageboolean in database
    }

    // Set Items for Player when joining
    public void setItemsForPlayer(Player p) {
        nickMethods = new NickMethods();
        CraftPlayer player = (CraftPlayer) p;
        p.getInventory().clear();
        p.getInventory().setItem(0, ItemClass.createItem(Material.COMPASS, (byte) 0 , "§4§lNavigator §7(Rechtsklick)", "§7§oTeleportiere dich wohin du willst"));
        p.getInventory().setItem(4, ItemClass.createItem(Material.BLAZE_ROD, (byte) 0 , "§a§lAlle Spieler sichtbar §7(Rechtsklick)", "§7§oStelle die Spielersichtbarkeit ein"));
        p.getInventory().setItem(8, ItemClass.createItem(Material.SHEARS, (byte) 0 , "§6§lExtras §7(Rechtsklick)", "§7§oEntdecke coole Extras"));
        if (p.hasPermission("lobbysystem.nick")) {
            ResultSet set;
            set = MySQL.getResult("SELECT * FROM " + SQLManager.nickTable + " WHERE UUID= '" + p.getUniqueId() + "';");
            try {
                if(set != null) {
                    if (set.next()) {
                        boolean nicked = set.getBoolean("Nicked");
                        String nickname = set.getString("Nickname");
                        // Query if boolean nicked is true and what should happen
                        if (nicked) {
                            p.getInventory().setItem(5, ItemClass.createItem(Material.NAME_TAG, (byte) 0, "§5Nick §7: §aAktiviert", "§7§oÄndere deinen Namen"));
                            nickMethods.nickPlayer(player, nickname);
                            p.sendMessage(Main.Prefix + "§cDu bist genickt als: " + nickname);
                        } else {
                            p.getInventory().setItem(5, ItemClass.createItem(Material.NAME_TAG, (byte) 0, "§5Nick §7: §cDeaktiviert", "§7§oÄndere deinen Namen"));
                        }
                    } else {
                        p.getInventory().setItem(5, ItemClass.createItem(Material.NAME_TAG, (byte) 0, "§5Nick §7: §cDeaktiviert", "§7§oÄndere deinen Namen"));
                    }
                } else {
                    p.getInventory().setItem(5, ItemClass.createItem(Material.NAME_TAG, (byte) 0, "§5Nick §7: §cDeaktiviert", "§7§oÄndere deinen Namen"));
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }
}
