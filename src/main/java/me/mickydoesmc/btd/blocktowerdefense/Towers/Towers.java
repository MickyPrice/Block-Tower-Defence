package me.mickydoesmc.btd.blocktowerdefense.Towers;

import me.mickydoesmc.btd.blocktowerdefense.BlockTowerDefense;
import me.mickydoesmc.btd.blocktowerdefense.Utils.Chat;
import me.mickydoesmc.btd.blocktowerdefense.Utils.YamlConfig;
import org.bukkit.ChatColor;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;

public class Towers {

    private static ArrayList<Tower> towers = new ArrayList<>();

    public static ArrayList<Tower> getTowers() {
        return towers;
    }

    public static void init() {
        final YamlConfig YAMLCONFIG = new YamlConfig(BlockTowerDefense.getInstance().getDataFolder(), "towers");
        final FileConfiguration CONFIG = YAMLCONFIG.getConfig();

        MemorySection configTowers = (MemorySection) CONFIG.get("towers");

        try {
            if (configTowers != null) {
                for (String tower : configTowers.getKeys(false)) {
                    if (CONFIG.get("towers." + tower) instanceof MemorySection) {
                        MemorySection towerSection = (MemorySection) CONFIG.get("towers." + tower);
                        Tower newTower = new Tower(
                                towerSection.getString("name"),
                                towerSection.getString("entityType"),
                                towerSection.getInt("price"),
                                towerSection.getInt("attackSpeed"),
                                towerSection.getInt("attackRange"),
                                towerSection.getString("attackType"),
                                towerSection.getBoolean("waterOnly"),
                                towerSection.getString("icon"),
                                towerSection.getString("name")
                        );
                        towers.add(newTower);
                    } else {
                        throw(new Exception("InvalidConfigError - TOWER: " + tower + " is not instance of MemorySection"));
                    }
                }
            } else {
                throw(new Exception("InvalidConfigError - towers list not found"));
            }
        } catch (Exception e) {
            Chat.broadcast(ChatColor.RED + "ERROR: " + e.getMessage());
            e.printStackTrace();
        }




    }

}
