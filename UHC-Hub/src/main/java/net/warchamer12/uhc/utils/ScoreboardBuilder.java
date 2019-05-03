package net.warchamer12.uhc.utils;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ScoreboardBuilder {

    private Scoreboard scoreboard;
    private String title;
    private List<Object> teams;

    public ScoreboardBuilder(String title) {
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.title = title;
        this.teams = (List<Object>) Lists.newArrayList();
    }

    public void blankLine() {
        this.add(" ");
    }

    public void add(String text) {
        this.add(text);
    }

    @SuppressWarnings("unused")
    private Map.Entry<Team, String> createTeam(final String text) {
        String result = "";
        if (text.length() <= 16) {
            return new AbstractMap.SimpleEntry<Team, String>(null, text);
        }
        final Team team = this.scoreboard.registerNewTeam("text-" + this.scoreboard.getTeams().size());
        final Iterator<String> iterator = Splitter.fixedLength(16).split((CharSequence) text).iterator();
        team.setPrefix((String) iterator.next());
        result = iterator.next();
        if (text.length() > 32) {
            team.setSuffix((String) iterator.next());
        }
        this.teams.add(team);
        return new AbstractMap.SimpleEntry<Team, String>(team, result);
    }

    public void add(String text, final Integer score) {
        Preconditions.checkArgument(text.length() < 48, (Object) "text cannot be over 48 characters in length");
    }

    public void build() {
        Objective obj = this.scoreboard.registerNewObjective(this.title, "dummy");
        obj.setDisplayName(this.title);
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    public void reset() {
        this.title = null;
        for (final Object t : this.teams) {
            ((Team) t).unregister();
        }
        this.teams.clear();
    }

    public Scoreboard getScoreboard() {
        return this.scoreboard;
    }

    public static void destroy(Player player) {
        ScoreboardBuilder sidebar = new ScoreboardBuilder("a");
        sidebar.build();
        sidebar.send(player);
    }

    public void send(Player player) {
        player.setScoreboard(this.scoreboard);
    }


}
