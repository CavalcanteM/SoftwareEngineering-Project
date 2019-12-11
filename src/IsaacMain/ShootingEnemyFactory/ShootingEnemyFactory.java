package IsaacMain.ShootingEnemyFactory;

import Entities.Turret.ShootingEnemy;
import org.newdawn.slick.geom.Shape;

public abstract class ShootingEnemyFactory {
    
    public abstract ShootingEnemy create(int x, int y, Shape hitboxArea);
}
