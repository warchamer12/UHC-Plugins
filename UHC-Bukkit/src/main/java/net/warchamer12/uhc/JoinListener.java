package net.warchamer12.uhc;

import net.minecraft.server.v1_8_R3.MinecraftServer;
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
        UHCDeathPlayers.remove(player);
        UHCPlayers.add(player);

        player.setHealthScale(40D);
        new BukkitRunnable() {
            @Override
            public void run() {
                Arena arena = ArenaStorage.INSTANCE.getArenasMap().get(UHCPlugin.Companion.getConfigManager().getArenaId());

                ScoreboardManager manager = Bukkit.getScoreboardManager();
                Scoreboard board = manager.getNewScoreboard();

                String name = Util.fixColor("&eUHC CHAMPIONS");

                Objective objective = board.registerNewObjective(Util.fixColor(name), "dummy");
                objective.setDisplaySlot(DisplaySlot.SIDEBAR);
                objective.setDisplayName(Util.fixColor(name));



                Date teraz = new Date();
                SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");

                Score s1 = objective.getScore(Util.fixColor("&7" + data.format(teraz) + "        &8" + Bukkit.getServer().getName()));
                s1.setScore(10);
                Score s2 = objective.getScore(Util.fixColor("&1"));
                s2.setScore(9);
                Score s3 = objective.getScore(Util.fixColor("&fGracze: &a" + Bukkit.getOnlinePlayers().size() + "/70"));
                s3.setScore(8);
                Score s4 = objective.getScore(Util.fixColor("&2"));
                s4.setScore(7);
                Score s5 = objective.getScore(Util.fixColor("&fRozgrywka wystartuje za &a" + time + " &fsekund"));
                s5.setScore(6);
                Score s6 = objective.getScore(Util.fixColor("&fjesli bedzie minimum &a30 &fgraczy!"));
                s6.setScore(5);
                Score s7 = objective.getScore(Util.fixColor("&3"));
                s7.setScore(4);
                Score s8 = objective.getScore(Util.fixColor("&fTryb: &6&lSOLO"));
                s8.setScore(3);
                Score s9 = objective.getScore(Util.fixColor("&4"));
                s9.setScore(2);
                Score s10 = objective.getScore(Util.fixColor("&euhc-champions.pl"));
                s10.setScore(1);
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
                                new Title(UHCPlayers).title(Util.fixColor("&aGra startuje za " + time)).times(0, 1, 0).send();
                            }
                            if (time == 0) {
                                    new Title(UHCPlayers).title(Util.fixColor("&aStart!")).times(0, 2, 0).send();
                                    Random random = new Random();
                                    int x = random.nextInt(900);
                                    int z = random.nextInt(900);
                                    Location UHCTeleport = Bukkit.getWorld("UHC").getHighestBlockAt(x, z).getLocation();
                                    UHCPlayers.teleport(UHCTeleport);
                                    cancel();
                                }
                            new Title(UHCPlayers).title(Util.fixColor("&a" + time)).times(0, 1, 0).send();
                        }
                    }.runTaskLater(UHCPlugin.Companion.getInstance(), 20L);
                }
            }
        } else {
            return;
        }
    }


}
