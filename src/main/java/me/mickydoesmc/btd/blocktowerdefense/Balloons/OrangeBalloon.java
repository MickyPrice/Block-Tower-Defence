package me.mickydoesmc.btd.blocktowerdefense.Balloons;

import org.bukkit.Material;

public class OrangeBalloon extends Balloon {
    public OrangeBalloon() {
        setChildBalloon(BalloonType.GREEN);
        setHealth(1);
        setMaterial(Material.ORANGE_CONCRETE);
        setMaxHealth(1);
        setType(BalloonType.ORANGE);
        setMovementSpeed(5);
    }
}
