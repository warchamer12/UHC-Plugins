package net.warchamer12.uhc.redis

import com.google.gson.Gson
import com.google.gson.JsonObject
import net.warchamer12.uhc.basic.ArenaStorage
import org.bukkit.Bukkit
import redis.clients.jedis.JedisPubSub
import java.util.*

class RedisListener : JedisPubSub() {

    private val gson = Gson()

    fun listen() {
        Thread {
            RedisManager.getJedis { jedis ->
                jedis.clientSetname("UHC-Bukkit")
                jedis.subscribe(
                    this,
                    "UHC.joinPlayerToArena"
                )
            }
        }.start()
    }

    override fun onMessage(channel: String, json: String) {
        when (channel) {
            "UHC.joinPlayerToArena" -> {
                val jsonObject = gson.fromJson(json, JsonObject::class.java)

                val uuid = UUID.fromString(jsonObject.get("uuid").asString)
                val player = Bukkit.getPlayer(uuid) ?: return
                val id = jsonObject.get("id").asInt

                val arena = ArenaStorage.arenasMap[id] ?: throw NullPointerException("no arena for id $id found")
                arena.onlinePlayers.add(player)
            }
        }
    }

}