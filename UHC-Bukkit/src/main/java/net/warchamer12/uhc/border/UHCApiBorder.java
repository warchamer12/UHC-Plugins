package net.warchamer12.uhc.border;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;

import java.util.ArrayList;

public class UHCApiBorder {

    private ArrayList<UHCMovingBorder> borders = new ArrayList<UHCMovingBorder>();
    private static UHCApiBorder instance;
    public UHCApiBorder(){
        instance = this;
    }

    public static UHCApiBorder getAPI(){
        return instance;
    }

    public UHCMovingBorder createBorder(World world, Location center, double radius){
        UHCMovingBorder border = new UHCMovingBorder(world, center, radius);
        borders.add(border);
        return border;
    }

    public UHCMovingBorder createBorder(World world, WorldBorder worldBorder){
        UHCMovingBorder border = new UHCMovingBorder(world, worldBorder);
        borders.add(border);
        return border;
    }

    public UHCMovingBorder createBorder(World world, double centerX, double centerZ, double radius){
        UHCMovingBorder border = new UHCMovingBorder(world, centerX, centerZ, radius);
        borders.add(border);
        return border;
    }

    public UHCMovingBorder getBorder(World world){
        if(borders.isEmpty())return null;
        for(UHCMovingBorder b : borders){
            if(b.getWorld().getName().equals(world.getName()))return b;
        }
        return null;
    }
}
