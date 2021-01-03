package me.mickydoesmc.btd.blocktowerdefense.Towers;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

public class Spawnable {
    public Entity spawn(EntityType entityType, Location location) {
        Entity entity = location.getWorld().spawnEntity(location, entityType);
        entity.setGravity(false);
        entity.setInvulnerable(true);
        if (entity instanceof LivingEntity) {
            ((LivingEntity) entity).setAI(false);
            ((LivingEntity) entity).setRemoveWhenFarAway(false);
        }

        return entity;

    }
}
