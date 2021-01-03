package me.mickydoesmc.btd.blocktowerdefense.Gameplay.Lobby;

import me.mickydoesmc.btd.blocktowerdefense.Games.GameManager;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import java.util.Arrays;

public class Lobby implements Listener {

    private static final World world = Bukkit.getWorld("world");

    public static void LobbyEquip(Player player) {
        GameManager.hubPlayer(player);
        equip(player);
    }


    public static void equip(Player player) {
        player.teleport(world.getSpawnLocation());
        for (PotionEffect potion : player.getActivePotionEffects()) {
            player.removePotionEffect(potion.getType());
        }
        player.updateInventory();
        player.setFlying(false);
        player.setGameMode(GameMode.ADVENTURE);
        player.setExhaustion(0);
        player.setFoodLevel(20);
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
        player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        player.getInventory().clear();
        player.setTotalExperience(0);
        player.setExp(0);
        player.setLevel(0);
        player.setSaturation(0);
        player.setGlowing(false);
        player.setGravity(true);
        player.setGliding(false);

        final ItemStack ITEM_JOIN = new ItemStack(Material.NETHER_STAR, 1);
        final ItemMeta ITEM_JOIN_META = ITEM_JOIN.getItemMeta();
        ITEM_JOIN_META.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&a&lGames &7(Right Click)"));
        ITEM_JOIN_META.setLore(Arrays.asList(
                ChatColor.translateAlternateColorCodes('&',""),
                ChatColor.translateAlternateColorCodes('&',"&7Join/Spectate Games")
        ));
        ITEM_JOIN.setItemMeta(ITEM_JOIN_META);

        player.getInventory().addItem(ITEM_JOIN);

        final ItemStack ITEM_CLASSES = new ItemStack(Material.BOOK, 1);
        final ItemMeta ITEM_CLASSES_META = ITEM_CLASSES.getItemMeta();
        ITEM_CLASSES_META.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&e&lClasses &7(Right Click)"));
        ITEM_CLASSES_META.setLore(Arrays.asList(
                ChatColor.translateAlternateColorCodes('&',""),
                ChatColor.translateAlternateColorCodes('&',"&7View classes you can play as")
        ));
        ITEM_CLASSES.setItemMeta(ITEM_CLASSES_META);

        player.getInventory().addItem(ITEM_CLASSES);

        final ItemStack ITEM_SHOP = new ItemStack(Material.GOLD_INGOT, 1);
        final ItemMeta ITEM_SHOP_META = ITEM_SHOP.getItemMeta();
        ITEM_SHOP_META.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&6&lShop &7(Right Click)"));
        ITEM_SHOP_META.setLore(Arrays.asList(
                ChatColor.translateAlternateColorCodes('&',""),
                ChatColor.translateAlternateColorCodes('&',"&7Buy new kits, perks and effects")
        ));
        ITEM_SHOP.setItemMeta(ITEM_SHOP_META);

        player.getInventory().addItem(ITEM_SHOP);
    }


    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if (!GameManager.isInGame(event.getPlayer())) { event.setCancelled(true); }
    }
    @EventHandler
    public void onPickup(EntityPickupItemEvent event) {
        if (event.getEntity() instanceof Player) {
            if (!GameManager.isInGame((Player) event.getEntity())) {
                event.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (!GameManager.isInGame(event.getPlayer())) { event.setCancelled(true); }
    }
    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (!GameManager.isInGame(event.getPlayer())) { event.setCancelled(true); }
    }
    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            if (!GameManager.isInGame((Player) event.getEntity())) {
                event.setCancelled(true);
                if (event.getCause() == EntityDamageEvent.DamageCause.VOID) {
                    event.getEntity().teleport(world.getSpawnLocation());
                }
            }
        }
    }
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (!GameManager.isInGame(event.getPlayer())) { event.setCancelled(true); }
    }
    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof Player) {
            if (!GameManager.isInGame((Player) event.getEntity())) {
                event.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onInvClick(InventoryClickEvent event) {
        if (!GameManager.isInGame((Player) event.getWhoClicked())) { event.setCancelled(true); }
    }
    @EventHandler
    public void onOffHandSwitch(PlayerSwapHandItemsEvent event) {
        if (!GameManager.isInGame(event.getPlayer())) { event.setCancelled(true); }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        LobbyEquip(event.getPlayer());
    }


    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (!GameManager.isInGame(event.getPlayer()) & (event.getAction() == Action.RIGHT_CLICK_BLOCK | event.getAction() == Action.RIGHT_CLICK_AIR)) {
            if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.NETHER_STAR) {
                GameJoiningInterface.open(event.getPlayer());
            }
        }
    }

}
