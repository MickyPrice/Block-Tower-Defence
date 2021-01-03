package me.mickydoesmc.btd.blocktowerdefense.Games.Game;

public enum State {

    WAITING("Waiting"),
    STARTING("Starting"),
    INGAME("In Game"),
    OVER("Over");


    private final String friendlyName;

    State(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

}
