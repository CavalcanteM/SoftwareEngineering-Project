package ShootingEnemy.Factory;

import ShootingEnemy.ShootingEnemy;
import org.newdawn.slick.geom.Shape;

public abstract class ShootingEnemyFactory {
/**
 * 
 * @param x x position of the shootingenemy
 * @param y y position of the shootingenemy
 * @param hitboxArea activation area of the turret
 * @param difficulty this increases the damage of the bullet that are shot by the turret
 * @return 
 */
    public abstract ShootingEnemy create(int x, int y, Shape hitboxArea, int difficulty);
}
