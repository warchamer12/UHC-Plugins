package net.warchamer12.uhc;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerInteractSwordPvpListener implements Listener {

    @EventHandler
    public void onPlayerInteract(EntityDamageEvent event) {
        if (JoinListener.PVP == false) {
            event.setCancelled(true);
        } else {
            return;
        }
    }
}
