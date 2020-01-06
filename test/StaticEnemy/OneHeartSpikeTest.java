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
public class OneHeartSpikeTest {

    private OneHeartSpike spike;
    private Shape hitbox;
    private int x = 0;
    private int y = 0;
    private int damage;

    @BeforeClass
    public static void setUpClass() {
        System.out.println("----- Test OneHeartSpike class -----");
    }

    @Before
    public void initialize() {
        /**
         * Initialize it with some values.
         */
        spike = new OneHeartSpike(x, y, 1);
    }

    @After
    public void end() {
    }

    @Test
    public void test_getHitbox() {
        this.hitbox = spike.getHitbox();

        Assert.assertNotNull("Hitbox returned null", hitbox);
        Assert.assertEquals(spike.getClass(), OneHeartSpike.class);
        Assert.assertEquals(this.hitbox.getClass(), Rectangle.class);
    }

    @Test
    public void test_getHitbox_Height() {
        this.hitbox = spike.getHitbox();

        Assert.assertNotNull("Hitbox returned null", hitbox);
        Assert.assertEquals(spike.getClass(), OneHeartSpike.class);
        Assert.assertEquals(this.hitbox.getClass(), Rectangle.class);
        Assert.assertTrue(hitbox.getHeight() == 20);
    }

    @Test
    public void test_getHitbox_Width() {
        this.hitbox = spike.getHitbox();

        Assert.assertNotNull("Hitbox returned null", hitbox);
        Assert.assertEquals(spike.getClass(), HalfHeartSpike.class);
        Assert.assertEquals(this.hitbox.getClass(), Rectangle.class);
        Assert.assertTrue(hitbox.getWidth() == 20);
    }

    @Test
    public void test_doDamage() {
        this.damage = spike.doDamage();

        Assert.assertNotNull(damage);
        Assert.assertTrue("Is positive", damage > 0);
        Assert.assertTrue("Is actually equal to 1", damage == 1);
    }
}
