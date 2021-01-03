package me.mickydoesmc.btd.blocktowerdefense.Commands;

import me.mickydoesmc.btd.blocktowerdefense.Games.Game.Game;
import me.mickydoesmc.btd.blocktowerdefense.Games.Game.Map;
import me.mickydoesmc.btd.blocktowerdefense.Games.Game.Mode;
import me.mickydoesmc.btd.blocktowerdefense.Utils.Chat;
import me.mickydoesmc.btd.blocktowerdefense.Utils.User;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameManager implements CommandExecutor, TabCompleter {

    private static List<String> commands = Arrays.asList("list","new","remove");

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        User user = new User(commandSender);

        if (user.hasPermission("btd.gamemanager")) {
            if (strings.length >= 1) {
                switch (strings[0].toUpperCase()) {
                    case "LIST":
                        Chat.send(commandSender, "&aRegistered Games:");
                        if (strings.length == 1) {
                            for (Game game : me.mickydoesmc.btd.blocktowerdefense.Games.GameManager.getGames()) {
                                Chat.send(commandSender, "&7" + game.getGAMEID());
                            }
                        } else {
                            for (Game game : me.mickydoesmc.btd.blocktowerdefense.Games.GameManager.getGames()) {
                                Chat.send(commandSender, "&b" + game.getGAMEID());
                                for (Player player : game.getPlayers()) {
                                    Chat.send(commandSender, " - &e" + player.getName());
                                }
                            }
                        }
                        return true;
                    case "NEW":
                        if (strings.length == 3 | strings.length == 4) {
                            try {
                                Game game;
                                if (strings.length == 3) {
                                    game = me.mickydoesmc.btd.blocktowerdefense.Games.GameManager.newGame(new Map(strings[1]), 1,3, Mode.valueOf(strings[2].toUpperCase()));
                                } else {
                                    game = me.mickydoesmc.btd.blocktowerdefense.Games.GameManager.newGame(new Map(strings[1]), 1,3, Mode.valueOf(strings[2].toUpperCase()), strings[3]);
                                }
                                Chat.send(commandSender, "&aStarted new game with id &7" + game.getGAMEID());
                                return true;
                            } catch (Exception e) {
                                Chat.send(commandSender, "Failed to boot up new game: " + e.getMessage());
                                return false;
                            }
                        } else {
                            Chat.send(commandSender, "&cUsage: /gamemanager new <map> <mode> [name]");
                            return false;
                        }

                    case "REMOVE":
                        if (strings.length == 2) {
                            Game removedGame = me.mickydoesmc.btd.blocktowerdefense.Games.GameManager.getGameById(strings[1]);
                            if (removedGame != null) {
                                Chat.send(commandSender, "&aSuccessfully removed game " + removedGame.getGAMEID());
                                me.mickydoesmc.btd.blocktowerdefense.Games.GameManager.removeGame(removedGame);
                                return true;
                            } else {
                                Chat.send(commandSender, "&cNo game with an ID of &7" + strings[1] + "&c was found");
                                return false;
                            }
                        } else {
                            Chat.send(commandSender, "&cPlease include a game ID");
                            return false;
                        }
                    default:
                        Chat.send(commandSender, "&cInvalid sub command");
                        return false;
                }
            }
        } else {
            Chat.send(commandSender, "&cYou don't have permission to use that command");
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        ArrayList<String> values = new ArrayList<>();
        if (strings.length == 2) {
            if (strings[0].toUpperCase().equals("REMOVE")) {
                for (Game game : me.mickydoesmc.btd.blocktowerdefense.Games.GameManager.getGames()) {
                    if (game.getGAMEID().toUpperCase().startsWith(strings[1].toUpperCase())) {
                        values.add(game.getGAMEID());
                    }
                }
            }
        } else if (strings.length == 1) {
            for (String cmd : commands) {
                if (cmd.toUpperCase().startsWith(strings[0].toUpperCase())) {
                    values.add(cmd);
                }
            }
        } else if (strings.length == 3) {
            if (strings[0].toUpperCase().equals("NEW")) {
                for (Mode mode : Mode.values()) {
                    if (mode.toString().toUpperCase().startsWith(strings[2].toUpperCase())) {
                        values.add(mode.toString());
                    }
                }
            }
        }
        return values;
    }
}
