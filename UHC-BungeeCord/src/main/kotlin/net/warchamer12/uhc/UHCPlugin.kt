package net.warchamer12.uhc

import net.md_5.bungee.api.plugin.Plugin
import net.warchamer12.uhc.redis.RedisManager

class UHCPlugin : Plugin() {

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

        logger.info("Plugin has been enabled.")
    }

    override fun onDisable() {
        logger.info("Plugin has been disabled.")
    }

}