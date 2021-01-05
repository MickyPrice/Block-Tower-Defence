package me.mickydoesmc.btd.blocktowerdefense.Commands;

import me.mickydoesmc.btd.blocktowerdefense.Gameplay.Lobby.Lobby;
import me.mickydoesmc.btd.blocktowerdefense.Utils.Chat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LeaveCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Lobby.LobbyEquip((Player) commandSender);
        } else {
            Chat.send(commandSender, "&cOnly players can execute this command");
        }
        return false;
    }
}
