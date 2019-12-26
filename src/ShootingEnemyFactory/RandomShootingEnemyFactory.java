package ShootingEnemyFactory;

import ShootingEnemies.ShootingEnemy;
import org.newdawn.slick.geom.Shape;

public abstract class RandomShootingEnemyFactory {

    public abstract ShootingEnemy create(int x, int y, Shape hitboxArea, int difficulty, String path);
}
