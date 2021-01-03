package me.mickydoesmc.btd.blocktowerdefense.Games.Game;


import me.mickydoesmc.btd.blocktowerdefense.BlockTowerDefense;
import me.mickydoesmc.btd.blocktowerdefense.Utils.WorldManager;
import me.mickydoesmc.btd.blocktowerdefense.Utils.YamlConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.UUID;

public class Map {

    private final String name;
    private final String id;
    private final FileConfiguration config;
    private final File file;
    private final String author;
    private World world;
    private final UUID instanceId;
    private final Location lobby;
    private final Location center;
    private final int size;

    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }
    public FileConfiguration getConfig() {
        return config;
    }
    public File getFile() {
        return file;
    }
    public String getAuthor() {
        return author;
    }
    public UUID getInstanceId() {return instanceId;}
    private String worldName;
    public World getWorld() {return world;}

    public Map(String id) throws Exception {
        this.id = id;
        instanceId = UUID.randomUUID();
        file = new File(BlockTowerDefense.getInstance().getDataFolder().getPath() + "/maps/" + id + "/CONFIG.yml");
        if (file.exists()) {
            YamlConfig yamlConfig = new YamlConfig(BlockTowerDefense.getInstance().getDataFolder(), "/maps/" + id + "/CONFIG");
            config = yamlConfig.getConfig();
            name = (String) config.get("name");
            author = (String) config.get("author");
            size = config.getInt("size");
            worldName = "GW-" + getInstanceId().toString();
            loadWorld();
            lobby = new Location(
                    getWorld(),
                    config.getInt("lobby.x"),
                    config.getInt("lobby.y"),
                    config.getInt("lobby.z")
            );
            center = new Location(
                    getWorld(),
                    config.getInt("center.x"),
                    config.getInt("center.y"),
                    config.getInt("center.z")
            );
        } else {
            throw new Exception("Map not found");
        }

    }

    private void loadWorld() {
        WorldManager.copyWorld(new File(BlockTowerDefense.getInstance().getDataFolder().getPath() + "/maps/" + id), new File(worldName));
        WorldManager.loadWorld(worldName, World.Environment.NORMAL);
        world = Bukkit.getWorld(worldName);
    }


    public int getSize() {
        return size;
    }

    public Location getCenter() {
        return center;
    }

    public Location getLobby() {
        return lobby;
    }
}
