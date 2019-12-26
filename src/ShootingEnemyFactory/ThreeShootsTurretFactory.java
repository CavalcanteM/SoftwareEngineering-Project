package ShootingEnemyFactory;

import ShootingEnemies.ShootingEnemy;
import ShootingEnemies.ThreeBulletsTurret;
import org.newdawn.slick.geom.Shape;

public class ThreeShootsTurretFactory extends ShootingEnemyFactory{
    
    @Override
    public ShootingEnemy create(int x, int y, Shape hitboxArea, int difficulty) {
        return new ThreeBulletsTurret(x, y, hitboxArea, difficulty);
    }    
}