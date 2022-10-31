package fr.zeyx.gamecreator;

import fr.zeyx.gamecreator.commands.GameCreatorCommand;
import fr.zeyx.gamecreator.database.Database;
import fr.zeyx.gamecreator.database.SQLite;
import fr.zeyx.gamecreator.listeners.BlockPlaceListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class GameCreator extends JavaPlugin {

    private static GameCreator instance;
    private Database database;

    @Override
    public void onEnable() {
        instance = this;

        this.saveDefaultConfig();
        initDatabase();
        registerCommands();
        registerEvents();

        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[§6GameCreator&8] &aPlugin enabled!"));
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[§6GameCreator&8] &cPlugin disabled!"));
    }

    private void initDatabase() {
        this.database = new SQLite();
        this.database.load();
    }

    private void registerCommands() {
        getCommand("gamecreator").setExecutor(new GameCreatorCommand());
    }

    private void registerEvents() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new BlockPlaceListener(), this);
    }

    public static GameCreator getInstance() {
        return instance;
    }

    public Database getDatabase() {
        return database;
    }

}