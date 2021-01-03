package me.mickydoesmc.btd.blocktowerdefense.Games.GameEvents;

import me.mickydoesmc.btd.blocktowerdefense.Games.Game.Game;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GameRemovedEvent extends Event {

    private final Game game;

    private static final HandlerList handlers = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public GameRemovedEvent(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
