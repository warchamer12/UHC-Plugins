package net.warchamer12.uhc.tasks

import net.warchamer12.uhc.border.UHCApiBorder
import org.bukkit.Bukkit

class ArenaTimerTask : Runnable {

    private val border = UHCApiBorder.getAPI().createBorder(Bukkit.getWorlds()[0], Bukkit.getWorlds()[0].worldBorder)
    private var minutes = 50

    override fun run() {
        when (minutes) {
            50 -> border.moveWorldBorder(0.0, 0.0, 1000.0, 0, 0.0)
            40 -> border.moveWorldBorder(0.0, 0.0, 800.0, 120, 1.0)
            30 -> border.moveWorldBorder(0.0, 0.0, 600.0, 120, 1.0)
            20 -> border.moveWorldBorder(0.0, 0.0, 400.0, 120, 1.0)
            10 -> border.moveWorldBorder(0.0, 0.0, 200.0, 120, 1.0)
            0 -> {
                //deathmatch
            }
        }

        minutes--
    }

}