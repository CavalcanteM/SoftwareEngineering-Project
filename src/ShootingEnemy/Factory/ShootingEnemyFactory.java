package ShootingEnemy.Factory;

import ShootingEnemy.ShootingEnemy;
import org.newdawn.slick.geom.Shape;

public abstract class ShootingEnemyFactory {

    public abstract ShootingEnemy create(int x, int y, Shape hitboxArea, int difficulty);
}
