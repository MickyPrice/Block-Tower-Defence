package me.mickydoesmc.btd.blocktowerdefense.Towers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class Skeleton extends Spawnable implements Tower {

    @Override
    public String getName() {
        return "Skeleton";
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.SKELETON;
    }

    private Location location;

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public int getPrice() {
        return 200;
    }

    @Override
    public int getAttackSpeed() {
        return 10;
    }

    @Override
    public int getAttackRange() {
        return 3;
    }

    @Override
    public Runnable getAttackEvent() {
        return new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage("SHOOT");
            }
        };
    }

    private Entity entity;

    @Override
    public void spawnTower() {
        entity = spawn(getEntityType(), getLocation());
    }

    public Entity getEntity() {
        return entity;
    }
}
