package net.warchamer12.uhc;

import net.warchamer12.uhc.utils.Title;
import net.warchamer12.uhc.utils.Util;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class DeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (event.getEntity() != null && event.getEntity().getKiller() != null && event.getEntity() instanceof Player && event.getEntity().getKiller() instanceof Player) {
            Player zabity =  event.getEntity();
            JoinListener.UHCPlayers.remove(zabity);
            JoinListener.UHCDeathPlayers.add(zabity);
            Player zabojca = zabity.getKiller();
            new Title(zabojca).title(Util.fixColor("&cZabiles gracza: " + zabity)).times(0, 1, 0).send();
            zabity.setGameMode(GameMode.ADVENTURE);
            zabity.getAllowFlight();
            zabity.setFlying(true);
            zabity.setFlySpeed(4);
            new Title(zabity).title(Util.fixColor("&cUmarles!")).times(1, 3, 1).send();
            for (Player UHCPlayers : JoinListener.UHCPlayers) {
                zabity.hidePlayer(UHCPlayers);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (JoinListener.Game == false) {
                            zabity.showPlayer((Player) JoinListener.UHCDeathPlayers);
                        } else {
                            cancel();
                        }
                    }
                }.runTaskLater(UHCPlugin.Companion.getInstance(), 30L);
            }
        } else {
            Player zabity = event.getEntity();
            JoinListener.UHCPlayers.remove(zabity);
            JoinListener.UHCDeathPlayers.add(zabity);
            zabity.setGameMode(GameMode.ADVENTURE);
            zabity.getAllowFlight();
            zabity.setFlying(true);
            zabity.setFlySpeed(4);
            new Title(zabity).title(Util.fixColor("&cUmarles!")).times(1, 3, 1).send();
            for (Player UHCPlayers : JoinListener.UHCPlayers) {
                zabity.hidePlayer(UHCPlayers);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (JoinListener.Game == false) {
                            zabity.showPlayer((Player) JoinListener.UHCDeathPlayers);
                        } else {
                            cancel();
                        }
                    }
                }.runTaskLater(UHCPlugin.Companion.getInstance(), 30L);
            }
        }
    }


}
