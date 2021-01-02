package me.mickydoesmc.btd.blocktowerdefense;

import me.mickydoesmc.btd.blocktowerdefense.Commands.SpawnBalloonCommand;
import me.mickydoesmc.btd.blocktowerdefense.Events.BTDTickEvent;
import me.mickydoesmc.btd.blocktowerdefense.Events.TowerPlacement;
import me.mickydoesmc.btd.blocktowerdefense.Listeners.BalloonMoving;
import me.mickydoesmc.btd.blocktowerdefense.Listeners.BalloonSpawn;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public final class BlockTowerDefense extends JavaPlugin {

    private static boolean debugMode = true;
    private static BukkitRunnable gameTickEvent;
    public static ArrayList<ArmorStand> balloons = new ArrayList<>();

    public static ArrayList<ArmorStand> getBalloons() {
        return balloons;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Enabled BlockTowerDefense by Mickydoesmc");

//        Start BTDTickEvent
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getPluginManager().callEvent(new BTDTickEvent());
            }
        }.runTaskTimer(this,0,10);


//        Commands
        getCommand("spawnballoon").setExecutor(new SpawnBalloonCommand());


//        Event Listeners
        Bukkit.getPluginManager().registerEvents(new BalloonSpawn(), this);
        Bukkit.getPluginManager().registerEvents(new BalloonMoving(), this);
        Bukkit.getPluginManager().registerEvents(new TowerPlacement(), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public boolean debug(String msg) {
        if (debugMode) {
            this.getLogger().info("[DEBUG] " + msg);
        }
        return debugMode;
    }
}
