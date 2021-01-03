package me.mickydoesmc.btd.blocktowerdefense.Balloons;

import org.bukkit.Material;

public class GreenBalloon extends Balloon {
    public GreenBalloon() {
        setChildBalloon(BalloonType.BLUE);
        setHealth(1);
        setMaterial(Material.GREEN_CONCRETE);
        setMaxHealth(1);
        setType(BalloonType.GREEN);
        setMovementSpeed(5);
    }
}
