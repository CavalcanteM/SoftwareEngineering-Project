package gravityslick;


import Entities.*;
import Entities.Spikes.StaticDamage;
import Entities.Turret.Turret;
import gravityslick.Factory.RewardFactory;
import gravityslick.Factory.WallsLayerFactory;
import gravityslick.StaticDamageFactory.*;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;

/**  
 * This class has the scope of the definition of a level's
 * enviroment.
 */
public class Level {
    
    private TiledMap map;
    private ArrayList<Entity> Walls, Rewards;
    private ArrayList<StaticDamage> Spikes;
    private ArrayList<Turret> Turret;
    private int score;
    
     public int getScore() {
        return score;
    }
    
    public TiledMap getMap() {
        return map;
    } 
    
    public ArrayList<Turret> getTurret() {
        return Turret;
    }
    
    public ArrayList<Entity> getBolts() {
        return Rewards;
    }
   
       
    public ArrayList<Entity> getBlock() {
        return Walls;
    }
    
    public ArrayList<StaticDamage> getSpikes() {
        return Spikes;
    }
    
   
    /**
     * Is a sort of constructor for this class
     * @param gc
     * @param player
     * @throws org.newdawn.slick.SlickException
     */
    public void init(GameContainer gc, int score) throws SlickException {

        this.map = new TiledMap("\\src\\map\\Level_"+readFromFile()+".tmx");
        
        this.Spikes = new SpikesList(this.map).getStaticDamage();
        
        this.Walls = new WallsList(this.map).getEntities();
        this.Rewards = new RewardList(this.map).getEntities();
        
        //this.Turret = new TurretFactory(this.map).getShootingEnemy();
        
        this.score= score;
    }

    
    
    
    
    /**  
     * This method is invoked only by GravitySlick.render()
     * and has to render the map
     * @param gc
     * @param g
     * @throws org.newdawn.slick.SlickException
     */
    public void render(GameContainer gc, Graphics g) throws SlickException {
        map.render(0, 0, map.getLayerIndex("Back"));
        map.render(0, 0, map.getLayerIndex("Obj"));
        map.render(0, 0, map.getLayerIndex("Spikes"));

    }
    
    private int readFromFile(){
        return 1;
    }
    
}