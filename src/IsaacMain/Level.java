package IsaacMain;

import StaticEnemy.StaticDamage;
import ThrowersFactory.ThrowersClient;
import Throwers.Thrower;
import Entity.Entity;
import StaticEnemyFactory.StaticEnemyClient;
import Entity.EntityClient;
import ShootingEnemies.ShootingEnemy;
import ShootingEnemyFactory.ShootingEnemyClient;
import static java.lang.Math.floor;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 * This class has the scope of the definition of a level's enviroment.
 */
public class Level implements GalaxyComponent {

    private TiledMap map;
    private Thrower tr;
    private ArrayList<Entity> blocks, rewards;
    private ArrayList<StaticDamage> spikes;
    private ArrayList<ShootingEnemy> turrets;

    private ArrayList<Thrower> flameThrowers, laserThrowers;
    private int score, index, difficulty;
    private String name;
    private Points pts;
    private Graphics g;
    private static final long serialversionUId = 1;

    public Level(String name, int score, int index, int difficulty) {
        this.name = name;
        this.score = score;
        this.index = index;
        this.difficulty = difficulty;
    }

    public Graphics getG() {
        return g;
    }

    public int getScore() {
        return score;
    }

    public ArrayList<Thrower> getLaserThrowers() {
        return laserThrowers;
    }

    public ArrayList<Thrower> getThrowers() {
        return flameThrowers;
    }

    public TiledMap getMap() {
        return map;
    }

    public ArrayList<ShootingEnemy> getShootingEnemy() {
        return turrets;
    }

    public ArrayList<Entity> getRewards() {
        return rewards;
    }

    public ArrayList<Entity> getBlock() {
        return blocks;
    }

    public ArrayList<StaticDamage> getSpikes() {
        return spikes;
    }

    public Points getPts() {
        return pts;
    }

    /**
     * Is a sort of constructor for this class
     *
     * @param gc
     * @throws org.newdawn.slick.SlickException
     */
    @Override
    public void init(GameContainer gc) throws SlickException {
        /**
         * difficulty parameter passed to every concrete factory to generate
         * objects that vary based on the difficulty level.
         */
        this.map = new TiledMap("\\src\\map\\Level_" + this.index + ".tmx");
        this.spikes = new StaticEnemyClient(this.map, this.difficulty).getStaticEnemyList();
        EntityClient entityClient = new EntityClient(this.map);
        this.blocks = entityClient.getEntities("Walls");
        this.rewards = entityClient.getEntities("Rewards");
        ThrowersClient throwers_client = new ThrowersClient(this.map, this.difficulty);
        this.flameThrowers = throwers_client.getThrowers("Fire");
        this.laserThrowers = throwers_client.getThrowers("Laser");
        //Create an array list of turrets calling the List creator
        this.turrets = new ShootingEnemyClient(this.map, this.difficulty).getList();
        this.pts = new Points(rewards, score);
        this.pts.init();
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        if (!this.pts.iterator().hasNext()) {
            gc.pause();
        }
        for (Thrower t : flameThrowers) {
            t.update(delta);
        }
        for (Thrower t : laserThrowers) {
            t.update(delta);
        }
    }

    /**
     * This method is invoked only by GravitySlick.render() and has to render
     * the map
     *
     * @param gc
     * @param g
     * @throws org.newdawn.slick.SlickException
     */
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        map.render(0, 0, map.getLayerIndex("Background"));
        map.render(0, 0, map.getLayerIndex("Laser"));
        map.render(0, 0, map.getLayerIndex("Walls"));
        map.render(0, 0, map.getLayerIndex("StaticEnemies"));
        map.render(0, 0, map.getLayerIndex("Fire"));

        for (ShootingEnemy turret : this.turrets) {
            int x = (int) floor(turret.getHitbox().getX() - 10);
            int y = (int) floor(turret.getHitbox().getY() - 10);
            map.render(x, y, x / 30, y / 30, 1, 1, map.getLayerIndex("Turrets"), false);
        }
        /**
         * I only have to render the picked x turrets that are present in the
         * "turrets" ArrayList and not all the one present in the actual map. I
         * will only render x of them! NOTE: I have to offset the obtained
         * hitbox location because of its shape:
         * +-------------+
         * |             |
         * |    XXXXX    |
         * |    XXXXX    |
         * |    XXXXX    |
         * |             |
         * +-------------+
         */
        if (pts.iterator().hasNext()) {
            pts.render(gc, g);
        }
        for (Thrower t : flameThrowers) {
            t.render();
        }
        for (Thrower t : laserThrowers) {
            t.render();
        }

        for (ShootingEnemy turret : turrets) {
            turret.render(g, map);
        }

        g.setColor(Color.white);
        g.drawString(this.name, 850, 5);
    }

    @Override
    public void add(GalaxyComponent galaxyComponent) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<GalaxyComponent> getChildren() {
        return null;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GalaxyComponent getChild(int index) {
        return null;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
