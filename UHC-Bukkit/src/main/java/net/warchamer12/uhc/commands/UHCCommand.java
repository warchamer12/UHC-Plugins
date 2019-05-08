package net.warchamer12.uhc.commands;

import net.warchamer12.uhc.UHCPlugin;
import net.warchamer12.uhc.manager.ArenaManager;
import net.warchamer12.uhc.manager.ArenaObjects;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UHCCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) { return true; }
        Player player = (Player) sender;
        ArenaManager manager = UHCPlugin.Companion.getArenaManager();
        ArenaObjects arenaObject = manager.getArena(player.getPlayer().getWorld().getName());
        if (player.hasPermission("uhc.admin")) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("set")) {
                    Location PlayerLoc = player.getLocation();
                    arenaObject.setName("uhc");
                    arenaObject.setSpawn(PlayerLoc);
                    arenaObject.setMinPlayers(30);
                    arenaObject.setMaxPlayers(70);
                }
            } else if (args.length > 1) {
                return true;
            }
        }
        return false;
    }
}
