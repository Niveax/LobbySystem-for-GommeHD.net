package de.niveax.main;

import de.niveax.MySQL.MySQL;
import de.niveax.commands.*;
import de.niveax.extras.*;
import de.niveax.listener.AllSaveEvents;
import de.niveax.listener.Joinlistener;
import de.niveax.listener.PlayerHider;
import de.niveax.listener.QuitListener;
import de.niveax.navigator.InventoryClickListener;
import de.niveax.navigator.InteractListener;
import de.niveax.navigator.SetSpawnCommand;
import de.niveax.nick.ChatEvent;
import de.niveax.nick.NickInteract;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public final class Main extends JavaPlugin {

    public static String Prefix = "§7[§6Lobby§7] ";

    public static double Version = 1.0;

    private MySQL mySQL;

    private static Main plugin;



    @Override
    public void onEnable() {
        this.mySQL = new MySQL(this);
        mySQL.connect("localhost", 3306, "lobbysystem", "root", "");
        Bukkit.getConsoleSender().sendMessage("§aVerbindung zu der Datenbank erfolgreich hergestellt..");

        plugin = this;

        registerListener();
        registerCommands();


        Bukkit.getConsoleSender().sendMessage(Prefix + "§aPlugin wurde erfolgreich aktiviert");
        Bukkit.getConsoleSender().sendMessage(Prefix + "§bVersion: " + Version);
        Bukkit.getConsoleSender().sendMessage(Prefix + "§ePlugin by Niveax");

    }

    @Override
    public void onDisable() {
        mySQL.disconnect();
        Bukkit.getConsoleSender().sendMessage("§cVerbindung zu der Datenbank wurde erfolgreich getrennt");

        Bukkit.getConsoleSender().sendMessage(Prefix + "§cPlugin wurde erfolgreich deaktiviert");
        Bukkit.getConsoleSender().sendMessage(Prefix + "§bVersion: " + Version);
        Bukkit.getConsoleSender().sendMessage(Prefix + "§ePlugin by Niveax");
    }

    public void registerListener() {
        Bukkit.getPluginManager().registerEvents(new Joinlistener(), this);
        Bukkit.getPluginManager().registerEvents(new AllSaveEvents(), this);
        Bukkit.getPluginManager().registerEvents(new InteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerHider(), this);
        Bukkit.getPluginManager().registerEvents(new BootsListener(), this);
        Bukkit.getPluginManager().registerEvents(new EnderpearlGadget(), this);
        Bukkit.getPluginManager().registerEvents(new Extras(), this);
        Bukkit.getPluginManager().registerEvents(new FireworkJetPackGadget(), this);
        Bukkit.getPluginManager().registerEvents(new GrapplingHook(), this);
        Bukkit.getPluginManager().registerEvents(new BootsInventory(), this);
        Bukkit.getPluginManager().registerEvents(new TNTGadget(), this);
        Bukkit.getPluginManager().registerEvents(new NickInteract(), this);
        Bukkit.getPluginManager().registerEvents(new ChatEvent(), this);
        Bukkit.getPluginManager().registerEvents(new QuitListener(), this);

    }

    public void registerCommands() {
        this.getCommand("help").setExecutor(new HelpCommand());
        this.getCommand("Joinmessage").setExecutor(new JoinMessageCommand());
        this.getCommand("build").setExecutor(new BuildCommand());
        this.getCommand("gm").setExecutor(new GamemodeCommand());
        this.getCommand("setspawn").setExecutor(new SetSpawnCommand());
        this.getCommand("spawn").setExecutor(new SpawnCommand());
        this.getCommand("hub").setExecutor(new SpawnCommand());
        this.getCommand("Quitmessage").setExecutor(new QuitMessageCommand());

    }

    public static Main getPlugin() {
        return plugin;
    }
}
