/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShootingEnemy.Factory;

import ShootingEnemy.HiddenThreeShotTurret;
import ShootingEnemy.ShootingEnemy;
import static java.lang.Math.ceil;
import java.util.ArrayList;
import java.util.Collections;
import org.junit.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;

public class ShootingEnemyClientTest {

    ShootingEnemyClient client;
    TiledMap map;
    private static boolean setUpIsDone = false;
    int[] number_of_turrets = {4};

    @Before
    public void setUp() {
        if (setUpIsDone) {
            return;
        }
        try {
            Display.setDisplayMode(new DisplayMode(800, 600));
            Display.create();
        } catch (LWJGLException ex) {

        }
        setUpIsDone = true;
    }

    public void changelevel(int i) throws SlickException {
        this.map = new TiledMap("\\src\\map_level\\Level_" + i + ".tmx");
        client = new ShootingEnemyClient(map, 10);
    }

    @Test
    public void getList() throws SlickException {
        for (int i = 0; i < number_of_turrets.length; i++) {
            this.changelevel(1);
            
            ArrayList<ShootingEnemy> array = client.getList();
            for(int j = 0; j<array.size();j++)
                Assert.assertTrue(array.get(j) instanceof ShootingEnemy);
            Assert.assertTrue(array.size() == number_of_turrets[i]);
        }

    }

    @Test
    public void calculateHitboxAreaTest() {
       //HARD TO TEST USING A METHOD, EASY TO CHECK IF THE RESULTING
       //HITBOXAREA IS ACTUALLY THE ONE THAT IS PROPOSED IN THE TILEDMAP OBJECT.
}
}