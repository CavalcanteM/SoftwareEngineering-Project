package StaticEnemy;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author Heisenberg
 */
public class SpikeTest {

    private Spike spike;
    private Shape hitbox;
    private int x = 0;
    private int y = 0;
    private int damage;

    @BeforeClass
    public static void setUpClass() {
        System.out.println("----- Test Spike class -----");
    }

    @Before
    public void initialize() {
        /**
         * Initialize it with some values.
         */
        spike = new Spike(x, y, 1, 1);
    }

    @After
    public void end() {
    }
    
    @Test
    public void test_differentDamageAccordingToDifficulty(){
        this.spike = new Spike(0,0,2,1);
        Assert.assertTrue(spike.doDamage() == 10);
        
    }

    @Test
    public void test_getHitbox() {
        this.hitbox = spike.getHitbox();

        Assert.assertNotNull("Hitbox returned null", hitbox);
        Assert.assertEquals(Rectangle.class, this.hitbox.getClass());
    }

    @Test
    public void test_getHitbox_Height() {
        this.hitbox = spike.getHitbox();

        Assert.assertNotNull("Hitbox returned null", hitbox);
        Assert.assertEquals(this.hitbox.getClass(), Rectangle.class);
        Assert.assertTrue(hitbox.getHeight() == 20);
    }

    @Test
    public void test_getHitbox_Width() {
        this.hitbox = spike.getHitbox();

        Assert.assertNotNull("Hitbox returned null", hitbox);
        Assert.assertEquals(this.hitbox.getClass(), Rectangle.class);
        Assert.assertTrue(hitbox.getWidth() == 20);
    }

    @Test
    public void test_doDamage() {
        this.damage = spike.doDamage();

        Assert.assertNotNull(damage);
        Assert.assertTrue("Is positive", damage > 0);
        Assert.assertTrue("Is actually equal to 10, since i passed 2 as difficulty.", damage == 5);
    }
}
