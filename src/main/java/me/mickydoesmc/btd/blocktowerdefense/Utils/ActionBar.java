package me.mickydoesmc.btd.blocktowerdefense.Utils;

import me.mickydoesmc.btd.blocktowerdefense.BlockTowerDefense;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ActionBar {

    private static boolean actionbarLocked = false;
    private static String currentActionBar = "";


    public static void setActionbarLocked(boolean locked) {
        ActionBar.actionbarLocked = locked;
    }

    public static void setCurrentActionBar(String currentActionBar) {
        ActionBar.currentActionBar = currentActionBar;
    }

    static {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (actionbarLocked) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
//                        %d = display name
//                        %n = actual name
//                        %h = health
//                        %x = x coord
//                        %y = y coord
//                        %z = z coord
//                        %b = world border size
                        String string = currentActionBar
                                .replace("%d",player.getDisplayName())
                                .replace("%n",player.getName())
                                .replace("%h",player.getHealth() + "")
                                .replace("%x",player.getLocation().getBlockX() + "")
                                .replace("%y",player.getLocation().getBlockY() + "")
                                .replace("%z",player.getLocation().getBlockZ() + "")
                                .replace("%b",Math.round(player.getWorld().getWorldBorder().getSize()) + "");

                        player.sendActionBar(ChatColor.translateAlternateColorCodes('&', string));
                    }
                }
            }
        }.runTaskTimer(BlockTowerDefense.getInstance(), 0,10);
    }


    public static void broadcast(String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendActionBar(ChatColor.translateAlternateColorCodes('&', message));
        }
    }

}
