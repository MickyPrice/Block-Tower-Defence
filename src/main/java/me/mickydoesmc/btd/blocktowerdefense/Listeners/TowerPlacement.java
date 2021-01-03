package me.mickydoesmc.btd.blocktowerdefense.Listeners;

import me.mickydoesmc.btd.blocktowerdefense.BlockTowerDefense;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.util.Vector;

public class TowerPlacement implements Listener {
    @EventHandler
    public void onTowerPlace(BlockPlaceEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.SURVIVAL) {
            Location location = event.getBlock().getLocation().add(0.5,0,0.5);
            switch (event.getBlock().getType()) {
                case DIORITE:
                    event.setCancelled(true);
                    event.getPlayer().getInventory().setItemInMainHand(null);
                    spawnTower(TOWERTYPE.Skeleton, location);
                    break;
                default:
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(ChatColor.RED + "That is not a valid tower");
            }
        }

    }


    private static String spawnTower(TOWERTYPE tower, Location location) {
        switch (tower) {
            case Skeleton: {
                me.mickydoesmc.btd.blocktowerdefense.Towers.Skeleton spawnedTower = new me.mickydoesmc.btd.blocktowerdefense.Towers.Skeleton();

                spawnedTower.setLocation(location);
                spawnedTower.spawnTower();

                BlockTowerDefense.getTowers().add(spawnedTower);
                return null;
            }
            default: {
                return "Tower has not been implemented yet";
            }
        }
    }

//    @EventHandler
//    public void BTDTick(BTDTickEvent event) {
//        for (Tower tower : BlockTowerDefense.getTowers()) {
//            for (Entity entity : tower.getEntity().getNearbyEntities(3,3,3)) {
//                if (BlockTowerDefense.getBalloons().contains(entity)) {
//                    makeEntityLookInDirection(tower.getEntity(), entity.getLocation());
//                    int rnd = new Random().nextInt(10);
//                    if (rnd <= 2) {
//                        Snowball projectile = (Snowball) tower.getLocation().getWorld().spawnEntity(tower.getLocation().clone().add(0,1,0), EntityType.SNOWBALL);
//                        projectile.setItem(tower.getProjectile());
//                        projectile.setVelocity(tower.getEntity().getLocation().getDirection().multiply(0.5));
//                    }
//                    break;
//                }
//            }
//        }
//    }


    private static void makeEntityLookInDirection(Entity entity, Location location) {
        Vector dirBetweenLocations = location.toVector().subtract(entity.getLocation().toVector());
        Location loc = entity.getLocation();
        loc.setDirection(dirBetweenLocations);
        entity.teleport(loc);
    }

}



enum TOWERTYPE {
    Skeleton
}
