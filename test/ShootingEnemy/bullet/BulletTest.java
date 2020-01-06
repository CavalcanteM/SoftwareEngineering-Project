package ShootingEnemy.bullet;

import ShootingEnemy.ShootingEnemy;
import ShootingEnemy.ThreeBulletsTurret;
import org.junit.*;
import org.junit.Assert.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.geom.Circle;

/**
 *
 * @author Heisenberg
 */
public class BulletTest {

    private ShootingEnemy turret;
    private Bullet bullet;
    int x1, x2, y1, y2;
    Shape hitboxArea;

    @BeforeClass
    public static void setUpClass() {
        System.out.println("----- Test Bullet class -----");
    }

    @Before
    public void initialize() throws SlickException, LWJGLException {
        /**
         * Initialize it with some values. I set as starting point x1,x2. The
         * target point I assume is located at 10, 10 wrt the origin. The
         * hitbixarea needs to be gib enough for the bullet to travel, otherwise
         * it will disappear and stop going in that direction.
         *
         * NOTE: A slickException must be thrown!
         */

        x1 = 0;
        y1 = 0;
        x2 = 10;
        y2 = 10;

        /**
         * IMPORTANT! To test this clas, I should first create a new display to
         * avoid getting the "No openGL context found" error!
         */
        try {
            Display.setDisplayMode(new DisplayMode(800, 600));
            Display.create();
        } catch (LWJGLException e) {
        }

        this.hitboxArea = new Circle(0, 0, 2000);
        this.turret = new ThreeBulletsTurret(x1, y1, hitboxArea, 1);
        this.bullet = new Bullet(x1, y1, x2, y2, hitboxArea, turret);
    }

    @After
    public void end() {
        /**
         * At the end of the execution, I have to delete the created Display to
         * avoid getting the error message "Only one LWJGL context may be
         * instantiated at any time.".
         */
        Display.destroy();
    }

    @Test
    public void test_ComputeVelocity_positive() {

        x1 = 0;
        y1 = 0;
        x2 = 10;
        y2 = 10;
        /**
         * Since I used those values for the 2 coordinates, I can already test
         * what the actual values must be, did by the ComputeVelocity method.
         * Since it's private, I cannot test it directly!
         */
        double x = x2 - x1;
        double y = y2 - y1;
        double distance = Math.sqrt(x * x + y * y);
        double expectedvX = x / Math.abs(distance) * bullet.getVelocity();
        double expectedvY = y / Math.abs(distance) * bullet.getVelocity();
        /**
         * Check that vX and vY are not Null.
         */
        Assert.assertNotNull("vX is null", bullet.vX);
        Assert.assertNotNull("vX is null", bullet.vY);
        /**
         * Check that vX and vY are computed correcly with the given example.
         */
        Assert.assertEquals(expectedvY, bullet.vY, 0);
        Assert.assertEquals(expectedvX, bullet.vX, 0);

    }

    @Test
    public void test_setVelocity_positive() {
        int x = (int) Math.abs(Math.random() * 100) + 1;
        Assert.assertTrue("This one sets positive values for the velocity", x > 0);

        Assert.assertNotNull(bullet);
        bullet.setVelocity(x);
        int velocity = bullet.getVelocity();

        Assert.assertTrue(velocity != 0);
        Assert.assertNotNull(velocity);
        Assert.assertEquals(x, velocity);
    }

    @Test
    public void test_setVelocity_negative() {
        int x = (int) -Math.abs(Math.random() * 100) + 1;
        Assert.assertTrue("This one sets negative values for the velocity", x < 0);

        Assert.assertNotNull(bullet);
        bullet.setVelocity(x);
        int velocity = bullet.getVelocity();

        Assert.assertTrue(velocity != 0);
        Assert.assertNotNull(velocity);
        Assert.assertEquals(x, velocity);
    }

    @Test
    public void test_setVelocity_zero() {
        int x = 0;
        Assert.assertTrue("This one sets velocty to 0", x == 0);

        Assert.assertNotNull(bullet);
        bullet.setVelocity(x);
        int velocity = bullet.getVelocity();

        Assert.assertNotNull(velocity);
        Assert.assertEquals(x, velocity);
        Assert.assertTrue(velocity == 0);
    }

    @Test
    public void test_getShape() throws SlickException {
        /**
         * Depending on how the Bullet is actually implemented, it returns a
         * shape of the bullet only if it's inside the hitboxArea. This means
         * that, since there's no Slick's Graphics i can pass to the render
         * method, I expect it to be null! When it will be actually called
         * during the game execution, the returned shape, if the bullet is
         * within the box, is not null.
         *
         */
        Shape hitbox = bullet.getShape();
        Assert.assertNull(hitbox);
    }
    
    @Test
    public void test_getDamage() {
        Integer damage = bullet.getDamage();
        Assert.assertTrue(damage>0);
        Assert.assertTrue(damage ==1);

    }
}
