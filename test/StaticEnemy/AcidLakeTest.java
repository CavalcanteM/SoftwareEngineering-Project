package StaticEnemy;


import org.junit.*;
import static org.junit.Assert.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author Heisenberg
 */
public class AcidLakeTest {

    private AcidLake acid;
    private Shape hitbox;
    private int x = 0;
    private int y = 0;
    private int damage;

    @BeforeClass
    public static void setUpClass() {
        System.out.println("----- Test AcidLake class -----");
    }

    @Before
    public void initialize() {
        /**
         * Initialize it with some values.
         */
        acid = new AcidLake(x, y, 1);
    }

    @After
    public void end() {
    }

    @Test
    public void test_getHitbox() {
        this.hitbox = acid.getHitbox();

        Assert.assertNotNull("Hitbox returned null", hitbox);
        Assert.assertEquals(acid.getClass(), AcidLake.class);
        Assert.assertEquals(this.hitbox.getClass(), Rectangle.class);
    }
    
    @Test
    public void test_getHitbox_Height() {
        this.hitbox = acid.getHitbox();

        Assert.assertNotNull("Hitbox returned null", hitbox);
        Assert.assertEquals(acid.getClass(), AcidLake.class);
        Assert.assertEquals(this.hitbox.getClass(), Rectangle.class);
        Assert.assertTrue(hitbox.getHeight() == 20);
    }
        
    @Test
    public void test_getHitbox_Width() {
        this.hitbox = acid.getHitbox();

        Assert.assertNotNull("Hitbox returned null", hitbox);
        Assert.assertEquals(acid.getClass(), AcidLake.class);
        Assert.assertEquals(this.hitbox.getClass(), Rectangle.class);
        Assert.assertTrue(hitbox.getWidth() == 30);
    }


    @Test
    public void test_doDamage() {
        this.damage = acid.doDamage();

        Assert.assertNotNull(damage);
        Assert.assertTrue("Is positive", damage > 0);
        Assert.assertTrue("Is actually equal to 1", damage == 1);
    }
}
