package me.mickydoesmc.btd.blocktowerdefense.Listeners;

import me.mickydoesmc.btd.blocktowerdefense.BlockTowerDefense;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GeneralListeners implements Listener {

//    GAMEMODE CHANGE EVENT
    @EventHandler
    public void onGameModeChange(PlayerGameModeChangeEvent event) {
        if (event.getNewGameMode() == GameMode.SURVIVAL) {
            event.getPlayer().setAllowFlight(true);
            event.getPlayer().setFlying(true);
            event.getPlayer().getInventory().clear();
            event.getPlayer().getInventory().setItem(0, makeItem(Material.EMERALD, 1, "&aShop &7(Right Click)"));
        }
    }


    private static ItemStack makeItem(Material material, int amount, String name) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        item.setItemMeta(meta);
        return item;
    }




    @EventHandler
    public void onBalloonHit(ProjectileHitEvent event) {
        if (event.getHitEntity() != null) {
            if (event.getHitEntity() instanceof ArmorStand) {
                if (BlockTowerDefense.getBalloons().contains(event.getHitEntity())) {
                    BlockTowerDefense.getBalloons().remove(event.getHitEntity());
                    event.getHitEntity().remove();
                    event.getHitEntity().getLocation().createExplosion(null, 0);
                }
            }
        }
    }
}
