package me.mickydoesmc.btd.blocktowerdefense.Events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BTDTickEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final int ticksPassed;

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }
    public BTDTickEvent(int ticksPassed) {
        this.ticksPassed = ticksPassed;
    }

    public int getTicksPassed() {
        return ticksPassed;
    }
}
