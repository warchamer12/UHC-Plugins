package net.warchamer12.uhc.basic

import net.warchamer12.uhc.managers.PacketManager
import org.bukkit.entity.Player

data class Arena(val id: Int, val worldName: String, val maxPlayers: Int) {

    var arenaType = ArenaTimeType.WAITING_FOR_PLAYERS

    init {
        ArenaStorage.arenasMap[id] = this

        PacketManager.createArenaInLobby(this)
    }

    val onlinePlayers: MutableCollection<Player> = mutableSetOf()

    fun update() {
        PacketManager.createArenaInLobby(this)
    }

}