package net.warchamer12.uhc

class ConfigManager {

    var redisHostname = "localhost"
    var redisPassword = "password"
    var redisDatabase = 0
    var redisPort = 6379

    var arenaWorld = "world"
    var arenaId = 0
    var arenaMaxPlayers = 70

    fun loadConfig() {
        UHCPlugin.instance.saveDefaultConfig()
        val config = UHCPlugin.instance.config

        redisHostname = config.getString("config.redis.hostname")
        redisPassword = config.getString("config.redis.password")
        redisDatabase = config.getInt("config.redis.database")
        redisPort = config.getInt("config.redis.port")

        arenaWorld = config.getString("config.arena.world")
        arenaId = config.getInt("config.arena.id")
        arenaMaxPlayers = config.getInt("config.arena.maxPlayers")
    }

}