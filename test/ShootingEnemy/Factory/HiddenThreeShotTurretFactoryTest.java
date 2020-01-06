/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShootingEnemy.Factory;

import ShootingEnemy.HiddenThreeShotTurret;
import ShootingEnemy.ShootingEnemy;
import org.junit.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author Adria
 */
public class HiddenThreeShotTurretFactoryTest {

    @Test
    public void createTest() {
        int x, y, difficulty;
        Shape activationArea;

        HiddenThreeShotTurretFactory factory = new HiddenThreeShotTurretFactory();
        x=5;y=5; activationArea = new Rectangle(50,50,100,100);difficulty=1;
        ShootingEnemy enemy = factory.create( x , y, activationArea , difficulty);
        Assert.assertTrue(enemy instanceof HiddenThreeShotTurret);
    }
    
}
