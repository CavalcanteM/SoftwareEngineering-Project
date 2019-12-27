package ShootingEnemy.Factory;

import ShootingEnemy.ThreeBulletsTurret;
import ShootingEnemy.ShootingEnemy;
import org.newdawn.slick.geom.Shape;

public class ThreeShootsTurretFactory extends ShootingEnemyFactory{
    
    @Override
    public ShootingEnemy create(int x, int y, Shape hitboxArea, int difficulty) {
        return new ThreeBulletsTurret(x, y, hitboxArea, difficulty);
    }    
}