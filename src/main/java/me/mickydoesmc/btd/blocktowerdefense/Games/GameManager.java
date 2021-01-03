package me.mickydoesmc.btd.blocktowerdefense.Games;

import me.mickydoesmc.btd.blocktowerdefense.BlockTowerDefense;
import me.mickydoesmc.btd.blocktowerdefense.Gameplay.Lobby.Lobby;
import me.mickydoesmc.btd.blocktowerdefense.Games.Game.Game;
import me.mickydoesmc.btd.blocktowerdefense.Games.Game.Map;
import me.mickydoesmc.btd.blocktowerdefense.Games.Game.Mode;
import me.mickydoesmc.btd.blocktowerdefense.Games.GameEvents.GameCreatedEvent;
import me.mickydoesmc.btd.blocktowerdefense.Games.GameEvents.GameRemovedEvent;
import me.mickydoesmc.btd.blocktowerdefense.Utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class GameManager implements Listener {

    private static ArrayList<Game> games = new ArrayList<>();
    private static HashMap<Player, Game> playerGameMap = new HashMap<>();

    public static ArrayList<Game> getGames() {
        return games;
    }

    public static Game newGame(Map map, int minPlayers, int maxPlayers, Mode gamemode) {
        Game game = new Game(map, minPlayers, maxPlayers, gamemode);
        games.add(game);
        Bukkit.getPluginManager().callEvent(new GameCreatedEvent(game));
        return game;
    }
    public static Game newGame(Map map, int minPlayers, int maxPlayers, Mode gamemode, String name) {
        Game game = new Game(map, minPlayers, maxPlayers, gamemode, name);
        games.add(game);
        Bukkit.getPluginManager().callEvent(new GameCreatedEvent(game));
        return game;
    }

    public static void removeGame(Game game) {
        games.remove(game);
        ArrayList<Player> playersToRemove = new ArrayList<>();
        if (playerGameMap.containsValue(game)) {
            for (Player player : playerGameMap.keySet()) {
                if (playerGameMap.get(player) == game) {
                    player.setGameMode(GameMode.CREATIVE);
                    Lobby.equip(player);
                    updateWhoCanSeeWho(player);
                    playersToRemove.add(player);
                }
            }
        }
        for (Player player : playersToRemove) {
            playerGameMap.get(player).removePlayer(player);
            playerGameMap.remove(player);
        }

        Bukkit.getPluginManager().callEvent(new GameRemovedEvent(game));
        game.clearWorld();
    }

    public static void join(Player player, Game game) {
        if (!playerGameMap.containsKey(player)) {
            if (game.addPlayer(player)) {
                if (!game.getName().equals(game.getGAMEID())) {
                    Chat.send(player, "&aConnecting to &f" + game.getName() + " &7&o(" + game.getGAMEID() + ")&a...");
                } else {
                    Chat.send(player, "&aConnecting to &f" + game.getName() + "&a...");
                }
                playerGameMap.put(player, game);
                updateWhoCanSeeWho(player);
                player.teleport( game.getMap().getLobby() );
            }
        }
    }

    public static Game getGameById(String id) {
        for (Game game : getGames()) {
            if (game.getGAMEID().toUpperCase().equals(id.toUpperCase())) {
                return game;
            }
        }
        return null;
    }

    public static boolean isInGame(Player player) {
        return playerGameMap.containsKey(player);
    }

    public static void hubPlayer(Player player) {
        if (isInGame(player)) {
            playerGameMap.get(player).removePlayer(player);
            playerGameMap.remove(player);
            updateWhoCanSeeWho(player);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        hubPlayer(event.getPlayer());
        updateWhoCanSeeWho(event.getPlayer());
        event.setQuitMessage(null);
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        updateWhoCanSeeWho(event.getPlayer());
    }


    public static Game getPlayerGame(Player player) {
        return playerGameMap.getOrDefault(player, null);
    }

    public static void updateWhoCanSeeWho(Player player) {
        if (GameManager.isInGame(player)) {
            for (Player pl : Bukkit.getOnlinePlayers()) {
                if (GameManager.playerGameMap.get(player).getPlayers().contains(pl)) {
                    pl.showPlayer(BlockTowerDefense.getInstance(), player);
                    player.showPlayer(BlockTowerDefense.getInstance(), pl);
                } else {
                    pl.hidePlayer(BlockTowerDefense.getInstance(), player);
                    player.hidePlayer(BlockTowerDefense.getInstance(), pl);
                }
            }
        } else {
            for (Player pl : Bukkit.getOnlinePlayers()) {
                if (GameManager.isInGame(pl)) {
                    pl.hidePlayer(BlockTowerDefense.getInstance(), player);
                    player.hidePlayer(BlockTowerDefense.getInstance(), pl);
                } else {
                    pl.showPlayer(BlockTowerDefense.getInstance(), player);
                    player.showPlayer(BlockTowerDefense.getInstance(), pl);
                }
            }
        }
    }

}
