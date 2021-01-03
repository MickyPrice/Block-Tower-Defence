package me.mickydoesmc.btd.blocktowerdefense.Utils;

import org.bukkit.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WorldManager {
    public World createEmptyWorld(String name, World.Environment environment) {
        if (Bukkit.getWorld(name) == null) {
            loadWorld(name, environment);
            return Bukkit.getWorld(name);
        }
        return null;
    }

    public static boolean loadWorld(String worldName, World.Environment environment){
        WorldCreator worldCreator = new WorldCreator(worldName);
        worldCreator.environment(environment);
        worldCreator.generateStructures(false);
        worldCreator.type(WorldType.FLAT);
        World world = worldCreator.createWorld();
        world.setDifficulty(Difficulty.HARD);
        world.setSpawnFlags(true, true);
        world.setPVP(true);
        world.setStorm(false);
        world.setThundering(false);
        world.setWeatherDuration(Integer.MAX_VALUE);
        world.setKeepSpawnInMemory(false);
        world.setTicksPerAnimalSpawns(1);
        world.setTicksPerMonsterSpawns(1);
        world.setAutoSave(false);

        world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        world.setGameRule(GameRule.DISABLE_RAIDS, true);
        world.setGameRule(GameRule.SPECTATORS_GENERATE_CHUNKS, false);
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        world.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
        world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
        world.setGameRule(GameRule.DO_INSOMNIA, false);
        world.setGameRule(GameRule.DO_TRADER_SPAWNING, false);
        world.setGameRule(GameRule.LOG_ADMIN_COMMANDS, false);

        boolean loaded = false;
        for(World w: Bukkit.getServer().getWorlds()) {
            if(w.getName().equals(world.getName())) {
                loaded = true;
                break;
            }
        }
        return loaded;
    }

    public static void unloadWorld(String w) {
        World world = Bukkit.getServer().getWorld(w);
        if(world != null) {
            Bukkit.getServer().unloadWorld(world, false);
        }
    }

    public static void copyWorld(File source, File target){
        try {
            ArrayList<String> ignore = new ArrayList<>(Arrays.asList("uid.dat", "session.dat", "session.lock"));
            if(!ignore.contains(source.getName())) {
                if(source.isDirectory()) {
                    if(!target.exists())
                        if (target.mkdirs()) {
                            String[] files = source.list();
                            if (files != null) {
                                for (String file : files) {
                                    File srcFile = new File(source, file);
                                    File destFile = new File(target, file);
                                    copyWorld(srcFile, destFile);
                                }
                            }
                        }
                } else {
                    InputStream in = new FileInputStream(source);
                    OutputStream out = new FileOutputStream(target);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = in.read(buffer)) > 0)
                        out.write(buffer, 0, length);
                    in.close();
                    out.close();
                }
            }
        } catch (IOException e) {
            Bukkit.getLogger().warning("Failed to copy world as required!");
            e.printStackTrace();
        }
    }

    public static void deleteWorld(String name) {
        unloadWorld(name);
        File target = new File (Bukkit.getServer().getWorldContainer().getAbsolutePath(), name);
        deleteWorld(target);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void deleteWorld(File path) {
        if(path.exists()) {
            File[] files = path.listFiles();
            if (files != null) {
                for (File file: files) {
                    if(file.isDirectory()) {
                        deleteWorld(file);
                    } else {
                        file.delete();
                    }
                }
            }
        }
        path.delete();
    }
}
