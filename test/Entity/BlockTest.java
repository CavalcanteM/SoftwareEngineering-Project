package Entity;

import org.junit.*;
import org.newdawn.slick.geom.Shape;
import static org.junit.Assert.*;
import org.newdawn.slick.geom.Rectangle;

public class BlockTest {

    private Block instance;
    private Shape hitbox, genericRectangle;

    @BeforeClass
    public static void setUpClass() {
        System.out.println("----- Test Block class -----");
    }

    @Before
    public void initialize() {
        /**
         * Initialize it with some values
         */
        int x = 30, y = 40;
        instance = new Block(x, y);
        genericRectangle = new Rectangle(0, 0, 50, 50);
    }

    @After
    public void end() {

    }

    /**
     * Tests for the setHeightAndWidht metohd with all the possible cases
     */
    @Test
    public void test_setHeightAndWidth_Positive_Positive() {
        int x = 10, y = 10;

        instance.setHeightAndWidth(x, y);
        hitbox = instance.getHitBox();
        Assert.assertEquals((float) x, hitbox.getHeight(), 0);
        Assert.assertEquals((float) y, hitbox.getWidth(), 0);
    }

    @Test
    public void test_setHeightAndWidth_Positive_Zero() {
        int x = 10, y = 0;

        instance.setHeightAndWidth(x, y);
        hitbox = instance.getHitBox();
        Assert.assertEquals((float) x, hitbox.getHeight(), 0);
        Assert.assertEquals((float) y, hitbox.getWidth(), 0);
    }

    @Test
    public void test_setHeightAndWidth_Positive_Negative() {
        int x = 10, y = -10;

        instance.setHeightAndWidth(x, y);
        hitbox = instance.getHitBox();
        Assert.assertEquals((float) x, hitbox.getHeight(), 0);
        Assert.assertEquals((float) y, hitbox.getWidth(), 0);
    }

    @Test
    public void test_setHeightAndWidth_Zero_Positive() {
        int x = 0, y = 10;

        instance.setHeightAndWidth(x, y);
        hitbox = instance.getHitBox();
        Assert.assertEquals((float) x, hitbox.getHeight(), 0);
        Assert.assertEquals((float) y, hitbox.getWidth(), 0);
    }

    @Test
    public void test_setHeightAndWidth_Zero_Zero() {
        int x = 0, y = 0;

        instance.setHeightAndWidth(x, y);
        hitbox = instance.getHitBox();
        Assert.assertEquals((float) x, hitbox.getHeight(), 0);
        Assert.assertEquals((float) y, hitbox.getWidth(), 0);
    }

    @Test
    public void test_setHeightAndWidth_Zero_Negative() {
        int x = 0, y = -5;

        instance.setHeightAndWidth(x, y);
        hitbox = instance.getHitBox();
        Assert.assertEquals((float) x, hitbox.getHeight(), 0);
        Assert.assertEquals((float) y, hitbox.getWidth(), 0);
    }

    @Test
    public void test_setHeightAndWidth_Negative_Positive() {
        int x = -10, y = 10;

        instance.setHeightAndWidth(x, y);
        hitbox = instance.getHitBox();
        Assert.assertEquals((float) x, hitbox.getHeight(), 0);
        Assert.assertEquals((float) y, hitbox.getWidth(), 0);
    }

    @Test
    public void test_setHeightAndWidth_Negative_Negative() {
        int x = -10, y = -10;

        instance.setHeightAndWidth(x, y);
        hitbox = instance.getHitBox();
        Assert.assertEquals((float) x, hitbox.getHeight(), 0);
        Assert.assertEquals((float) y, hitbox.getWidth(), 0);
    }

    @Test
    public void test_setHeightAndWidth_Negative_Zero() {
        int x = -5, y = 0;

        instance.setHeightAndWidth(x, y);
        hitbox = instance.getHitBox();
        Assert.assertEquals((float) x, hitbox.getHeight(), 0);
        Assert.assertEquals((float) y, hitbox.getWidth(), 0);
    }

    /**
     * Tests for the getShape method.
     */
    @Test
    public void test_getHitBox() {
        instance.setHeightAndWidth(30, 30);
        hitbox = instance.getHitBox();
        Assert.assertNotNull("The returned hitbox should not be Null", hitbox);
        Assert.assertEquals("Returned hitbox must be a Shape", genericRectangle.getClass(), hitbox.getClass());
    }
}