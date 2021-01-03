package me.mickydoesmc.btd.blocktowerdefense.Balloons;

import org.bukkit.Material;

public class BlueBalloon extends Balloon {
    public BlueBalloon() {
//        setChildBalloon();
        setHealth(1);
        setMaterial(Material.BLUE_CONCRETE);
        setMaxHealth(1);
        setType(BalloonType.BLUE);
    }
}
