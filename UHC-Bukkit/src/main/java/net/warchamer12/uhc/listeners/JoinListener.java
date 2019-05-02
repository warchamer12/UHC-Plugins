package net.warchamer12.uhc.listeners;

import net.warchamer12.uhc.UHCPlugin;
import net.warchamer12.uhc.utils.Util;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        new BukkitRunnable() {
            @Override
            public void run() {
                Util.sendScoreboard(player);
            }
        }.runTaskLater(UHCPlugin.Companion.getInstance(), 3L);
    }
}
