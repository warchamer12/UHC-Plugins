package net.warchamer12.uhc.utils;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;
import net.minecraft.server.v1_8_R3.PlayerConnection;

public class Title {

    private PlayerConnection con;
    PacketPlayOutTitle title;
    PacketPlayOutTitle subtitle;
    PacketPlayOutTitle times;

    public Title(Player p) {
        con = ((CraftPlayer) p).getHandle().playerConnection;
    }

    public Title(Player p, String title) {
        con = ((CraftPlayer) p).getHandle().playerConnection; title(title);
    }

    public Title title(String title) {
        IChatBaseComponent comp = ChatSerializer
                .a("{text:\"" + ChatColor.translateAlternateColorCodes('&', title) + "\"}");
        this.title = new PacketPlayOutTitle(EnumTitleAction.TITLE, comp);
        return this;
    }

    public Title subtitle(String subtitle) {
        IChatBaseComponent comp = ChatSerializer
                .a("{text:\"" + ChatColor.translateAlternateColorCodes('&', subtitle) + "\"}");
        this.subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, comp);
        return this;
    }

    public Title times(int fadeIn, int displayTime, int fadeOut) {
        times = new PacketPlayOutTitle(fadeIn, displayTime, fadeOut);
        return this;
    }

    public void send() {
        if (title != null) {
            con.sendPacket(title);
            if (subtitle != null)
                con.sendPacket(subtitle);
            if (times != null)
                con.sendPacket(subtitle);
        }
    }


}
