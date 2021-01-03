package me.mickydoesmc.btd.blocktowerdefense.Games.Game;


import me.mickydoesmc.btd.blocktowerdefense.Games.GameEvents.GameStateChangeEvent;
import me.mickydoesmc.btd.blocktowerdefense.Games.GameEvents.PlayerJoinGameEvent;
import me.mickydoesmc.btd.blocktowerdefense.Games.GameEvents.PlayerLeaveGameEvent;
import me.mickydoesmc.btd.blocktowerdefense.Games.Game.Map;
import me.mickydoesmc.btd.blocktowerdefense.Utils.Chat;
import me.mickydoesmc.btd.blocktowerdefense.Utils.WorldManager;
import org.apache.commons.lang.RandomStringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Game {

    private State state = State.WAITING;
    private ArrayList<Player> players = new ArrayList<>();
    private final Map MAP;
    private int minPlayers;
    private int maxPlayers;
    private final Mode GAMEMODE;
    private String name;
    private final String GAMEID;

    public Game(Map map, int minPlayers, int maxPlayers, Mode gameMode) {
        this.MAP = map;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.GAMEMODE = gameMode;
        this.GAMEID = gameMode.toString() + RandomStringUtils.randomNumeric(3);
        this.name = getGAMEID();
    }
    public Game(Map map, int minPlayers, int maxPlayers, Mode gameMode, String name) {
        this.MAP = map;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.GAMEMODE = gameMode;
        this.name = ChatColor.translateAlternateColorCodes('&', name);
        this.GAMEID = RandomStringUtils.randomAlphanumeric(5);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        Bukkit.getPluginManager().callEvent(new GameStateChangeEvent(this, state, this.state));
        this.state = state;
    }

    public Map getMap() {
        return MAP;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public boolean addPlayer(Player player) {
        if ((this.state == State.WAITING | this.state == State.STARTING)) {
            if (this.players.size()+1 <= maxPlayers) {
                this.players.add(player);
                Bukkit.getPluginManager().callEvent(new PlayerJoinGameEvent(this, player));
                return true;
            } else {
                Chat.send(player, "&cFailed to connect to game server: &7This game is full");
                return false;
            }
        } else {
            Chat.send(player, "&cFailed to connect to game server: &7This game has already started");
            return false;
        }
    }

    public void removePlayer(Player player) {
        Chat.send(player, "&aLeaving game &7" + this.GAMEID + "&a...");
        this.players.remove(player);
        Bukkit.getPluginManager().callEvent(new PlayerLeaveGameEvent(this, player));
    }

    public String getGAMEID() {
        return GAMEID;
    }

    public void clearWorld() {
        for (Player player : getMap().getWorld().getPlayers()) {
            player.teleport(Bukkit.getWorld("world").getSpawnLocation());
        }
        WorldManager.unloadWorld(getMap().getWorld().getName());
        WorldManager.deleteWorld(getMap().getWorld().getName());
    }

    public boolean isFull() {
        return this.players.size() >= this.maxPlayers;
    }

    public ArrayList<Player> getSpectators() {
        ArrayList<Player> spectators = new ArrayList<>();
        for (Player player : getPlayers()) {
            if (player.getGameMode() == GameMode.SPECTATOR) {
                spectators.add(player);
            }
        }
        return spectators;
    }
    public ArrayList<Player> getAlivePlayers() {
        ArrayList<Player> players = new ArrayList<>();
        for (Player player : getPlayers()) {
            if (player.getGameMode() != GameMode.SPECTATOR) {
                players.add(player);
            }
        }
        return players;
    }

    public Mode getGAMEMODE() {
        return GAMEMODE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
