package net.warchamer12.uhc.managers

import com.google.gson.Gson
import com.google.gson.JsonObject
import net.warchamer12.uhc.redis.RedisManager
import java.util.*

object PacketManager {

    private val gson = Gson()

    @JvmStatic
    fun connectPlayer(uuid: UUID, server: String) {
        RedisManager.getJedis { jedis ->
            val jsonObject = JsonObject()
            jsonObject.addProperty("uuid", uuid.toString())
            jsonObject.addProperty("server", server)

            jedis.publish("UHC.connectPlayer", gson.toJson(jsonObject))
        }
    }

    @JvmStatic
    fun joinPlayerToArena(uuid: UUID, id: Int) {
        RedisManager.getJedis { jedis ->
            val jsonObject = JsonObject()
            jsonObject.addProperty("uuid", uuid.toString())
            jsonObject.addProperty("id", id)

            jedis.publish("UHC.joinPlayerToArena", gson.toJson(jsonObject))
        }
    }

}