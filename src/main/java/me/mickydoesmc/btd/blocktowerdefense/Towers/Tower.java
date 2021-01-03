package me.mickydoesmc.btd.blocktowerdefense.Towers;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public interface Tower {
    String getName();
    EntityType getEntityType();
    Location getLocation();
    void setLocation(Location location);
    int getPrice();
    int getAttackSpeed();
    int getAttackRange();
    Runnable getAttackEvent();
    void spawnTower();
    Entity getEntity();
}
