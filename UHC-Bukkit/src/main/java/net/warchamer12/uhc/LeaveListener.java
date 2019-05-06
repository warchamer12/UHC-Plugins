package net.warchamer12.uhc;

import net.warchamer12.uhc.utils.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveListener implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        JoinListener.UHCPlayers.remove(player);
        player.sendMessage(Util.fixColor("&6UHC Champions &8--> &7Wyszedles z UHC!"));
    }


}
