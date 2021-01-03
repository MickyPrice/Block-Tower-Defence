package me.mickydoesmc.btd.blocktowerdefense.Games.Game;

public enum Mode {

    BTD("Block Tower Defense");

    private final String name;

    Mode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
