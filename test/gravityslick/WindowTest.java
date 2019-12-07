/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gravityslick;

import org.junit.*;
import static org.junit.Assert.*;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Marco
 */
public class WindowTest {
    
    private Window window;
    
    public WindowTest() {
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
        window = Window.getWindow();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getWindow method, of class Window.
     * @throws org.newdawn.slick.SlickException
     */
    @Test
    public void testGetWindow() throws SlickException {
        System.out.println("getWindow");
        Window expResult = Window.getWindow();
        assertSame(expResult, window);
    }

    /**
     * Test of getWidth method, of class Window.
     */
    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        int expResult = 960;
        window.setWidth(expResult);
        int result = window.getWidth();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHeight method, of class Window.
     */
    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        int expResult = 720;
        window.setHeight(expResult);
        int result = window.getHeight();
        assertEquals(expResult, result);
    }

    /**
     * Test of isFullscreen method, of class Window.
     */
    @Test
    public void testIsFullscreen() {
        System.out.println("isFullscreen");
        boolean expResult = false;
        window.setFullscreen(expResult);
        boolean result = window.isFullscreen();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFrameRate method, of class Window.
     */
    @Test
    public void testGetFrameRate() {
        System.out.println("getFrameRate");
        int expResult = 60;
        window.setFrameRate(expResult);
        int result = window.getFrameRate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setWidth method, of class Window.
     */
    @Test
    public void testSetWidth() {
        System.out.println("setWidth");
        int widht = 100;
        window.setWidth(widht);
        assertEquals(widht, window.getWidth());
    }

    /**
     * Test of setHeight method, of class Window.
     */
    @Test
    public void testSetHeight() {
        System.out.println("setHeight");
        int height = 100;
        window.setHeight(height);
        assertEquals(height, window.getHeight());
    }

    /**
     * Test of setFullscreen method, of class Window.
     */
    @Test
    public void testSetFullscreen() {
        System.out.println("setFullscreen");
        boolean fullscreen = true;
        window.setFullscreen(fullscreen);
        assertEquals(fullscreen, window.isFullscreen());
    }

    /**
     * Test of setFrameRate method, of class Window.
     */
    @Test
    public void testSetFrameRate() {
        System.out.println("setFrameRate");
        int frameRate = 100;
        window.setFrameRate(frameRate);
        assertEquals(frameRate, window.getFrameRate());
    }

    /**
     * Test of showWindow method, of class Window.
     * This test expects an exception because the measures of the game screen 
     * do not allow to set the fullscreen=true
     */
    @Test(expected = SlickException.class)
    public void testShowWindowException() throws Exception {
        System.out.println("showWindow");
        window.setHeight(10000);
        window.setWidth(10000);
        window.setFullscreen(true);
        window.showWindow();
    }
    
}
