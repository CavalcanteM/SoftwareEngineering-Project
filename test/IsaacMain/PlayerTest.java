/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IsaacMain;

import static java.lang.Math.signum;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.GameContainer;


/**
 *
 * @author danya
 */
public class PlayerTest {
    
    private Player player;
    private Level level;
    
    public PlayerTest() {
     
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("----- Test Player class -----");
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
    
    @Before
    public void setUp() {
        level = new Level();
        player = Player.getPlayerInstance();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getLEFT method, of class Player.
     */
    @Test
    public void testGetLEFT() {
        System.out.println("getLEFT");
        int expResult = -1;
        int result = player.getLEFT();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRIGHT method, of class Player.
     */
    @Test
    public void testGetRIGHT() {
        System.out.println("getRIGHT");
        int expResult = 1;
        int result = player.getRIGHT();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDashValue method, of class Player.
     */
    @Test
    public void testGetDashValue() {
        System.out.println("getDashValue");
        int expResult = 10;
        int result = player.getDashValue();         // accessing a static method, modify player dashvalue;
        assertEquals(expResult, result);
    }

    /**
     * Test of getGravity method, of class Player.
     */
    @Test
    public void testGetGravity() {
        System.out.println("getGravity");
        float expResult = 0.5F;
        float result = player.getGravity();         // accessing a static method, modify player gravity;
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getIterations method, of class Player.
     */
    @Test
    public void testGetIterations() {
        System.out.println("getIterations");
        float expResult = 20F;
        float result = player.getIterations();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getPlayer method, of class Player.
     */
    @Test
    public void testGetPlayer() {
        System.out.println("getPlayer");
        Shape expResult = new Rectangle(200, 200, 29, 59);
        player.setPlayer(expResult);
        Shape result = player.getPlayer();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSpeed method, of class Player.
     */
    @Test
    public void testGetSpeed() {
        System.out.println("getSpeed");
        float expResult = 5F;
        float result = player.getSpeed();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getvX method, of class Player.
     */
    @Test
    public void testGetvX() {
        System.out.println("getvX");
        float expResult = 0.0F;
        float result = player.getvX();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getvY method, of class Player.
     */
    @Test
    public void testGetvY() {
        System.out.println("getvY");
        float expResult = 0.0F;
        float result = player.getvY();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setDashValue method, of class Player.
     */
    @Test
    public void testSetDashValue() {
        System.out.println("setDashValue");
        int dashValue = 0;
        player.setDashValue(0);
        assertEquals(dashValue, player.getDashValue());
    }
    
    /**
     * Test of setGravity method, of class Player.
     */
    @Test
    public void testSetGravity() {
        System.out.println("setGravity");
        float gravity = 0.5F;
        player.setGravity(0.5F);
        assertEquals(gravity, player.getGravity(), 0.0);
    }

    /**
     * Test of setIterations method, of class Player.
     */
    @Test
    public void testSetIterations() {
        System.out.println("setIterations");
        float iterations = 20F;
        player.setIterations(20F);
        assertEquals(iterations, player.getIterations(), 0.0);
    }
    
    /**
     * Test of setPlayer method, of class Player.
     */
    @Test
    public void testSetPlayer() {
        System.out.println("setPlayer");
        Shape shape = new Rectangle(200, 200, 30, 60);
        player.setPlayer(shape);
        assertSame(player.getPlayer(), shape);
    }

    /**
     * Test of setSpeed method, of class Player.
     */
    @Test
    public void testSetSpeed() {
        System.out.println("setSpeed");
        float expSpeed = 0.0F;
        player.setSpeed(0);
        assertEquals(expSpeed, player.getSpeed(), 0.0);
    }

    /**
     * Test of setvX method, of class Player.
     */
    @Test
    public void testSetvX() {
        System.out.println("setvX");
        float expvX = 0.0F;
        player.setvX(0.0F);
        assertEquals(expvX, player.getvX(), 0.0);
    }

    /**
     * Test of setvY method, of class Player.
     */
    @Test
    public void testSetvY() {
        System.out.println("setvY");
        float expvY = 0.0F;
        player.setvY(0.0F);
        assertEquals(expvY, player.getvY(), 0.0);
    }

    /**
     * Test of init method, of class Player.
     */
//    @Test
//    public void testInit() throws Exception {
//        System.out.println("init");
//        GameContainer gc = null;
//        Player instance = null;
//        instance.init(gc);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of update method, of class Player.
     */
//    @Test
//    public void testUpdate() throws Exception {
//        System.out.println("update");
//        GameContainer gc = null;
//        int delta = 0;
//        Player instance = null;
//        instance.update(gc, delta);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of render method, of class Player.
//     */
//    @Test
//    public void testRender() throws Exception {
//        System.out.println("render");
//        GameContainer gc = null;
//        Graphics g = null;
//        Player instance = null;
//        instance.render(gc, g);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of changeGravity method, of class Player.
//     */
//    @Test
//    public void testChangeGravity() {
//        System.out.println("changeGravity");
//        
//        /* To guarantee statement, branch, condition and path coverage we need two test case:
//         * 1) there is a collision with an object of the current level during the transition, so
//         *    the robot has to block
//         * 2) there is not a collision, so the transition can continue
//         */
//        // First case
//        player.setGravity(0.5F);
//        float expResult = -0.5F;
//        float result = player.changeGravity(signum(player.getGravity()));
//        assertEquals(expResult, result, 0.0);
//        
//        // Second case
//        expResult = 0.5f;
//        result = player.changeGravity(signum(player.getGravity()));
//        System.out.println(result);
//        assertEquals(expResult, result, 0.0);
//    }

    /**
     * Test of dash method, of class Player.
     */
    @Test
    public void testDash() {
        System.out.println("dash");
        player.setPlayer(new Rectangle(200, 200,30,60));
        int dir = 1;    // the direction of the dash
        
        // Check the dash on the right
        float vx = player.getvX();
        player.dash(dir);
        float vx1 = player.getvX();
        assertTrue(vx1 > vx); 
        
        // Check the dash on the left
        player.dash(-dir);
        float vx2 = player.getvX();
        assertTrue(vx2 < vx1);
    }
    
}
