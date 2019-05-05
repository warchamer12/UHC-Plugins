package net.warchamer12.uhc.basic

import org.bukkit.entity.Player

data class Arena(val id: Int, val maxPlayers: Int) {

    var arenaType = ArenaTimeType.WAITING_FOR_PLAYERS

    init {
        ArenaStorage.arenasMap[id] = this
    }

    val onlinePlayers: MutableCollection<Player> = mutableSetOf()

}