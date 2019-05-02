package net.warchamer12.uhc.tasks

class ArenaTimerTask : Runnable {

    private var minutes = 0

    override fun run() {
        if (minutes == 10) {
            //Wlaczenie pvp
        } else if (minutes == 40) {
            //teleportowanie do deathmetchu
        }

        minutes++
    }

}