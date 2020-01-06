package Entity;

import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 *
 * @author Heisenberg
 */
public class EntityClientTest {
    /**
     * WARNING! WARNING!
     * Remember to update the correct number of levels to test!
     * This fails because the levels next to 4 does not have the upgrades layer.
     * If you don't read me you will never fix this problem.
     */
    
    private int number_of_levels = 9;

    private TiledMap map;
    private EntityClient client;
    private String[] layers = new String[]{"Walls", "Rewards", "Upgrades"};
    private ArrayList<Entity> list = new ArrayList<>();

    @BeforeClass
    public static void setUpClass() {
        System.out.println("----- Test EntityClient class -----");
    }

    @Before
    public void initialize() throws SlickException {
        /**
         * IMPORTANT! To test this clas, I should first create a new display to
         * avoid getting the "No openGL context found" error!
         */
        try {
            Display.setDisplayMode(new DisplayMode(800, 600));
            Display.create();
        } catch (LWJGLException e) {
        }

        /**
         * Initialize one of the maps (the first one, just as test). Then I will
         * test it will all the possible maps.
         */
        map = new TiledMap("\\src\\map_level\\Level_1.tmx");
        client = new EntityClient(map);
    }

    @After
    public void end() {
        /**
         * At the end of the execution, I have to delete the created Display to
         * avoid getting the error message "Only one LWJGL context may be
         * instantiated at any time.".
         */
        Display.destroy();
    }

    @Test
    public void test_getEntities_Walls_layer() throws SlickException {
        /**
         * I know that for sure there're 10 levels in map_levels folder (at the
         * thime this test was writte. In case more levels are added, the value
         * needs to be modified according to the number of levels!
         */
        String layer = layers[0];

        for (int i = 1; i <= number_of_levels; i++) {
            /**
             * Creates a new client for each level in the game.
             */
            this.map = new TiledMap("\\src\\map_level\\Level_" + i + ".tmx");
            this.client = new EntityClient(map);
            Assert.assertEquals("The client cannot be instantiated", client.getClass(), EntityClient.class);

            list = client.getEntities(layer);

            Assert.assertNotNull("The returned ArrayList is null", list);
            Assert.assertEquals("It's not an ArrayList", ArrayList.class, list.getClass());
            System.out.println("Testing Level_" + i + " for the Walls layer accomplished correctly.");
        }
    }

    @Test
    public void test_getEntities_Rewards_layer() throws SlickException {
        /**
         * I know that for sure there're 10 levels in map_levels folder (at the
         * thime this test was writte. In case more levels are added, the value
         * needs to be modified according to the number of levels!
         */
        String layer = layers[1];

        for (int i = 1; i <= number_of_levels; i++) {
            /**
             * Creates a new client for each level in the game.
             */
            this.map = new TiledMap("\\src\\map_level\\Level_" + i + ".tmx");
            this.client = new EntityClient(map);
            Assert.assertEquals("The client cannot be instantiated", client.getClass(), EntityClient.class);

            list = client.getEntities(layer);

            Assert.assertNotNull("The returned ArrayList is null", list);
            Assert.assertEquals("It's not an ArrayList", ArrayList.class, list.getClass());
            System.out.println("Testing Level_" + i + " for the Rewards layer accomplished correctly.");
        }

    }
    
        @Test
    public void test_getEntities_Upgrades_layer() throws SlickException {
        /**
         * I know that for sure there're 10 levels in map_levels folder (at the
         * thime this test was writte. In case more levels are added, the value
         * needs to be modified according to the number of levels!
         */
        String layer = layers[2];

        for (int i = 1; i <= number_of_levels; i++) {
            /**
             * Creates a new client for each level in the game.
             */
            this.map = new TiledMap("\\src\\map_level\\Level_" + i + ".tmx");
            this.client = new EntityClient(map);
            Assert.assertEquals("The client cannot be instantiated", client.getClass(), EntityClient.class);

            list = client.getEntities(layer);

            Assert.assertNotNull("The returned ArrayList is null", list);
            Assert.assertEquals("It's not an ArrayList", ArrayList.class, list.getClass());
            System.out.println("Testing Level_" + i + " for the Ugrades layer accomplished correctly.");
        }

    }
}
