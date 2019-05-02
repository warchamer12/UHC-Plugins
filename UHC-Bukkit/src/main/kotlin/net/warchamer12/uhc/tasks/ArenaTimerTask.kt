package net.warchamer12.uhc.tasks

import net.warchamer12.uhc.border.UHCApiBorder
import net.warchamer12.uhc.border.UHCMovingBorder

class ArenaTimerTask : Runnable {

    private var minutes = 40

    override fun run() {
        if (minutes == 30) {
            //Wlaczenie pvp

        } else if (minutes == 0) {
            //teleportowanie do deathmetchu
        }

        minutes--
    }

}