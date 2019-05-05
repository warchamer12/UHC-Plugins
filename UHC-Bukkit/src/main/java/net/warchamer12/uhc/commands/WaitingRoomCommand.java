package net.warchamer12.uhc.commands;

import net.warchamer12.uhc.UHCPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WaitingRoomCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            UHCPlugin.Companion.getInstance().getConfig().set("WaitingRoom", 1);
            UHCPlugin.Companion.getInstance().saveConfig();
        } else {
            sender.sendMessage("Uzycie tej komendy nie jest możliwe z poziomu konsoli!");
        }
        return false;
    }

}
