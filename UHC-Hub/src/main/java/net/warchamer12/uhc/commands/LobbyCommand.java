package net.warchamer12.uhc.commands;

import net.warchamer12.uhc.HubPlugin;
import net.warchamer12.uhc.utils.Util;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LobbyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Uzycie tej komendy nie jest mozliwe z konsoli!");
            return true;
        }
        Player player = ((Player) sender).getPlayer();
        if (args.length == 1 && player.hasPermission("uhc.admin") && args[0].equalsIgnoreCase("ustaw")) {
            Location UHCLobbyLocation = player.getLocation();
            String UHCLobby = Util.deserializeLocation(UHCLobbyLocation);
            HubPlugin.getPlugin().getConfig().set("Hub", UHCLobby);
            HubPlugin.getPlugin().saveConfig();
            return true;
        }
        return false;
    }
}
