package me.mickydoesmc.btd.blocktowerdefense.Games.GameEvents;

import me.mickydoesmc.btd.blocktowerdefense.Games.Game.Game;
import me.mickydoesmc.btd.blocktowerdefense.Games.Game.State;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GameStateChangeEvent extends Event {

    private final Game game;
    private final State state;
    private final State oldState;

    private static final HandlerList handlers = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public GameStateChangeEvent(Game game, State state, State oldState) {
        this.game = game;
        this.state = state;
        this.oldState = oldState;
    }

    public State getState() {
        return state;
    }

    public Game getGame() {
        return game;
    }

    public State getOldState() {
        return oldState;
    }
}
