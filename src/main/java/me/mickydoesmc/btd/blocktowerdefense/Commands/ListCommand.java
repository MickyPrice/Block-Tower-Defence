package me.mickydoesmc.btd.blocktowerdefense.Commands;

import me.mickydoesmc.btd.blocktowerdefense.Games.Game.Game;
import me.mickydoesmc.btd.blocktowerdefense.Utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ListCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            if (me.mickydoesmc.btd.blocktowerdefense.Games.GameManager.getPlayerGame((Player) commandSender) != null) {
                Game game = me.mickydoesmc.btd.blocktowerdefense.Games.GameManager.getPlayerGame((Player) commandSender);
                Chat.send(commandSender, "&aOnline Players: &f(" + game.getPlayers().size() + ")");
                Chat.send(commandSender, "&f" + game.getPlayers());
            } else {
                ArrayList<Player> playerList = new ArrayList<>();
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (me.mickydoesmc.btd.blocktowerdefense.Games.GameManager.getPlayerGame(player) == null) {
                        playerList.add(player);
                    }
                }
                Chat.send(commandSender, "&aOnline Players: &f(" + playerList.size() + ")");
                Chat.send(commandSender, "&f" + playerList);
            }
        } else {
            Chat.send(commandSender, "&cOnly players can execute this command");
        }
        return false;
    }
}
