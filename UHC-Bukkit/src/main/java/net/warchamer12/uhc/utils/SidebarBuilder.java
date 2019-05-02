package net.warchamer12.uhc.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class SidebarBuilder {

    private Scoreboard sidebar;

    public void sendScoreboard(Player player) {
        Scoreboard UHCSidebar = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective UHCObject = sidebar.registerNewObjective(Util.fixColor(""), "dummy");
        UHCObject.setDisplaySlot(DisplaySlot.SIDEBAR);
    }
}
