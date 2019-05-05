package net.warchamer12.uhc.commands;

import net.warchamer12.uhc.managers.PacketManager;
import net.warchamer12.uhc.utils.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HubCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage(Util.fixColor("&6&lUHC Champions &8--> &7Przenoszenie do lobby!"));
            PacketManager.connectPlayer(player.getUniqueId(), "hub");
        } else {
            sender.sendMessage("Uzycie tej komendy nie jest możliwe z poziomu konsoli!");
        }
        return false;
    }
}
