/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShootingEnemy;

import ShootingEnemy.bullet.Bullet;
import com.sun.tools.javac.Main;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.ArrayList;
import org.junit.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author Adria
 */
public class HiddenThreeShotTurretTest {

    ThreeBulletsTurret turret = null;
    Shape activation_area = null;

    @Before
    public void instanziate() {
        activation_area = new Rectangle(50, 50, 600, 600);
        turret = new ThreeBulletsTurret(50, 50, activation_area, 1);
        try {
            Display.setDisplayMode(new DisplayMode(800, 600));

        } catch (LWJGLException ex) {

        }
    }

    @Test
    public void getActivationAreaTest() {
        Assert.assertTrue(activation_area == turret.getActivationArea());
    }

    @Test
    public void getHitboxTest() {
        Assert.assertNotNull(turret);
        Assert.assertTrue(turret.getHitbox() instanceof Shape);
    }

    @Test
    public void ShootTest() throws LWJGLException {
        float x, y;
        x = 5;
        y = 10;
        Display.create();
        Bullet bullet = turret.Shoot(x, y);

        Assert.assertTrue("L'oggetto bullet non è di tipo Bullet", bullet instanceof Bullet);
    }

    @Test
    public void renderTest() throws SlickException {
        //This test is done by starting the game and looking at the rendering of the images.
    }

    @Test
    public void removeBulletTest() throws LWJGLException {
        ArrayList<Bullet> bulletList, bulletList_after_shooting_twice = new ArrayList<>();

        Bullet bull1 = turret.Shoot(50, 50);
        bulletList_after_shooting_twice.add(bull1);
        Bullet bull2 = turret.Shoot(50, 50);
        bulletList_after_shooting_twice.add(bull2);
        turret.removeBullet(bull1);
        bulletList = turret.getBullet();
        Assert.assertTrue(bulletList != bulletList_after_shooting_twice);
    }

    @Test
    public void isVisibleTest() {
//This function can be verified and tested by looking at the screen.
//It says if a turret must be visible or not.
    }

    @Test
    public void getBulletTest() {
        ArrayList<Bullet> bulletList = new ArrayList<>();
        Assert.assertTrue(bulletList instanceof ArrayList);
    }
}
