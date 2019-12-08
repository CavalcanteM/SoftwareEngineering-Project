/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gravityslick;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author danya
 */
public class StaticLevelTest {
    
    public StaticLevelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getRtl method, of class Level.
     */
    @Test
    public void testGetRtl() {
        System.out.println("getRtl");
        Level instance = new Level();
        ArrayList<Shape> expResult = null;
        ArrayList<Shape> result = instance.getRtl();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of init method, of class Level.
     */
    @Test
    public void testInit() throws Exception {
        System.out.println("init");
        int score = 0;
        GameContainer gc = null;
        Shape player = null;
        Level instance = new Level();
        instance.init(gc, player, score);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of render method, of class Level.
     */
    @Test
    public void testRender() throws Exception {
        System.out.println("render");
        GameContainer gc = null;
        Graphics g = null;
        Level instance = new Level();
        instance.render(gc, g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
