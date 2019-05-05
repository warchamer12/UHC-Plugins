package net.warchamer12.uhc.redis

import com.google.gson.Gson
import net.warchamer12.uhc.basic.Arena
import net.warchamer12.uhc.basic.ArenaStorage
import redis.clients.jedis.JedisPubSub

class RedisListener : JedisPubSub() {

    private val gson = Gson()

    fun listen() {
        Thread {
            RedisManager.getJedis { jedis ->
                jedis.clientSetname("UHC-Hub")
                jedis.subscribe(
                    this,
                    "UHC.createArena"
                )
            }
        }.start()
    }

    override fun onMessage(channel: String, json: String) {
        when (channel) {
            "UHC.createArena" -> {
                val arena = gson.fromJson(json, Arena::class.java)

                ArenaStorage.arenasMap[arena.id] = arena
            }
        }
    }

}