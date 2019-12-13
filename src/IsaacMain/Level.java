package IsaacMain;

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
public class Level {

    private TiledMap map;
    private EntityClient entityClient;
    private ArrayList<Entity> blocks, rewards;
    private ArrayList<StaticDamage> spikes;
    private ArrayList<ShootingEnemy> turrets;
    private int score;
    
    private Graphics g;

    public Graphics getG() {
        return g;
    }

    private Points pts;

    public int getScore() {
        return score;
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
     * @param player
     * @throws org.newdawn.slick.SlickException
     */
    public void init(GameContainer gc, int score) throws SlickException {

        this.map = new TiledMap("\\src\\map\\Level_" + readFromFile() + ".tmx");

        this.spikes = new StaticEnemyList(this.map).getStaticEnemyList();

        this.entityClient = new EntityClient(this.map);
        this.blocks = entityClient.getEntities("Walls");
        this.rewards = entityClient.getEntities("Rewards");
        
        //Create an array list of turrets calling the List creator
        this.turrets = new ShootingEnemyList(this.map).getList();
        
        this.score = score;

        this.pts = new Points(rewards, score);
        this.pts.init();
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
    public void render(GameContainer gc, Graphics g) throws SlickException {
        this.g=g;
        map.render(0, 0, map.getLayerIndex("Background"));
        map.render(0, 0, map.getLayerIndex("Walls"));
        map.render(0, 0, map.getLayerIndex("StaticEnemies"));
        map.render(0,0, map.getLayerIndex("Turrets"));
        map.render(0,0, map.getLayerIndex("TurretsHitbox"));
        if(pts.iterator().hasNext()){
            pts.render(gc, g);
        }
      
        if(turrets != null)
            for(int i=0; i<turrets.size(); i++){
                turrets.get(i).render(g);
            }
    }

    private int readFromFile() {
        return 1;
    }
}
