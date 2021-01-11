package org.imanity.console;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ConsoleSpamHiderCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConsoleSpamHider.INSTANCE.reload();
        sender.sendMessage(ChatColor.GREEN + "[ConsoleSpamHider] Reloaded Configurations.");
        return true;
    }
}
