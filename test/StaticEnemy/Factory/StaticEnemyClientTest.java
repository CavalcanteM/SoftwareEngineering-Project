package StaticEnemy.Factory;

import ShootingEnemy.ShootingEnemy;
import StaticEnemy.StaticDamage;
import java.util.ArrayList;
import org.junit.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class StaticEnemyClientTest {

    StaticEnemyClient client;
    TiledMap map;
    private static boolean setUpIsDone = false;
    int[] number_of_static_enemies = {14};

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
        client = new StaticEnemyClient(map, 10);
    }

    @Test
    public void getStaticEnemyListTest() throws SlickException {
        for (int i = 0; i < number_of_static_enemies.length; i++) {
            this.changelevel(1);

            ArrayList<StaticDamage> array = client.getStaticEnemyList();
            for (int j = 0; j < array.size(); j++) {
                Assert.assertTrue(array.get(j) instanceof StaticDamage);
            }
            
            Assert.assertTrue(number_of_static_enemies[i] == array.size());
        }

    }

}
