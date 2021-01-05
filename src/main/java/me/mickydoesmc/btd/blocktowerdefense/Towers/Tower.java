package me.mickydoesmc.btd.blocktowerdefense.Towers;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

public class Tower {

    final private String name;
    final private EntityType entityType;
    final private int price;
    final private int attackSpeed;
    final private int attackRange;
    final private String attackType;
    final private boolean waterOnly;
    final private Material icon;
    final private String towerId;

    public String getName() {
        return name;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public int getPrice() {
        return price;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public String getAttackType() {
        return attackType;
    }

    public boolean isWaterOnly() {
        return waterOnly;
    }

    public Material getIcon() {
        return icon;
    }

    public Tower(String name, String entityType, int price, int attackSpeed, int attackRange, String attackType, boolean waterOnly, String icon, String towerId) {
        this.name = name;
        this.price = price;
        this.attackSpeed = attackSpeed;
        this.attackRange = attackRange;
        this.attackType = attackType;
        this.waterOnly = waterOnly;
        this.towerId = towerId;


        if (Material.getMaterial(icon) != null) {
            this.icon = Material.getMaterial(icon);
        } else {
            this.icon = Material.STONE;
        }

        this.entityType = EntityType.valueOf(entityType);
    }

    public String getTowerId() {
        return towerId;
    }
}
