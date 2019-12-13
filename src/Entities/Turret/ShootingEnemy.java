package Entities.Turret;

import Entities.Turret.Bullets.Bullet;
import org.newdawn.slick.geom.Shape;


public interface ShootingEnemy {

    public Shape getHitboxArea();

    public Bullet Shoot(float x2, float y2);
}
