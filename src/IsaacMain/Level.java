package IsaacMain;

import Entities.Entity.*;
import Entities.StaticDamage.*;
import Entities.Turret.Turret;
import IsaacMain.StaticEnemyFactory.StaticEnemyList;
import Entities.Entity.EntityClient;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 * This class has the scope of the definition of a level's enviroment.
 */
public class Level implements GalaxyComponent{

    private TiledMap map;
    private EntityClient entityClient;
    private ArrayList<Entity> blocks, rewards;
    private ArrayList<StaticDamage> spikes;
    private ArrayList<Turret> turret;
    private int score;
    private String name;
    private Points pts;
    private int index;
    private static final long serialversionUId = 1;
    
    public Level(String name, int score, int index){
        this.name = name;
        this.score = score;
        this.index = index;
    }
    
    public int getScore() {
        return score;
    }

    public TiledMap getMap() {
        return map;
    }

    public ArrayList<Turret> getTurret() {
        return turret;
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
     * @param player
     * @throws org.newdawn.slick.SlickException
     */
    public void init(GameContainer gc) throws SlickException {

        this.map = new TiledMap("\\src\\map\\Level_" + this.index + ".tmx");

        this.spikes = new StaticEnemyList(this.map).getStaticEnemyList();

        this.entityClient = new EntityClient(this.map);
        this.blocks = entityClient.getEntities("Walls");
        this.rewards = entityClient.getEntities("Rewards");

        //this.turret = new TurretFactory(this.map).getShootingEnemy();

        this.pts = new Points(rewards, score);
        this.pts.init();
        
        System.out.println(rewards.size());
    }

    public void update(GameContainer gc, int delta) throws SlickException {
        if (!this.pts.iterator().hasNext()) {
            gc.pause();
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
        map.render(0, 0, map.getLayerIndex("Walls"));
        map.render(0, 0, map.getLayerIndex("StaticEnemies"));
        if(pts.iterator().hasNext()){
            pts.render(gc, g);
        }
        g.setColor(Color.white);
        g.drawString(this.name, 850, 5);
    }

    private int readFromFile() {
        return 1;
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
