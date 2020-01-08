package ShootingEnemy.Factory;

import ShootingEnemy.ShootingEnemy;
import ShootingEnemy.HiddenThreeShotTurret;
import org.newdawn.slick.geom.Shape;

public class HiddenThreeShotTurretFactory extends ShootingEnemyFactory {
/**
 * 
 * @param x x position of the shootingenemy
 * @param y y position of the shootingenemy
 * @param activationArea activation area of the turret
 * @param difficulty this increases the damage of the bullet that are shot by the turret
 * @return 
 */
    @Override
    public ShootingEnemy create(int x, int y, Shape activationArea, int difficulty) {
        return new HiddenThreeShotTurret(x, y, activationArea, difficulty);
    }
}
