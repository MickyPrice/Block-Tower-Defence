package me.mickydoesmc.btd.blocktowerdefense.Listeners;

import me.mickydoesmc.btd.blocktowerdefense.Balloons.Balloon;
import me.mickydoesmc.btd.blocktowerdefense.Balloons.BalloonType;
import me.mickydoesmc.btd.blocktowerdefense.Balloons.RedBalloon;
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
            default: {
                Bukkit.broadcastMessage(ChatColor.RED + "BALLOON TYPE " + ChatColor.DARK_RED + event.getType().toString() + ChatColor.RED + " NOT ADDED YET");
                break;
            }
        }
    }

    @EventHandler
    public void placeBalloon(PlayerInteractEvent event) {
        if (event.getItem() != null & event.getClickedBlock() != null) {
            if (event.getItem().getType() == Material.MAGMA_CREAM) {
                event.setCancelled(true);
                Bukkit.getPluginManager().callEvent(new BalloonSpawnEvent(event.getClickedBlock().getLocation().clone().add(0.5,1,0.5), BalloonType.RED));
            }
        }
    }
}
