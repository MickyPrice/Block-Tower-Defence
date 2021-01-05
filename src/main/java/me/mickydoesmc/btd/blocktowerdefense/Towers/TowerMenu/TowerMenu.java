package me.mickydoesmc.btd.blocktowerdefense.Towers.TowerMenu;

import me.mickydoesmc.btd.blocktowerdefense.Towers.Tower;
import me.mickydoesmc.btd.blocktowerdefense.Towers.Towers;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

import java.util.ArrayList;

public class TowerMenu {

    public static void showTowerMenu(Player player) {
        Inventory inventory = Bukkit.createInventory(null,27, "Towers");
        for (Tower tower : Towers.getTowers()) {
            TowerMenuIcon icon = new TowerMenuIcon(
                    tower.getName(),
                    tower.getName(),
                    tower.getPrice(),
                    tower.getIcon()
            );


            inventory.addItem(icon.getItemStack());
        }

        player.openInventory(inventory);

    }


}
