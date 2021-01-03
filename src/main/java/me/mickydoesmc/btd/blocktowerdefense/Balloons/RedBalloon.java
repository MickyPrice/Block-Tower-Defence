package me.mickydoesmc.btd.blocktowerdefense.Balloons;

import org.bukkit.Material;

public class RedBalloon extends Balloon {
    public RedBalloon() {
        setChildBalloon(null);
        setHealth(1);
        setMaterial(Material.RED_CONCRETE);
        setMaxHealth(1);
        setType(BalloonType.RED);
    }
}
