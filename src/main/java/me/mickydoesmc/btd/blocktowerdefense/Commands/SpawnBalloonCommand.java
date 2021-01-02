package me.mickydoesmc.btd.blocktowerdefense.Commands;

import me.mickydoesmc.btd.blocktowerdefense.Events.BalloonSpawnEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnBalloonCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Bukkit.getPluginManager().callEvent(new BalloonSpawnEvent(((Player) sender).getLocation()));
            sender.sendMessage(ChatColor.GREEN + "Spawned balloon at your location");
        } else {
            sender.sendMessage(ChatColor.RED + "This command must be executed by a player");
        }
        return true;
    }
}
