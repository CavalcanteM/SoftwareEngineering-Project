package IsaacMain;

import IsaacMain.ThrowersFactory.*;
import Entities.Throwers.*;
import Entities.Entity.*;
import Entities.StaticDamage.*;
import IsaacMain.StaticEnemyFactory.StaticEnemyList;
import Entities.Entity.EntityClient;
import Entities.Turret.ShootingEnemy;
import IsaacMain.ShootingEnemyFactory.ShootingEnemyList;
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
    private Thrower tr;
    private EntityClient entityClient;
    private ClientThrowersFactory ctf;
    private ArrayList<Entity> blocks, rewards;
    private ArrayList<StaticDamage> spikes;
    private ArrayList<ShootingEnemy> turrets;
    private ArrayList<Thrower> flameThrowers;
    private ArrayList<Thrower> laserThrowers;
    private int score;
    private String name;
    private Points pts;
    private int index;
    private Graphics g;
    private static final long serialversionUId = 1;
   
    public Level(String name, int score, int index){
        this.name = name;
        this.score = score;
        this.index = index;
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
        this.map = new TiledMap("\\src\\map\\Level_" + this.index + ".tmx");
        this.spikes = new StaticEnemyList(this.map).getStaticEnemyList();
        this.entityClient = new EntityClient(this.map);
        this.blocks = entityClient.getEntities("Walls");
        this.rewards = entityClient.getEntities("Rewards");
        this.ctf = new ClientThrowersFactory(this.map);
        this.flameThrowers = ctf.getEntities("Fire");
        this.laserThrowers = ctf.getEntities("Laser");
        //Create an array list of turrets calling the List creator
        this.turrets = new ShootingEnemyList(this.map).getList();
        this.pts = new Points(rewards, score);
        this.pts.init();
    }
    
    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        if (!this.pts.iterator().hasNext()) {
            gc.pause();
        }
        for(Thrower t: flameThrowers){
            t.update(delta);
        }
        for(Thrower t: laserThrowers){
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
        map.render(0, 0, map.getLayerIndex("Turrets"));
        //map.render(0, 0, map.getLayerIndex("TurretsHitbox"));
        
        if(pts.iterator().hasNext()){
            pts.render(gc, g);
        }
        for(Thrower t: flameThrowers){
            t.render();
        }
        for(Thrower t: laserThrowers){
            t.render();
        }
        if(turrets != null){
            for(int i=0; i<turrets.size(); i++){
                turrets.get(i).render(g);
            }
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
