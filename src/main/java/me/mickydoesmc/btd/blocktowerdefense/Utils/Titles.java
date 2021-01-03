package me.mickydoesmc.btd.blocktowerdefense.Utils;

import com.destroystokyo.paper.Title;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Titles {

    public static Title createTitle(String title, String subtitle) {
        return new Title(ChatColor.translateAlternateColorCodes('&', title), ChatColor.translateAlternateColorCodes('&', subtitle),5,40,5);
    }

    public static Title createTitle(String title, String subtitle, boolean doFade, boolean doLong) {
        int time = doLong ? 100 : 40;
        int fade = doFade ? 5 : 0;
        return new Title(ChatColor.translateAlternateColorCodes('&', title), ChatColor.translateAlternateColorCodes('&', subtitle), fade, time, fade);
    }

    public static void sendTitle(Player player, String title, String subtitle) {
        player.sendTitle(createTitle(title,subtitle));
    }
    public static void broadcastTitle(String title, String subtitle) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendTitle(createTitle(title, subtitle));
        }
    }


    public static void sendTitle(Player player, String title, String subtitle, boolean doFade, boolean doLong) {
        player.sendTitle(createTitle(title,subtitle,doFade,doLong));
    }

    public static void broadcastTitle(String title, String subtitle, boolean doFade, boolean doLong) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendTitle(createTitle(title, subtitle,doFade,doLong));
        }
    }

}
