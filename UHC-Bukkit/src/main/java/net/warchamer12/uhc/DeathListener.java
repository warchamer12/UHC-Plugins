package net.warchamer12.uhc;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (event.getEntity() instanceof Player && event.getEntity().getKiller() instanceof Player) {
            Player zabity = (Player) event.getEntity();
            JoinListener.UHCPlayers.remove(zabity);
            JoinListener.UHCDeathPlayers.add(zabity);
            Player zabojca = zabity.getKiller();
        }
    }


}
