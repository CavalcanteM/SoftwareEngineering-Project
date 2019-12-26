package ShootingEnemyFactory;

import ShootingEnemies.ShootingEnemy;
import ShootingEnemies.RandomTurret;
import org.newdawn.slick.geom.Shape;

public class RandomTurretFactory extends ShootingEnemyFactory{
    
    @Override
    public ShootingEnemy create(int x, int y, Shape activationArea, int difficulty) {
        return new RandomTurret(x, y, activationArea, difficulty);
    }    
}

