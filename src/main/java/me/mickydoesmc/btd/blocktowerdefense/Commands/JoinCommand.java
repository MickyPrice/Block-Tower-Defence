package me.mickydoesmc.btd.blocktowerdefense.Commands;

import me.mickydoesmc.btd.blocktowerdefense.Games.Game.Game;
import me.mickydoesmc.btd.blocktowerdefense.Utils.Chat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class JoinCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 1) {
            if (commandSender instanceof Player) {
                Game game = me.mickydoesmc.btd.blocktowerdefense.Games.GameManager.getGameById(strings[0]);
                if (game != null) {
                    me.mickydoesmc.btd.blocktowerdefense.Games.GameManager.join((Player) commandSender, game);
                } else {
                    Chat.send(commandSender, "&cNo game server with an ID of &7" + strings[0] + "&c was found");
                }
            } else {
                Chat.send(commandSender, "&cOnly players can execute this command.");
            }
        } else {
            Chat.send(commandSender, "&cUsage: /join <SERVER>");
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        ArrayList<String> values = new ArrayList<>();
        if (strings.length == 1) {
            for (Game game : me.mickydoesmc.btd.blocktowerdefense.Games.GameManager.getGames()) {
                if (game.getGAMEID().toString().toUpperCase().startsWith(strings[0].toUpperCase())) {
                    values.add(game.getGAMEID().toString());
                }
            }
        }
        return values;
    }
}
