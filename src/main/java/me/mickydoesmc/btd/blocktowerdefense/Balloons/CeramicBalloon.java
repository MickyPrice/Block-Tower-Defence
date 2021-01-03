package me.mickydoesmc.btd.blocktowerdefense.Balloons;

import org.bukkit.Material;

public class CeramicBalloon extends Balloon {
    public CeramicBalloon() {
        setChildBalloon(BalloonType.ORANGE);
        setHealth(10);
        setMaterial(Material.TERRACOTTA);
        setMaxHealth(10);
        setType(BalloonType.CERAMIC);
        setMovementSpeed(5);
    }
}
