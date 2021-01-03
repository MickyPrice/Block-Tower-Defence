package me.mickydoesmc.btd.blocktowerdefense.Events;

import me.mickydoesmc.btd.blocktowerdefense.Balloons.BalloonType;
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
    private final BalloonType type;

    public BalloonSpawnEvent(Location location, BalloonType type) {
        this.location = location;
        this.type = type;
    }

    public Location getLocation() {
        return location;
    }

    public BalloonType getType() {
        return type;
    }
}
