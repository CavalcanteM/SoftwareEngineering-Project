package IsaacMain;

import Throwers.FlameThrower;
import Throwers.Thrower;
import Throwers.LaserThrower;
import Entity.Block;
import Entity.Entity;
import Entity.Reward;
import Entity.Upgrade;
import ShootingEnemy.ThreeBulletsTurret;
import StaticEnemy.HalfHeartSpike;
import StaticEnemy.StaticDamage;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class CollisionManagerTest {
    private ArrayList<Entity> blocks;
    private Points pts;
    private Shape reward;
    private ArrayList<StaticDamage> spikes;
    private ArrayList<Shape> players;
    private ArrayList<Thrower> t;
    private ThreeBulletsTurret tbt;
    private Entity upgrade;
    
    public CollisionManagerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("----- Test CollisionManager class -----");
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Here I create the fake objects in the map
     */

    @Before
    public void setUp() throws SlickException {/**
         * IMPORTANT! To test this clas, I should first create a new display to
         * avoid getting the "No openGL context found" error!
         */
        try {
            Display.setDisplayMode(new DisplayMode(800, 600));
            Display.create();
        } catch (LWJGLException e) {
        }
        players = new ArrayList<Shape>();
        players.add(new Rectangle(200, 200, 50, 50));
        players.add(new Rectangle(300, 300, 50, 50));
        players.add(new Rectangle(400, 400, 50, 50));
        players.add(new Rectangle(500, 500, 50, 50));
        players.add(new Rectangle(700, 700, 50, 50));
        players.add(new Rectangle(900, 900, 50, 50));
        players.add(new Rectangle(15, 15, 50, 50));
        players.add(new Rectangle(1100, 1100, 50, 50));
        ArrayList<Entity> points = new ArrayList<Entity>();
        points.add(new Reward(200,249));
        pts = new Points(points,3);
        reward = points.get(0).getHitBox();
        spikes = new ArrayList<StaticDamage>();
        spikes.add(new HalfHeartSpike(329,300,1));
        blocks = new ArrayList<Entity>();
        blocks.add(new Block(449,400));
        t = new ArrayList<Thrower>();
        t.add(new LaserThrower(700,779,2,1,1));
        t.add(new FlameThrower(900,979,2,1,1));
        this.tbt = new ThreeBulletsTurret(0,0, new Rectangle(1000,1000,50,50), 1);
        this.upgrade = new Upgrade(1149, 1100);
    }

    @After
    public void tearDown() {/**
         * At the end of the execution, I have to delete the created Display to
         * avoid getting the error message "Only one LWJGL context may be
         * instantiated at any time.".
         */
        Display.destroy();
    }

    /**
     * Test of collidesWith method, of class CollisionManager.
     * This method test if collidesWith correctly collides with a reward
     */
    @Test
    public void testCollidesWith() {
        CollisionManager collision = new CollisionManager();
        collision.setParameters(blocks, pts, spikes, players.get(0), reward, t, tbt, upgrade);
        System.out.println("collidesWith");
        boolean result = collision.collidesWith();
        assertTrue(!result && collision.test1 && !collision.test2 && !collision.test3 && !collision.test4 && !collision.test5 && !collision.test6);
    }

    /**
     * Test of collidesWith method, of class CollisionManager.
     * This method test if collidesWith correctly collides with a spike
     */
    @Test
    public void testCollidesWith2(){
        CollisionManager collision = new CollisionManager();
        collision.setParameters(blocks, pts, spikes, players.get(1), reward, t, tbt, upgrade);
        System.out.println("collidesWith2");
        boolean result = collision.collidesWith();
        assertTrue(!result && !collision.test1 && collision.test2 && !collision.test3 && !collision.test4 && !collision.test5 && !collision.test6);
    }

    /**
     * Test of collidesWith method, of class CollisionManager.
     * This method test if collidesWith correctly collides with a block
     */
    @Test
    public void testCollidesWith3(){
        CollisionManager collision = new CollisionManager();
        collision.setParameters(blocks, pts, spikes, players.get(2), reward, t, tbt, upgrade);
        System.out.println("collidesWith3");
        boolean result = collision.collidesWith();
        assertTrue(result && !collision.test1 && !collision.test2 && !collision.test3 && !collision.test4 && !collision.test5 && !collision.test6);
    }

    /**
     * Test of collidesWith method, of class CollisionManager.
     * This method test if collidesWith correctly works when the player doesn't collides
     */
    @Test
    public void testCollidesWith4(){
        CollisionManager collision = new CollisionManager();
        collision.setParameters(blocks, pts, spikes, players.get(3), reward, t, tbt, upgrade);
        System.out.println("collidesWith4");
        boolean result = collision.collidesWith();
        assertTrue(!result && !collision.test1 && !collision.test2 && !collision.test3 && !collision.test4 && !collision.test5 && !collision.test6);
    }


    @Test
    public void testCollidesWith5(){
        CollisionManager collision = new CollisionManager();
        collision.setParameters(blocks, pts, spikes, players.get(4), reward, t, tbt, upgrade);
        System.out.println("collidesWith5");
        boolean result = collision.collidesWith();
        assertTrue(!result && !collision.test1 && !collision.test2 && collision.test3 && !collision.test4 && !collision.test5 && !collision.test6);
    }

    /*
        To do this test is necessary to comment the part of the code of LaserThrower
        in witch is initialized the image
    */

    @Test
    public void testCollidesWith6(){
        CollisionManager collision = new CollisionManager();
        collision.setParameters(blocks, pts, spikes, players.get(5), reward, t, tbt, upgrade);
        System.out.println("collidesWith6");
        boolean result = collision.collidesWith();
        assertTrue(!result && !collision.test1 && !collision.test2 && !collision.test3 && collision.test4 && !collision.test5 && !collision.test6);
    }
    
    @Test
    public void testCollidesWith7(){
        CollisionManager collision = new CollisionManager();
        collision.setParameters(blocks, pts, spikes, players.get(6), reward, t, tbt, upgrade);
        System.out.println("collidesWith7");
        boolean result = collision.collidesWith();
        assertTrue(result && !collision.test1 && !collision.test2 && !collision.test3 && !collision.test4 && collision.test5 && !collision.test6);
    }
    
    @Test
    public void testCollidesWith8(){
        CollisionManager collision = new CollisionManager();
        collision.setParameters(blocks, pts, spikes, players.get(7), reward, t, tbt, upgrade);
        System.out.println("collidesWith8");
        boolean result = collision.collidesWith();
        assertTrue(!result && !collision.test1 && !collision.test2 && !collision.test3 && !collision.test4 && !collision.test5 && collision.test6);
    }
}
