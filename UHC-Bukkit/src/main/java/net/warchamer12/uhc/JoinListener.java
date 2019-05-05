package net.warchamer12.uhc;

import net.warchamer12.uhc.basic.Arena;
import net.warchamer12.uhc.basic.ArenaStorage;
import net.warchamer12.uhc.utils.ScoreboardBuilder;
import net.warchamer12.uhc.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        Arena arena = ArenaStorage.INSTANCE.getArenasMap().get(UHCPlugin.Companion.getConfigManager().getArenaId());

        player.setHealthScale(20D);
        ScoreboardBuilder sidebar = new ScoreboardBuilder(Util.fixColor("&e&lUHC CHAMPIONS"));

        Date teraz = new Date();
        SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");

        sidebar.add(Util.fixColor("&7" + data.format(teraz)), -1);
        sidebar.add(" ", -2);
        sidebar.add(Util.fixColor("&fGracze: &a" + Bukkit.getOnlinePlayers() + "/70"), -3);
        sidebar.add(" ", -4);
        sidebar.add(Util.fixColor("&fRozgrywka wystartuje za &a"), -5);
        sidebar.add(Util.fixColor("&fjesli dolaczy jeszcze &a" + (arena.getMaxPlayers() - arena.getOnlinePlayers().size()) + " &fgraczy!"), -6);
        sidebar.add(" ", -7);
        sidebar.add(Util.fixColor("&fTryb: &6&l" + arena.getArenaType().name()), -8);
        sidebar.add("&eTutaj bedzie domena!", -9);

        new BukkitRunnable() {
            @Override
            public void run() {
                sidebar.build();
                sidebar.send(player);
            }
        }.runTaskLater(UHCPlugin.Companion.getInstance(), 3L);
    }


}
