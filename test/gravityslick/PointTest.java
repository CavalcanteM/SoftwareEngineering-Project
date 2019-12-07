/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gravityslick;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author Marco
 */
public class PointTest {
    
    private Points p;
    
    public PointTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("----- Test Window class -----");
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws SlickException{
        //this.p = new Point(10, 20, 30, 40);
    }
    
    @After
    public void tearDown() {
    }

//    /**
//     * Test of getL method, of class Point.
//     */
//    @Test
//    public void testGetL() {
//        System.out.println("getL");
//        int expResult = 10;
//        int result = p.getL();
//        assertSame(expResult, result);
//    }
//
//    /**
//     * Test of setL method, of class Point.
//     */
//    @Test
//    public void testSetL() {
//        System.out.println("setL");
//        int expResult = 15;
//        p.setL(expResult);
//        assertSame(expResult, p.getL());
//    }
//
//    /**
//     * Test of getH method, of class Point.
//     */
//    @Test
//    public void testGetH() {
//        System.out.println("getH");
//        int expResult = 20;
//        int result = p.getH();
//        assertSame(expResult, result);
//    }
//
//    /**
//     * Test of setH method, of class Point.
//     */
//    @Test
//    public void testSetH() {
//        System.out.println("setH");
//        int expResult = 15;
//        p.setH(expResult);
//        assertSame(expResult, p.getH());
//    }
//
//    /**
//     * Test of getX method, of class Point.
//     */
//    @Test
//    public void testGetX() {
//        System.out.println("getX");
//        int expResult = 30;
//        int result = p.getX();
//        assertSame(expResult, result);
//    }
//
//    /**
//     * Test of setX method, of class Point.
//     */
//    @Test
//    public void testSetX() {
//        System.out.println("setX");
//        int expResult = 15;
//        p.setX(expResult);
//        assertSame(expResult, p.getX());
//    }
//
//    /**
//     * Test of getY method, of class Point.
//     */
//    @Test
//    public void testGetY() {
//        System.out.println("getY");
//        int expResult = 40;
//        int result = p.getY();
//        assertSame(expResult, result);
//    }
//
//    /**
//     * Test of setY method, of class Point.
//     */
//    @Test
//    public void testSetY() {
//        System.out.println("setY");
//        int expResult = 15;
//        p.setY(expResult);
//        assertSame(expResult, p.getY());
//    }
//
//    /**
//     * Test of getPoint method, of class Point.
//     */
//    @Test
//    public void testGetPoint() {
//        System.out.println("getPoint");
//        p.init();
//        Shape expResult = new Rectangle(10, 20, 30, 40);
//        Shape result = p.getPoint();
//        assertTrue((expResult.getMinX() == result.getMinX()) && (expResult.getMinY() == result.getMinY())
//        && (expResult.getCenterX() == result.getCenterX()) && (expResult.getCenterY() == result.getCenterY()));
//    }
//
//    /**
//     * Test of init method, of class Point.
//     */
//    @Test
//    public void testInit() {
//        System.out.println("init");
//       
//        p.init();
//        Shape expResult = new Rectangle(10, 20, 30, 40);
//        assertTrue((expResult.getMinX() == p.getPoint().getMinX()) && (expResult.getMinY() == p.getPoint().getMinY())
//        && (expResult.getCenterX() == p.getPoint().getCenterX()) && (expResult.getCenterY() == p.getPoint().getCenterY()));
//    }

//    /**
//     * Test of update method, of class Point.
//     */
//    @Test
//    public void testUpdate() throws Exception {
//        System.out.println("update");
//        GameContainer gc = null;
//        int delta = 0;
//        Point instance = null;
//        instance.update(gc, delta);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of render method, of class Point.
//     */
//    @Test
//    public void testRender() {
//        System.out.println("render");
//        GameContainer gc = null;
//        Graphics g = null;
//        Point instance = null;
//        instance.render(gc, g);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
}
