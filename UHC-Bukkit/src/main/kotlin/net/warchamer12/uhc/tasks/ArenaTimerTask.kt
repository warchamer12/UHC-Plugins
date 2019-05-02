package net.warchamer12.uhc.tasks

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