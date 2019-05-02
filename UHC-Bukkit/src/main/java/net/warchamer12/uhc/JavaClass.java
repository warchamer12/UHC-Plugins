package net.warchamer12.uhc;

import net.warchamer12.uhc.listeners.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class JavaClass extends JavaPlugin {

    private static JavaClass plugin;

    @Override
    public void onEnable() {
        getLogger().info("On");
        getListeners();
        getCommands();
    }

    private void getListeners() {
        PluginManager UHC = Bukkit.getPluginManager();
        UHC.registerEvents(new JoinListener(), this);
    }

    private void getCommands() {
    }

    @Override
    public void onDisable() {
        getLogger().info("Off");
    }

    public static JavaClass getPlugin() {
        return plugin;
    }

    public Plugin getInstance() {
        return null;
    }
}
