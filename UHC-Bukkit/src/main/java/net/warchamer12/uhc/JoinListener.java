package net.warchamer12.uhc;

import net.warchamer12.uhc.basic.Arena;
import net.warchamer12.uhc.basic.ArenaStorage;
import net.warchamer12.uhc.utils.Title;
import net.warchamer12.uhc.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class JoinListener implements Listener {

    public static int time = 25;

    public static boolean Start = false;
    public static boolean Game = true;

    public static ArrayList<Player> UHCPlayers = new ArrayList<>();
    public static ArrayList<Player> UHCDeathPlayers = new ArrayList<>();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (Start == true) {
            event.getPlayer().kickPlayer("&6Na tym serwerze aktualnie trwa rozgrywka, sprobuj innym razem!");
        }
        Player player = event.getPlayer();
        UHCPlayers.add(player);

        player.setHealthScale(40D);
        new BukkitRunnable() {
            @Override
            public void run() {
                Arena arena = ArenaStorage.INSTANCE.getArenasMap().get(UHCPlugin.Companion.getConfigManager().getArenaId());
                /*ScoreboardBuilder sidebar = new ScoreboardBuilder(Util.fixColor("&eUHC CHAMPIONS"));

                sidebar.add(Util.fixColor("&7" + data.format(teraz)), -1);
                sidebar.add(" ", -2);
                sidebar.add(Util.fixColor("&fGracze: &a" + Bukkit.getOnlinePlayers() + "/70"), -3);
                sidebar.add(" ", -4);
                sidebar.add(Util.fixColor("&fRozgrywka wystartuje za &a" + time), -5);
                sidebar.add(Util.fixColor("&fjesli bedzie minimum &a30 &fgraczy!"), -6);
                sidebar.add(" ", -7);
                sidebar.add(Util.fixColor("&fTryb: &6&l" + arena.getArenaType().name()), -8);
                sidebar.add("&eUHC CHAMPIONS", -9);
                sidebar.build();
                sidebar.send(player);*/

                ScoreboardManager manager = Bukkit.getScoreboardManager();
                Scoreboard board = manager.getNewScoreboard();

                Objective objective = board.registerNewObjective(Util.fixColor("&eUHC CHAMPIONS"), "dummy");
                objective.setDisplaySlot(DisplaySlot.SIDEBAR);
                objective.setDisplayName(Util.fixColor("&eUHC CHAMPIONS"));

                Date teraz = new Date();
                SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");

                Score s1 = objective.getScore(Util.fixColor("&7" + data.format(teraz) + "      &8" + Bukkit.getServer().getName()));
                s1.setScore(9);
                Score s2 = objective.getScore(Util.fixColor("&1"));
                s2.setScore(8);
                Score s3 = objective.getScore(Util.fixColor("&fGracze: &a" + Bukkit.getOnlinePlayers().size() + "/70"));
                s3.setScore(7);
                Score s4 = objective.getScore(Util.fixColor("&2"));
                s4.setScore(6);
                Score s5 = objective.getScore(Util.fixColor("&fRozgrywka wystartuje za &a" + time + " &fsekund"));
                s5.setScore(5);
                Score s6 = objective.getScore(Util.fixColor("&fjesli bedzie minimum &a30 &fgraczy!"));
                s6.setScore(4);
                Score s7 = objective.getScore(Util.fixColor("&3"));
                s7.setScore(3);
                Score s8 = objective.getScore(Util.fixColor("&fTryb: &6&lSOLO"));
                s8.setScore(2);
                Score s9 = objective.getScore(Util.fixColor("&e&lUHC CHAMPIONS"));
                s9.setScore(1);
                for(Player online : Bukkit.getServer().getOnlinePlayers()) {
                    online.setScoreboard(board);
                }
            }
        }.runTaskLater(UHCPlugin.Companion.getInstance(), 3L);
        if (Game == true) {
            Bukkit.getServer().getConsoleSender().sendMessage("Wlaczono Game na false!");
            Game = false;
            Bukkit.getServer().getConsoleSender().sendMessage("Gra zaraz wystartuje!");
            if (UHCPlayers.size() >= 30) {
                for (Player UHCPlayers : Bukkit.getServer().getOnlinePlayers()) {
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            time--;
                            if (time == 25) {
                                new Title(player).title(Util.fixColor("&aGra startuje za " + time)).times(0, 1, 0).send();
                            }
                            if (time == 0) {
                                    new Title(player).title(Util.fixColor("&aStart!")).times(0, 2, 0).send();
                                    Random random = new Random();
                                    int x = random.nextInt(900);
                                    int z = random.nextInt(900);
                                    Location UHCTeleport = Bukkit.getWorld("UHC").getHighestBlockAt(x, z).getLocation();
                                    UHCPlayers.teleport(UHCTeleport);
                                    cancel();
                                }
                            new Title(player).title(Util.fixColor("&a" + time)).times(0, 1, 0).send();
                        }
                    }.runTaskLater(UHCPlugin.Companion.getInstance(), 20L);
                }
            }
        } else {
            return;
        }
    }


}
