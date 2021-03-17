package de.niveax.MySQL;


/**
 * @author Niveax
 * @version 1.0
 * <p>
 * Copyright © 2021. All rights reserved.
 * <p>
 */
public class SQLManager {

    private MySQL mySQL;

    public static String serverTable = "Servers";
    public static String nickTable = "NickedPlayers";
    public static String nicknamesTable = "Nicknames";

    public SQLManager(MySQL mySQL) {
        this.mySQL = mySQL;
    }

    public void createTable() {
        mySQL.update("CREATE TABLE IF NOT EXISTS " + serverTable + " (ServerIP TEXT DEFAULT NULL, Joinmessage BOOLEAN DEFAULT true, Quitmessage BOOLEAN DEFAULT true);");
    }

    public void createNicknameTable() {
        mySQL.update("CREATE TABLE IF NOT EXISTS " + nickTable + " (UUID TEXT DEFAULT NULL, Playername TEXT DEFAULT NULL, Nickname TEXT DEFAULT NULL, Nicked BOOLEAN DEFAULT false);");
    }
    public void createNicknamesTable() {
        mySQL.update("CREATE TABLE IF NOT EXISTS " + nicknamesTable + " (UUID TEXT DEFAULT NULL, Nickname TEXT DEFAULT NULL);");
    }
}
