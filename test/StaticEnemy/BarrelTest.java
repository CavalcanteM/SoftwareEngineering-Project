/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class BarrelTest {

    private Barrel barrel;
    private Shape hitbox;
    private int x = 0;
    private int y = 0;
    private int damage;

    @BeforeClass
    public static void setUpClass() {
        System.out.println("----- Test Barrel class -----");
    }

    @Before
    public void initialize() {
        /**
         * Initialize it with some values.
         */
        this.barrel = new Barrel(x, y, 1, 1);
    }

    @After
    public void end() {
    }

    @Test
    public void test_getHitbox() {
        this.hitbox = barrel.getHitbox();

        Assert.assertNotNull("Hitbox returned null", hitbox);
        Assert.assertEquals(barrel.getClass(), Barrel.class);
        Assert.assertEquals(this.hitbox.getClass(), Rectangle.class);
    }

    @Test
    public void test_getHitbox_Height() {
        this.hitbox = barrel.getHitbox();

        Assert.assertNotNull("Hitbox returned null", hitbox);
        Assert.assertEquals(barrel.getClass(), Barrel.class);
        Assert.assertEquals(this.hitbox.getClass(), Rectangle.class);
        Assert.assertTrue(hitbox.getHeight() == 40);
    }

    @Test
    public void test_getHitbox_Width() {
        this.hitbox = barrel.getHitbox();

        Assert.assertNotNull("Hitbox returned null", hitbox);
        Assert.assertEquals(barrel.getClass(), Barrel.class);
        Assert.assertEquals(this.hitbox.getClass(), Rectangle.class);
        Assert.assertTrue(hitbox.getWidth() == 30);
    }

    @Test
    public void test_doDamage() {
        this.damage = barrel.doDamage();

        Assert.assertNotNull(damage);
        Assert.assertTrue("Is positive", damage > 0);
        Assert.assertTrue("Is actually equal to 1", damage == 1);
    }
}
