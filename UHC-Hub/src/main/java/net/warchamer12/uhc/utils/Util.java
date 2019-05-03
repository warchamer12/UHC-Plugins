package net.warchamer12.uhc.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Util {

    public static String fixColor(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }


}
