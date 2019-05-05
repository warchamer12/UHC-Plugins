package net.warchamer12.uhc

import net.warchamer12.uhc.redis.RedisManager
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class UHCPlugin : JavaPlugin() {

    companion object {
        lateinit var instance: UHCPlugin
            private set

        lateinit var configManager: ConfigManager
            private set

        lateinit var redisManager: RedisManager
            private set
    }

    override fun onEnable() {
        instance = this

        logger.info("Creating and loading config.")
        configManager = ConfigManager()
        configManager.loadConfig()

        logger.info("Connecting to redis server.")
        redisManager = RedisManager()
        redisManager.start()

        val pluginManager = Bukkit.getPluginManager()
        pluginManager.registerEvents(JoinListener(), this)

        logger.info("Plugin has been enabled.")
    }

    override fun onDisable() {
        logger.info("Plugin has been disabled.")
    }
}