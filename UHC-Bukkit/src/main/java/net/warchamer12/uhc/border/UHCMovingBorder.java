package net.warchamer12.uhc.border;

import net.warchamer12.uhc.JavaClass;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.scheduler.BukkitScheduler;

public class UHCMovingBorder {

    private World world;
    private WorldBorder worldBorder;

    private double centerX;
    private double centerZ;
    private double radius;
    private double borderAttackDamage;

    public UHCMovingBorder(World world, double centerX, double centerZ, double radius) {
        super();
        this.world = world;
        this.worldBorder = world.getWorldBorder();
        this.centerX = centerX;
        this.centerZ = centerZ;
        this.radius = radius;
        this.borderAttackDamage = 1;

        this.worldBorder.setCenter(this.centerX, this.centerZ);
        this.worldBorder.setSize(this.radius);
        this.worldBorder.setDamageAmount(1);
        this.worldBorder.setDamageBuffer(0);
        this.worldBorder.setWarningDistance(0);
        this.worldBorder.setWarningTime(0);
    }

    public UHCMovingBorder(World world, Location center, double radius){
        super();
        this.world = world;
        this.worldBorder = world.getWorldBorder();
        this.centerX = center.getX();
        this.centerZ = center.getZ();
        this.radius = radius;
        this.borderAttackDamage = 1;

        this.worldBorder.setCenter(center);
        this.worldBorder.setSize(this.radius);
        this.worldBorder.setDamageAmount(1);
        this.worldBorder.setDamageBuffer(0);
        this.worldBorder.setWarningDistance(0);
        this.worldBorder.setWarningTime(0);
    }

    public UHCMovingBorder(World world, WorldBorder border){
        super();
        this.world = world;
        this.worldBorder = world.getWorldBorder();
        this.centerX = border.getCenter().getX();
        this.centerZ = border.getCenter().getZ();
        this.radius = border.getSize();
        this.borderAttackDamage = 1;
        this.worldBorder.setDamageAmount(1);
        this.worldBorder.setDamageBuffer(0);
        this.worldBorder.setWarningDistance(0);
        this.worldBorder.setWarningTime(0);
    }

    public void moveWorldBorder(double newX, double newZ, double newRadius, int timeInSeconds){
        moveWorldBorder(newX, newZ, newRadius, timeInSeconds, this.borderAttackDamage);
    }

    public void moveWorldBorder(double newX, double newZ, double newRadius, int timeInSeconds, double damageAmount){
        BukkitScheduler bs = Bukkit.getScheduler();
        int stepsTime = timeInSeconds*20;
        if(damageAmount != this.borderAttackDamage)bs.runTaskLater(JavaClass.getPlugin(), ()->{setBorderDamage(damageAmount);}, stepsTime);
        int st = stepsTime/5;
        double gapX = 0;
        double gapZ = 0;
        if(newX < this.centerX) gapX = this.centerX - newX;
        else gapX = newX - this.centerX;
        if(newZ < this.centerZ) gapZ = this.centerZ - newZ;
        else gapZ = newZ - this.centerZ;
        double stepsX = (gapX/st);
        double stepsZ = (gapZ/st);
        this.worldBorder.setSize(newRadius, timeInSeconds);
        for(int i = 1; i <= st; i++){
            if(newX < this.centerX){
                double n = this.centerX-(i*stepsX);
                bs.runTaskLaterAsynchronously(JavaClass.getPlugin(), ()->{
                    this.worldBorder.setCenter(n, this.worldBorder.getCenter().getZ());
                }, i*5);
            }else{
                double n = this.centerX+(i*stepsX);
                bs.runTaskLaterAsynchronously(JavaClass.getPlugin(), ()->{
                    this.worldBorder.setCenter(n, this.worldBorder.getCenter().getZ());
                }, i*5);
            }
        }
        for(int i = 1; i <= st; i++){
            if(newZ < this.centerZ){
                double n = this.centerZ-(i*stepsZ);
                bs.runTaskLaterAsynchronously(JavaClass.getPlugin(), ()->{
                    this.worldBorder.setCenter(this.worldBorder.getCenter().getX(), n);
                }, i*5);
            }else{
                double n = this.centerZ+(i*stepsZ);
                bs.runTaskLaterAsynchronously(JavaClass.getPlugin(), ()->{
                    this.worldBorder.setCenter(this.worldBorder.getCenter().getX(), n);
                }, i*5);
            }
        }
    }

    public World getWorld(){
        return this.world;
    }

    public WorldBorder getWorldBorder(){
        return this.worldBorder;
    }

    public double getRadius(){
        return this.radius;
    }

    public double getCenterX(){
        return this.centerX;
    }

    public double getCenterZ(){
        return this.centerZ;
    }

    public void setBorderDamage(double damageAmount){
        this.borderAttackDamage = damageAmount;
        this.worldBorder.setDamageAmount(damageAmount);
    }

    public void setBorderDamageBuffer(double damageBuffer){
        this.worldBorder.setDamageBuffer(damageBuffer);
    }

    public double getBorderDamage(){
        return this.borderAttackDamage;
    }


}
