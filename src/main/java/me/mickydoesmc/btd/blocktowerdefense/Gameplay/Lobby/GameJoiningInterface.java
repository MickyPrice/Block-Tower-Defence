package me.mickydoesmc.btd.blocktowerdefense.Gameplay.Lobby;

import me.mickydoesmc.btd.blocktowerdefense.BlockTowerDefense;
import me.mickydoesmc.btd.blocktowerdefense.Games.Game.Game;
import me.mickydoesmc.btd.blocktowerdefense.Games.Game.State;
import me.mickydoesmc.btd.blocktowerdefense.Games.GameEvents.*;
import me.mickydoesmc.btd.blocktowerdefense.Games.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.HashMap;

public class GameJoiningInterface implements Listener {

    private final static String INVENTORY_NAME = "JOIN GAME";
    private static HashMap<Integer, Game> GameSlots = new HashMap<>();

    private static final Inventory INVENTORY = Bukkit.createInventory(null, 36, INVENTORY_NAME);
    private static final HashMap<State, Material> STATE_MATERIAL_HASH_MAP = new HashMap<State, Material>() {{
        put(State.WAITING, Material.LIME_CONCRETE);
        put(State.STARTING, Material.GREEN_CONCRETE);
        put(State.INGAME, Material.ORANGE_CONCRETE);
        put(State.OVER, Material.RED_CONCRETE);
    }};
    private static final HashMap<State, ChatColor> STATE_COLOR_HASH_MAP = new HashMap<State, ChatColor>() {{
        put(State.WAITING, ChatColor.GREEN);
        put(State.STARTING, ChatColor.DARK_GREEN);
        put(State.INGAME, ChatColor.GOLD);
        put(State.OVER, ChatColor.DARK_RED);
    }};

    public static void open(Player player) {
        player.openInventory(INVENTORY);
    }

    private static void updateGameMenu() {
        new BukkitRunnable() {
            @Override
            public void run() {
                INVENTORY.clear();
                GameSlots.clear();
                int i = 0;
                for (Game game : GameManager.getGames()) {
                    GameSlots.put(i,game);
                    final ItemStack ITEM;
                    if (!game.isFull()) {
                        ITEM = new ItemStack(STATE_MATERIAL_HASH_MAP.getOrDefault(game.getState(), Material.WHITE_CONCRETE));
                    } else {
                        ITEM = new ItemStack(Material.BLUE_CONCRETE);
                    }
                    ITEM.setAmount((game.getPlayers().size() == 0) ? 1 : game.getPlayers().size());
                    final ItemMeta ITEM_META = ITEM.getItemMeta();
                    ChatColor color = STATE_COLOR_HASH_MAP.getOrDefault(game.getState(), ChatColor.WHITE);
                    ITEM_META.setDisplayName(color + "" + ChatColor.BOLD + game.getName());
                    ITEM_META.setLore(Arrays.asList(
                            "",
                            ChatColor.WHITE + "Players: " + ChatColor.BLUE + game.getPlayers().size() + ChatColor.GRAY + "/" + ChatColor.BLUE + game.getMaxPlayers(),
                            ChatColor.WHITE + "GameMode: " + ChatColor.GOLD + game.getGAMEMODE().getName(),
                            ChatColor.WHITE + "Map: " + ChatColor.LIGHT_PURPLE + game.getMap().getName(),
                            ChatColor.WHITE + "State: " + color + game.getState().getFriendlyName(),
                            "",
                            ChatColor.DARK_GRAY + "ID: " + game.getGAMEID()
                    ));
                    ITEM.setItemMeta(ITEM_META);

                    INVENTORY.setItem(i, ITEM);
                    i++;
                }
            }
        }.runTaskLater(BlockTowerDefense.getInstance(), 1);

    }

    @EventHandler
    public void onGameCreated(GameCreatedEvent event) {
        updateGameMenu();
    }

    @EventHandler
    public void onPlayerJoinGame(PlayerJoinGameEvent event) {
        updateGameMenu();
    }
    @EventHandler
    public void onPlayerQuitGame(PlayerLeaveGameEvent event) {
        updateGameMenu();
    }
    @EventHandler
    public void onGameRemoved(GameRemovedEvent event) {
        updateGameMenu();
    }
    @EventHandler
    public void onGameStateChange(GameStateChangeEvent event) {
        updateGameMenu();
    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == INVENTORY & !GameManager.isInGame((Player) event.getWhoClicked())) {
            event.setCancelled(true);
            if (GameSlots.containsKey(event.getSlot())) {
                GameManager.join((Player) event.getWhoClicked(), GameSlots.get(event.getSlot()));
            }
        }
    }


}
