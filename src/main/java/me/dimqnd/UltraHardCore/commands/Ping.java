package me.dimqnd.UltraHardCore.commands;

import me.dimqnd.UltraHardCore.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ping implements CommandExecutor {
    private Main mainReference;

    public Ping(Main mainReference) { this.mainReference = mainReference; }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "[Dimqnd's UHC] Only players can use this command!");
        }

        Player player = (Player) sender;

        player.sendMessage("Pong!");

        return true;
    }
}
