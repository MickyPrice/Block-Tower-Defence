package me.mickydoesmc.btd.blocktowerdefense.Events;

import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BalloonSpawnEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }

    private final Location location;

    public BalloonSpawnEvent(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}
