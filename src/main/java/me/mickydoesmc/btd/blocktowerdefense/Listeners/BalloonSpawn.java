package me.mickydoesmc.btd.blocktowerdefense.Listeners;

import me.mickydoesmc.btd.blocktowerdefense.Balloons.*;
import me.mickydoesmc.btd.blocktowerdefense.BlockTowerDefense;
import me.mickydoesmc.btd.blocktowerdefense.Events.BalloonSpawnEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class BalloonSpawn implements Listener {
    @EventHandler
    public void onBalloonSpawn(BalloonSpawnEvent event) {
        switch (event.getType()) {
            case RED: {
                Balloon balloon = new RedBalloon();
                balloon.setLocation(event.getLocation());
                balloon.spawn();
                break;
            }
            case BLUE: {
                Balloon balloon = new BlueBalloon();
                balloon.setLocation(event.getLocation());
                balloon.spawn();
                break;
            }
            case GREEN: {
                Balloon balloon = new GreenBalloon();
                balloon.setLocation(event.getLocation());
                balloon.spawn();
                break;
            }
            case ORANGE: {
                Balloon balloon = new OrangeBalloon();
                balloon.setLocation(event.getLocation());
                balloon.spawn();
                break;
            }
            case CERAMIC: {
                Balloon balloon = new CeramicBalloon();
                balloon.setLocation(event.getLocation());
                balloon.spawn();
                break;
            }
            default: {
                Bukkit.broadcastMessage(ChatColor.RED + "BALLOON TYPE " + ChatColor.DARK_RED + event.getType().toString() + ChatColor.RED + " NOT ADDED YET");
                break;
            }
        }
    }

    @EventHandler
    public void placeBalloon(PlayerInteractEvent event) {
        if (event.getItem() != null & event.getClickedBlock() != null) {
            if (event.getItem().getType() == Material.RED_DYE) {
                event.setCancelled(true);
                Bukkit.getPluginManager().callEvent(new BalloonSpawnEvent(event.getClickedBlock().getLocation().clone().add(0.5,1,0.5), BalloonType.RED));
            }
            if (event.getItem().getType() == Material.BLUE_DYE) {
                event.setCancelled(true);
                Bukkit.getPluginManager().callEvent(new BalloonSpawnEvent(event.getClickedBlock().getLocation().clone().add(0.5,1,0.5), BalloonType.BLUE));
            }
            if (event.getItem().getType() == Material.GREEN_DYE) {
                event.setCancelled(true);
                Bukkit.getPluginManager().callEvent(new BalloonSpawnEvent(event.getClickedBlock().getLocation().clone().add(0.5,1,0.5), BalloonType.GREEN));
            }
            if (event.getItem().getType() == Material.ORANGE_DYE) {
                event.setCancelled(true);
                Bukkit.getPluginManager().callEvent(new BalloonSpawnEvent(event.getClickedBlock().getLocation().clone().add(0.5,1,0.5), BalloonType.ORANGE));
            }
            if (event.getItem().getType() == Material.CLAY_BALL) {
                event.setCancelled(true);
                Bukkit.getPluginManager().callEvent(new BalloonSpawnEvent(event.getClickedBlock().getLocation().clone().add(0.5,1,0.5), BalloonType.CERAMIC));
            }
        }
    }
}
