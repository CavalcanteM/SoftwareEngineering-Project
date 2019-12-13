package Entities.Turret;

import Entities.Turret.Bullets.Bullet;
import org.newdawn.slick.geom.Shape;

public class ThreeBulletsTurret implements ShootingEnemy {

    private Shape hitboxArea;
    int x, y;

    public ThreeBulletsTurret(int x, int y, Shape hitboxArea) {
        this.x = x;
        this.y = y;
        this.hitboxArea = hitboxArea;
    }

    @Override
    public Shape getHitboxArea() {
        return this.hitboxArea;
    }

    @Override
    public Bullet Shoot(float x2, float y2) {
        return new Bullet(x, y, x2, y2, hitboxArea);
    }

}
