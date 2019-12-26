package ShootingEnemyFactory;

import ShootingEnemies.ShootingEnemy;
import ShootingEnemies.RandomThreeShotTurret;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

public class RandomThreeShotTurretFactory extends RandomShootingEnemyFactory{
    
    @Override
    public ShootingEnemy create(int x, int y, Shape activationArea, int difficulty, String path) {
        RandomThreeShotTurret RandomThreeShotTurret = new RandomThreeShotTurret(x, y, activationArea, difficulty);
        
        try {
            RandomThreeShotTurret.setImage(path);
        } catch (SlickException ex) {
            Logger.getLogger(RandomThreeShotTurretFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return RandomThreeShotTurret;
    }    
}

