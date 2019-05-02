package net.warchamer12.uhc.redis

import net.warchamer12.uhc.HubPlugin
import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig
import redis.clients.jedis.Pipeline
import redis.clients.jedis.exceptions.JedisConnectionException

class RedisManager {

    fun start() {
        jedisPool = JedisPool(
            JedisPoolConfig(),
            HubPlugin.configManager.redisHostname,
            HubPlugin.configManager.redisPort, 10000,
            HubPlugin.configManager.redisPassword,
            HubPlugin.configManager.redisDatabase)
        RedisListener().listen()
    }

    companion object {
        private lateinit var jedisPool: JedisPool

        fun destroy() {
            jedisPool.destroy()
        }

        fun getJedis(code: (Jedis) -> Unit) {
            var jedis: Jedis? = null

            try {
                jedis = jedisPool.resource

                code.invoke(jedis)
            } catch (e: JedisConnectionException) {
                if (jedis != null) {
                    jedisPool.returnBrokenResource(jedis)
                }
            } finally {
                if (jedis != null) {
                    jedisPool.returnResource(jedis)
                }
            }
        }

        fun getPipeline(code: (Pipeline) -> Unit) {
            var jedis: Jedis? = null

            try {
                jedis = jedisPool.resource
                val pipeline = jedis.pipelined()

                code.invoke(pipeline)
                pipeline.sync()
            } catch (e: JedisConnectionException) {
                if (jedis != null) {
                    jedisPool.returnBrokenResource(jedis)
                }
            } finally {
                if (jedis != null) {
                    jedisPool.returnResource(jedis)
                }
            }
        }
    }

}