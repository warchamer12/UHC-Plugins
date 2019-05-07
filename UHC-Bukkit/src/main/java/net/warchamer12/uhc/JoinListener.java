package net.warchamer12.uhc;

import jdk.nashorn.internal.ir.Block;
import net.warchamer12.uhc.basic.Arena;
import net.warchamer12.uhc.basic.ArenaStorage;
import net.warchamer12.uhc.utils.Title;
import net.warchamer12.uhc.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
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

    public static boolean PVP = false;

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
        }.runTaskTimer(UHCPlugin.Companion.getInstance(), 20L, 20L);
        if (Game == true) {
            Bukkit.getServer().getConsoleSender().sendMessage("Wlaczono Game na false!");
            Game = false;
            Bukkit.getServer().getConsoleSender().sendMessage("Gra zaraz wystartuje!");
            if (UHCPlayers.size() >= 0) {
                for (Player UHCPlayers : Bukkit.getServer().getOnlinePlayers()) {
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (time == 24) {
                                new Title(UHCPlayers).title(Util.fixColor("&aGra startuje za " + time)).times(0, 1, 0).send();
                                return;
                            }
                            if (time == 0) {
                                new Title(UHCPlayers).title(Util.fixColor("&aStart!")).times(2, 3, 1).send();
                                Random random = new Random();
                                int x = random.nextInt(900);
                                int z = random.nextInt(900);
                                int y = Bukkit.getWorld("world").getHighestBlockYAt(x, z);
                                //Location UHCTeleport = Bukkit.getWorld("world").getHighestBlockAt(x, z).getLocation();
                                World world = Bukkit.getWorld("world");
                                Location loc = new Location(world, x, y + 1, z);
                                UHCPlayers.teleport(loc);
                                UHCPlayers.setHealthScale(40);
                                UHCPlayers.setFoodLevel(15);
                                Bukkit.getServer().getScheduler().cancelTask(time);
                                cancel();
                            }
                            new Title(UHCPlayers).title(Util.fixColor("&a" + time)).times(0, 1, 0).send();
                            time--;
                        }
                    }.runTaskTimer(UHCPlugin.Companion.getInstance(), 20L, 20L);
                }
            }
        } else {
            return;
        }
    }


}
