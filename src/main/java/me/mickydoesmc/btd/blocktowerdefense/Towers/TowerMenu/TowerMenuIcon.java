package me.mickydoesmc.btd.blocktowerdefense.Towers.TowerMenu;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TowerMenuIcon {

    final private String name;
    final private String entityId;
    final private int price;
    final private Material material;

    TowerMenuIcon(String name, String entityId, int price, Material material) {
        this.name = name;
        this.entityId = entityId;
        this.price = price;
        this.material = material;
    }


    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getEntityId() {
        return entityId;
    }

    public Material getMaterial() {
        return material;
    }

    public ItemStack getItemStack() {
        ItemStack item = new ItemStack(getMaterial(), 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + getName());
        item.setItemMeta(meta);
        return item;
    }
}
