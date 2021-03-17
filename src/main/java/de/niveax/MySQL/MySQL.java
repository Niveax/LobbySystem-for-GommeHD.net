package de.niveax.MySQL;


import de.niveax.main.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Niveax
 * @version 1.0
 * <p>
 * Copyright © 2021. All rights reserved.
 * <p>
 */
public class MySQL {

    private Main main;

    private static Connection conn;

    private final SQLManager sqlManager;

    public MySQL(Main main) {
        this.main = main;
        this.sqlManager =  new SQLManager(this);
    }


    public void connect(String host, int port, String database, String username, String password) {
        if (!isConnected()) {
            try {
                conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true", username, password);
                // Create all tables for database
                this.sqlManager.createTable();
                this.sqlManager.createNicknameTable();
                this.sqlManager.createNicknamesTable();
                System.out.println("Verbindung mit MySQL erfolgreich hergestelllt!");
            } catch (SQLException e) {
                System.out.println("Konnte sich nicht zur Datenbank verbinden, bitte überprüfe deine Einstellungen.");
            }
        }
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                conn.close();
                conn = null;
                System.out.println("Verbindung wurde geschlossen!");
            } catch (SQLException e) {
                System.out.println("Fehler beim schließen! (close)");
            }
        }
    }

    public static boolean isConnected() {
        return conn != null;
    }

    public static ResultSet update(String qry) {
        if (isConnected()) {
            try {
                conn.createStatement().executeUpdate(qry);
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return null;
    }

    public static ResultSet getResult(String qry) {
        if (isConnected()) {
            try {
                return conn.createStatement().executeQuery(qry);
            } catch (SQLException e) {
                System.out.println("Fehler: (getResult)");
            }
        }
        return null;
    }
}
