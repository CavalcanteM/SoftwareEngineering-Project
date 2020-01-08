package ShootingEnemy.Factory;

import ShootingEnemy.ShootingEnemy;
import ShootingEnemy.ThreeBulletsTurret;
import org.junit.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class ThreeShootsTurretFactoryTest {

    @Test
    public void createTest() {
        int x, y, difficulty;
        Shape activationArea;

        ThreeShootsTurretFactory factory = new ThreeShootsTurretFactory();
        x = 5;
        y = 5;
        activationArea = new Rectangle(50, 50, 100, 100);
        difficulty = 1;
        ShootingEnemy enemy = factory.create(x, y, activationArea, difficulty);
        Assert.assertTrue(enemy instanceof ThreeBulletsTurret);
    }

}
