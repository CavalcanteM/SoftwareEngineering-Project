package ShootingEnemyFactory;

import ShootingEnemies.ShootingEnemy;
import ShootingEnemies.HiddenThreeShotTurret;
import org.newdawn.slick.geom.Shape;

public class HiddenThreeShotTurretFactory extends ShootingEnemyFactory {

    @Override
    public ShootingEnemy create(int x, int y, Shape activationArea, int difficulty) {
        return new HiddenThreeShotTurret(x, y, activationArea, difficulty);
    }
}
