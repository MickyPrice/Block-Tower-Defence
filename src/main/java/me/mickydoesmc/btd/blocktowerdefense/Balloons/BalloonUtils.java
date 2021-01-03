package me.mickydoesmc.btd.blocktowerdefense.Balloons;

import me.mickydoesmc.btd.blocktowerdefense.Utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;

public class BalloonUtils {


    public static void spawnBalloonBasedOffType(BalloonType type, Location location) {
        switch (type) {
            case RED: {
                Balloon balloon = new RedBalloon();
                balloon.setLocation(location);
                balloon.spawn();
                break;
            }
            default: {
                Chat.broadcast("NONE", "&cBalloon Type not supported yet");
            }
        }
    }

}
