package net.warchamer12.uhc

import net.warchamer12.uhc.basic.Arena
import net.warchamer12.uhc.commands.GameCommand
import net.warchamer12.uhc.commands.HubCommand
import net.warchamer12.uhc.commands.UHCCommand
import net.warchamer12.uhc.commands.WaitingRoomCommand
import net.warchamer12.uhc.manager.ArenaManager
import net.warchamer12.uhc.manager.ArenaObjects
import net.warchamer12.uhc.redis.RedisManager
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import kotlin.math.log

class UHCPlugin : JavaPlugin() {

    companion object {
        lateinit var instance: UHCPlugin
            private set

        lateinit var configManager: ConfigManager
            private set

        lateinit var redisManager: RedisManager
            private set

        lateinit var arenaManager: ArenaManager
            private set
    }

    override fun onEnable() {
        instance = this

        arenaManager = ArenaManager()

        saveDefaultConfig()
        getCommands()

        logger.info("Creating and loading config.")
        configManager = ConfigManager()
        configManager.loadConfig()

        logger.info("Connecting to redis server.")
        redisManager = RedisManager()
        redisManager.start()

        val pluginManager = Bukkit.getPluginManager()
        pluginManager.registerEvents(JoinListener(), this)
        pluginManager.registerEvents(DeathListener(), this)
        pluginManager.registerEvents(LeaveListener(), this)

        val arena = Arena(configManager.arenaId, configManager.arenaWorld, configManager.arenaMaxPlayers)

        logger.info("Plugin has been enabled.")
        JoinListener.Game = false
        JoinListener.Start = false
        logger.info("Wlaczono gre na true!")

    }

    override fun onDisable() {
        logger.info("Plugin has been disabled.")
    }

    private fun getCommands() {
        getCommand("hub").setExecutor(HubCommand())
        getCommand("waitingroom").setExecutor(WaitingRoomCommand())
        getCommand("start").setExecutor(GameCommand())
        getCommand("uhc").setExecutor(UHCCommand())
    }

    fun getArenaManager():ArenaManager {
        return arenaManager
    }


}