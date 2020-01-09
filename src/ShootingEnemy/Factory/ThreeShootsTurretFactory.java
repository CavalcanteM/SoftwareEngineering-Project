package ShootingEnemy.Factory;

import ShootingEnemy.ThreeBulletsTurret;
import ShootingEnemy.ShootingEnemy;
import org.newdawn.slick.geom.Shape;

public class ThreeShootsTurretFactory extends ShootingEnemyFactory {

    /**
     *
     * @param x x position of the turret
     * @param y y position of the turret
     * @param hitboxArea activation area of the turret
     * @param difficulty this increases the damage of the bullet that are shot
     * by the turret
     * @return
     */
    @Override
    public ShootingEnemy create(int x, int y, Shape hitboxArea, int difficulty) {
        return new ThreeBulletsTurret(x, y, hitboxArea, difficulty);
    }
}
