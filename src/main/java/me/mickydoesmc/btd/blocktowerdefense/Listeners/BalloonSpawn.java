package me.mickydoesmc.btd.blocktowerdefense.Listeners;

import me.mickydoesmc.btd.blocktowerdefense.BlockTowerDefense;
import me.mickydoesmc.btd.blocktowerdefense.Events.BalloonSpawnEvent;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class BalloonSpawn implements Listener {
    @EventHandler
    public void onBalloonSpawn(BalloonSpawnEvent event) {
        ArmorStand balloon = event.getLocation().getWorld().spawn(event.getLocation().clone().subtract(0,1,0), ArmorStand.class);
        balloon.setSmall(false);
        balloon.setVisible(false);
        balloon.setDisabledSlots(EquipmentSlot.HEAD);
        balloon.setItem(EquipmentSlot.HEAD, new ItemStack(Material.RED_TERRACOTTA));
        BlockTowerDefense.getBalloons().add(balloon);
    }
}
