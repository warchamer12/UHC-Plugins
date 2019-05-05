package net.warchamer12.uhc.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

public class Util {

    public static String fixColor(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static String deserializeLocation(final Location location) {
        return String.valueOf(location.getWorld().getName()) + ";" + (location.getBlockX() + 0.5) + ";" + location.getBlockY() + ";" + (location.getBlockZ() + 0.5) + ";" + location.getYaw() + ";" + location.getPitch();
    }

    public static Location serializeLocation(final String location) {
        final String[] deserializedLocation = location.split(";");
        return new Location(Bukkit.getWorld(deserializedLocation[0]), (double)Double.valueOf(deserializedLocation[1]), (double)Double.valueOf(deserializedLocation[2]), (double)Double.valueOf(deserializedLocation[3]), (float)Float.valueOf(deserializedLocation[4]), (float)Float.valueOf(deserializedLocation[5]));
    }


}
