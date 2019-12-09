package Entities.StaticDamage;

import org.newdawn.slick.geom.Shape;

public interface StaticDamage {
/*
    The "StaticDamage" interface is used as an interface for every static object
    that has a fixed positon and can deal damage to the player. 
    */
    public Shape getHitbox();

    public int doDamage();

}
