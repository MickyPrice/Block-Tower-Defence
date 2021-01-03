package me.mickydoesmc.btd.blocktowerdefense.Utils;

import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

public class User {

    private final CommandSender player;

    public User(CommandSender player) {
        this.player = player;
    }

    public boolean hasPermission(String permission) {
        return player.hasPermission(permission) | player instanceof ConsoleCommandSender;
    }
    public boolean hasPermission(Permission permission) {
        return player.hasPermission(permission) | player instanceof ConsoleCommandSender;
    }

    public boolean isPlayer() {
        return player instanceof Player;
    }

    public boolean isAlive() {
        if (player instanceof Player) {
            return ((Player) player).getGameMode() != GameMode.SPECTATOR;
        } else {
            return false;
        }
    }

    public Player getPlayer() {
        if (player instanceof Player) {
            return (Player) player;
        } else {
            return null;
        }
    }
}
