package me.mickydoesmc.btd.blocktowerdefense.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class Chat {

    private final static String DEFAULT_PREFIX = "&e&lGAME";
    private final static String DIVIDER = "&8&l>>";
    public static String createString(String prefix, String message) {
        if (prefix.toUpperCase().equals("DEFAULT")) {
            return ChatColor.translateAlternateColorCodes('&', DEFAULT_PREFIX + "&f " + DIVIDER + "&7 " + message);
        } else if (prefix.toUpperCase().equals("NONE")) {
            return ChatColor.translateAlternateColorCodes('&',  "&7 " + message);
        } else {
            return ChatColor.translateAlternateColorCodes('&',  prefix + "&f " + DIVIDER + "&7 " + message);
        }
    }

    public static void send(CommandSender user, String message) {
        user.sendMessage(createString("DEFAULT", message));
    }
    public static void send(CommandSender user, String prefix, String message) {
        user.sendMessage(createString(Objects.requireNonNullElse(prefix, "NONE"), message));
    }

    public static void broadcast(String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(createString("DEFAULT", message));
        }
    }
    public static void broadcast(String prefix, String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(createString(Objects.requireNonNullElse(prefix, "NONE"), message));
        }
    }

}
