package net.warchamer12.uhc.commands;

import net.warchamer12.uhc.JoinListener;
import net.warchamer12.uhc.utils.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GameCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("uhc.admin")) {
            sender.sendMessage(Util.fixColor("&cWlaczono rozgrywke!"));
            JoinListener.Game = true;
            return true;
        }
        return false;
    }


}
