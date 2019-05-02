package net.warchamer12.uhc.redis

import com.google.gson.Gson
import com.google.gson.JsonObject
import net.md_5.bungee.api.ProxyServer
import redis.clients.jedis.JedisPubSub
import java.util.*

class RedisListener : JedisPubSub() {

    private val gson = Gson()

    fun listen() {
        Thread {
            RedisManager.getJedis { jedis ->
                jedis.clientSetname("UHC-BungeeCord")
                jedis.subscribe(
                    this,
                    "UHC.connectPlayer"
                )
            }
        }.start()
    }

    override fun onMessage(channel: String, json: String) {
        when (channel) {
            "UHC.connectPlayer" -> {
                val jsonObject = gson.fromJson(json, JsonObject::class.java)

                val uuid = UUID.fromString(jsonObject.get("uuid").asString)
                val player = ProxyServer.getInstance().getPlayer(uuid) ?: return
                val server = jsonObject.get("server").asString
                val serverInfo = ProxyServer.getInstance().getServerInfo(server) ?: return

                if (player.server.info.name == serverInfo.name)
                    return

                player.connect(serverInfo)
            }
        }
    }

}