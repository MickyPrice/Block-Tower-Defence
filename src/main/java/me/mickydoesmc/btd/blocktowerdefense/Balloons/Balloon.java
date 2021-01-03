package me.mickydoesmc.btd.blocktowerdefense.Balloons;

import me.mickydoesmc.btd.blocktowerdefense.BlockTowerDefense;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class Balloon {


    private Material material;
    private int maxHealth;
    private int health;
    private BalloonType childBalloon;
    private ArmorStand balloonEntity;
    private Location location;
    private BalloonType type;
    private int movementSpeed;


    public Material getMaterial() {
        return material;
    }

    void setMaterial(Material material) {
        this.material = material;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getHealth() {
        return health;
    }

    void setHealth(int health) {
        this.health = health;
    }

    public BalloonType getChildBalloon() {
        return childBalloon;
    }

    void setChildBalloon(BalloonType childBalloon) {
        this.childBalloon = childBalloon;
    }

    public ArmorStand getBalloonEntity() {
        return balloonEntity;
    }

    void setBalloonEntity(ArmorStand balloonEntity) {
        this.balloonEntity = balloonEntity;
    }

    public void popBalloon() {
        if (getHealth() > 1) {
            BalloonUtils.spawnBalloonBasedOffType(getType(), getLocation(), getHealth() -1);
        } else {
            if (getChildBalloon() != null) {
                BalloonUtils.spawnBalloonBasedOffType(getChildBalloon(), getLocation(), 0);
            }
        }
        BlockTowerDefense.getBalloons().remove(this);
    }


    public void spawn() {
        ArmorStand entity = getLocation().getWorld().spawn(getLocation(), ArmorStand.class);
        entity.setDisabledSlots(EquipmentSlot.HEAD);
        entity.setItem(EquipmentSlot.HEAD, new ItemStack(getMaterial()));
        entity.setSmall(false);
        entity.setGravity(false);
        entity.setArms(false);
        entity.setMarker(false);
        entity.setCanMove(false);
        entity.setCanTick(false);
        entity.setInvisible(true);
        entity.setInvulnerable(true);
        entity.setCustomName(ChatColor.RED + "" + getHealth() + "/" + getMaxHealth() + " HEALTH");
        if(getHealth() > 1) {
            entity.setCustomNameVisible(true);
        }
        BlockTowerDefense.getBalloons().add(this);
        setBalloonEntity(entity);
    }
//    public void spawn(Location location) {
//        ArmorStand entity = getLocation().getWorld().spawn(location, ArmorStand.class);
//        entity.setDisabledSlots(EquipmentSlot.HEAD);
//        entity.setItem(EquipmentSlot.HEAD, new ItemStack(getMaterial()));
//        entity.setSmall(false);
//        entity.setGravity(false);
//        entity.setArms(false);
//        entity.setMarker(false);
//        entity.setCanMove(false);
//        entity.setCanTick(false);
//        entity.setInvisible(true);
//        entity.setInvulnerable(true);
//        entity.setCustomName(ChatColor.RED + "" + getMaxHealth());
//        if(getMaxHealth() > 1) {
//            entity.setCustomNameVisible(true);
//        }
//        BlockTowerDefense.getBalloons().add(this);
//        setBalloonEntity(entity);
//    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
        if(getBalloonEntity() != null) {
            getBalloonEntity().teleport(location);
        }
    }

    public BalloonType getType() {
        return type;
    }

    void setType(BalloonType type) {
        this.type = type;
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
    }
}

