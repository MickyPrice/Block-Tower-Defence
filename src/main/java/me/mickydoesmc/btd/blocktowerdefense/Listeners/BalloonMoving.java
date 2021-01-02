package me.mickydoesmc.btd.blocktowerdefense.Listeners;

import me.mickydoesmc.btd.blocktowerdefense.BlockTowerDefense;
import me.mickydoesmc.btd.blocktowerdefense.Events.BTDTickEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.type.Piston;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.util.Vector;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

public class BalloonMoving implements Listener {

    @EventHandler
    public void BTDTick(BTDTickEvent event) {
        List<ArmorStand> toRemove = new ArrayList();

        for (ArmorStand balloon : BlockTowerDefense.getBalloons()) {
            Block directionBlockForBalloon = balloon.getLocation().getWorld().getBlockAt(balloon.getLocation().clone().subtract(0,1,0));
            if( directionBlockForBalloon.getType() == Material.PISTON ) {
                Vector direction = ((Directional) directionBlockForBalloon.getBlockData()).getFacing().getDirection();
                moveBalloon(balloon, direction);
            } else if (directionBlockForBalloon.getType() == Material.GOLD_BLOCK) {
                Bukkit.broadcastMessage(ChatColor.RED + "Balloon has gotten through");
                toRemove.add(balloon);
                balloon.remove();
            } else {
                toRemove.add(balloon);
                balloon.remove();
            }
        }

        BlockTowerDefense.getBalloons().removeAll(toRemove);

    }

    @EventHandler
    public void onBalloonDeath(EntityDeathEvent event) {
        BlockTowerDefense.getBalloons().remove(event.getEntity());
    }

    private static void moveBalloon(ArmorStand balloon, Vector direction) {
        Location currentLocation = balloon.getLocation();
        if (direction.getBlockZ() == -1) {
//            NORTH
            balloon.teleport(currentLocation.subtract(0,0,1));
            currentLocation.setYaw(180);
        } else if (direction.getBlockZ() == 1) {
//            SOUTH
            balloon.teleport(currentLocation.add(0,0,1));
            currentLocation.setYaw(0);
        } else if (direction.getBlockX() == 1) {
//            EAST
            balloon.teleport(currentLocation.add(1,0,0));
            currentLocation.setYaw(-90);
        } else if (direction.getBlockX() == -1) {
//            WEST
            balloon.teleport(currentLocation.subtract(1,0,0));
            currentLocation.setYaw(90);
        } else {
//            ERROR
        }

    }

}

