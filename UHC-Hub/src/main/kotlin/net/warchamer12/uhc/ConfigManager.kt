package net.warchamer12.uhc

class ConfigManager {

    var redisHostname = "localhost"
    var redisPassword = "password"
    var redisDatabase = 0
    var redisPort = 6379

    fun loadConfig() {
        HubPlugin.instance.saveDefaultConfig()
        val config = HubPlugin.instance.config

        redisHostname = config.getString("config.redis.hostname")
        redisPassword = config.getString("config.redis.password")
        redisDatabase = config.getInt("config.redis.database")
        redisPort = config.getInt("config.redis.port")


    }


}