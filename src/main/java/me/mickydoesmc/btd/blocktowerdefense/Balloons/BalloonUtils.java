package me.mickydoesmc.btd.blocktowerdefense.Balloons;

import me.mickydoesmc.btd.blocktowerdefense.Utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;

public class BalloonUtils {


    public static void spawnBalloonBasedOffType(BalloonType type, Location location, int health) {
        Balloon balloon;
        switch (type) {
            case RED:
                balloon = new RedBalloon();
                break;
            case BLUE:
                balloon = new BlueBalloon();
                break;
            case GREEN:
                balloon = new GreenBalloon();
                break;
            case ORANGE:
                balloon = new OrangeBalloon();
                break;
            case CERAMIC:
                balloon = new CeramicBalloon();
                break;
            default:
                balloon = null;
                Chat.broadcast("NONE", "&cBalloon Type not supported yet");
                break;
        }
        if (balloon != null) {
            balloon.setLocation(location);
            if (health > 0) { balloon.setHealth(health); }
            balloon.spawn();
        }

    }

}
