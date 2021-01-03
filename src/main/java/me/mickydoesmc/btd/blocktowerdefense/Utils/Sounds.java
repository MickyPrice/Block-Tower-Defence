package me.mickydoesmc.btd.blocktowerdefense.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Sounds {

    private final static Sound VICTORY = Sound.UI_TOAST_CHALLENGE_COMPLETE;
    private final static Sound SUCCESS = Sound.ENTITY_PLAYER_LEVELUP;
    private final static Sound TICK = Sound.UI_BUTTON_CLICK;
    private final static Sound ERROR = Sound.BLOCK_ANVIL_LAND;

    public static void playSound(Player player, Sound sound) {
        player.playSound(player.getLocation(), sound, 20,1);
    }
    public static void playSound(Player player, Sound sound, int volume, int pitch) {
        player.playSound(player.getLocation(), sound, volume,pitch);
    }

    public static void playVictory(Player player) {
        playSound(player, VICTORY);
    }
    public static void playSuccess(Player player) {
        playSound(player, SUCCESS);
    }
    public static void playTick(Player player) {
        playSound(player, TICK);
    }
    public static void playError(Player player) {
        playSound(player, ERROR);
    }

    public static void broadcastSound(Sound sound) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            playSound(player, sound);
        }
    }
    public static void broadcastSound(Sound sound, int volume, int pitch) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            playSound(player, sound, volume, pitch);
        }
    }

    public static void broadcastVictory() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            playSound(player, VICTORY);
        }
    }
    public static void broadcastSuccess() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            playSound(player, SUCCESS);
        }
    }
    public static void broadcastTick() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            playSound(player, TICK);
        }
    }
    public static void broadcastError() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            playSound(player, ERROR);
        }
    }

}
