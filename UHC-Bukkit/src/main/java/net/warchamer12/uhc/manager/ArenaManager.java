package net.warchamer12.uhc.manager;

import java.util.ArrayList;
import java.util.List;

public class ArenaManager {

    public List<ArenaObjects> arenas = new ArrayList<>();
    public ArenaObjects getArena(String name){
        return arenas.stream().filter(arena -> arena.getName().equals(name)).findFirst().orElse(null);
    }
}
