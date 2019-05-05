package net.warchamer12.uhc.commands;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.warchamer12.uhc.utils.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HubCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            player.sendMessage(Util.fixColor("&6&lUHC Champions &8--> &7Przenoszenie do lobby!"));
            player.connect(ProxyServer.getInstance().getServerInfo("hub"));
        } else {
            sender.sendMessage("Uzycie tej komendy nie jest możliwe z poziomu konsoli!");
        }
        return false;
    }
}
