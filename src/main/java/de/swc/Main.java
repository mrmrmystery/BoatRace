package de.swc;

import com.dieselpoint.norm.Database;
import com.dieselpoint.norm.sqlmakers.MySqlMaker;
import de.swc.commands.BoatRaceCommand;
import de.swc.game.Checkpoint;
import de.swc.game.Track;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Main extends JavaPlugin {

    private static Db db;
    private static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        // Plugin startup logic
        if (!getDataFolder().exists()) getDataFolder().mkdir();
        saveDefaultConfig();

        getCommand("boatrace").setExecutor(new BoatRaceCommand());

        try{

            db = new Db();

            try{
                db.createTable(Track.class);
                db.createTable(Checkpoint.class);
            }catch (Exception e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public static Database getDb() {
        return db;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getPlugin() {
        return plugin;
    }
}
