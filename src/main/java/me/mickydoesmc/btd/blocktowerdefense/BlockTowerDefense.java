package me.mickydoesmc.btd.blocktowerdefense;

import me.mickydoesmc.btd.blocktowerdefense.Balloons.Balloon;
import me.mickydoesmc.btd.blocktowerdefense.Commands.JoinCommand;
import me.mickydoesmc.btd.blocktowerdefense.Commands.LeaveCommand;
import me.mickydoesmc.btd.blocktowerdefense.Commands.ListCommand;
import me.mickydoesmc.btd.blocktowerdefense.Commands.SpawnBalloonCommand;
import me.mickydoesmc.btd.blocktowerdefense.Events.BTDTickEvent;
import me.mickydoesmc.btd.blocktowerdefense.Games.Game.Game;
import me.mickydoesmc.btd.blocktowerdefense.Games.GameManager;
import me.mickydoesmc.btd.blocktowerdefense.Listeners.TowerPlacement;
import me.mickydoesmc.btd.blocktowerdefense.Listeners.BalloonMoving;
import me.mickydoesmc.btd.blocktowerdefense.Listeners.BalloonSpawn;
import me.mickydoesmc.btd.blocktowerdefense.Listeners.GeneralListeners;
import me.mickydoesmc.btd.blocktowerdefense.Towers.Tower;
import me.mickydoesmc.btd.blocktowerdefense.Utils.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public final class BlockTowerDefense extends JavaPlugin {

    private static boolean debugMode = true;

    private int ticksPassed = 0;

    private static ArrayList<Balloon> balloons = new ArrayList<>();
    public static ArrayList<Balloon> getBalloons() {
        return balloons;
    }

    private static ArrayList<Tower> towers = new ArrayList<>();

    public static ArrayList<Tower> getTowers() {
        return towers;
    }


    private static BlockTowerDefense instance;
    public BlockTowerDefense() { instance = this;}
    public static BlockTowerDefense getInstance() { return instance; }

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Enabled BlockTowerDefense by Mickydoesmc");

//        Start BTDTickEvent
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getPluginManager().callEvent(new BTDTickEvent(ticksPassed));
                ticksPassed++;
            }
        }.runTaskTimer(this,0,1);


//        Commands
        getCommand("spawnballoon").setExecutor(new SpawnBalloonCommand());

        getCommand("gamemanager").setExecutor(new me.mickydoesmc.btd.blocktowerdefense.Commands.GameManager());
        getCommand("gamemanager").setTabCompleter(new me.mickydoesmc.btd.blocktowerdefense.Commands.GameManager());

        getCommand("list").setExecutor(new ListCommand());

        getCommand("join").setExecutor(new JoinCommand());
        getCommand("join").setTabCompleter(new JoinCommand());

        getCommand("leave").setExecutor(new LeaveCommand());


//        Event Listeners
        Bukkit.getPluginManager().registerEvents(new BalloonSpawn(), this);
        Bukkit.getPluginManager().registerEvents(new BalloonMoving(), this);
        Bukkit.getPluginManager().registerEvents(new TowerPlacement(), this);
        Bukkit.getPluginManager().registerEvents(new GeneralListeners(), this);

        Bukkit.getPluginManager().registerEvents(new GameManager(), this);
//        Bukkit.getPluginManager().registerEvents(new Lobby(), this);
//        Bukkit.getPluginManager().registerEvents(new GameJoiningInterface(), this);
//        Bukkit.getPluginManager().registerEvents(new GameLobbyListeners(), this);
//        Bukkit.getPluginManager().registerEvents(new GameFunctionListeners(), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic


//        Remove all balloons & towers
        for (Balloon balloon : getBalloons()) {
            balloon.getBalloonEntity().remove();
        }
        for (Tower tower : getTowers()) {
            tower.getEntity().remove();
        }


        for (Game game : GameManager.getGames()) {
            GameManager.removeGame(game);
        }
        for (World world : Bukkit.getWorlds()) {
            if (!world.getName().equals("world")) {
                for (Player player : world.getPlayers()) {
                    player.teleport(Bukkit.getWorld("world").getSpawnLocation());
                    WorldManager.unloadWorld(world.getName());
                    WorldManager.deleteWorld(world.getName());
                }
            }
        }
    }


    public boolean debug(String msg) {
        if (debugMode) {
            this.getLogger().info("[DEBUG] " + msg);
        }
        return debugMode;
    }
}
