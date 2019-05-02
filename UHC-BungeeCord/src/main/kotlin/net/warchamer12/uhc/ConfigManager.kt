package net.warchamer12.uhc

import net.md_5.bungee.config.ConfigurationProvider
import net.md_5.bungee.config.YamlConfiguration
import java.io.File
import java.io.IOException
import java.nio.file.Files

class ConfigManager {

    var redisHostname = "localhost"
    var redisPassword = "password"
    var redisDatabase = 0
    var redisPort = 6379

    fun loadConfig() {
        saveDefaultConfig()
        val config = ConfigurationProvider.getProvider(YamlConfiguration::class.java).load(File(UHCPlugin.instance.dataFolder, "config.yml"))

        redisHostname = config.getString("config.redis.hostname")
        redisPassword = config.getString("config.redis.password")
        redisDatabase = config.getInt("config.redis.database")
        redisPort = config.getInt("config.redis.port")
    }

    private fun saveDefaultConfig() {
        if (!UHCPlugin.instance.dataFolder.exists()) {
            UHCPlugin.instance.dataFolder.mkdirs()
        }

        val file = File(UHCPlugin.instance.dataFolder, "config.yml")

        if (!file.exists()) {
            try {
                val inputStream = UHCPlugin.instance.getResourceAsStream("config.yml")

                Files.copy(inputStream, file.toPath())
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

}