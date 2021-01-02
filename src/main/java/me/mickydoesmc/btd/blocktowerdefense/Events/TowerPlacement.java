package me.mickydoesmc.btd.blocktowerdefense.Events;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class TowerPlacement implements Listener {
    @EventHandler
    public void onTowerPlace(BlockPlaceEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.SURVIVAL) {
            switch (event.getBlock().getType()) {
                case DIORITE:
                    event.setCancelled(true);
                    event.getPlayer().getInventory().setItemInMainHand(null);
                    spawnTower(TOWER.SKELETON, event.getBlock().getLocation());
                    break;
                default:
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(ChatColor.RED + "That is not a valid tower");
            }
        }

    }


    private static String spawnTower(TOWER tower, Location location) {
        switch (tower) {
            case SKELETON: {
                Skeleton spawnedTower = location.getWorld().spawn(location, Skeleton.class);
                spawnedTower.setAI(true);
                spawnedTower.setInvulnerable(true);
                spawnedTower.setCanPickupItems(false);
                spawnedTower.setAware(false);
                spawnedTower.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0);
                spawnedTower.setPersistent(true);
                spawnedTower.getEquipment().setHelmet(new ItemStack(Material.SKELETON_SKULL));
                return null;
            }
            default: {
                return "Tower has not been implemented yet";
            }
        }
    }

}






enum TOWER {
    SKELETON
}
