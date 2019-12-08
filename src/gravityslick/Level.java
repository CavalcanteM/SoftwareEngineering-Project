package gravityslick;


import Entities.Entity;
import Entities.Turret.Turret;
import gravityslick.Factory.*;
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
    private ArrayList<Entity> Block, Spikes, Bolts;
    private ArrayList<Turret> Turret;
    private int score;
    
    
    public TiledMap getMap() {
        return map;
    } 
    
    public ArrayList<Turret> getTurret() {
        return Turret;
    }
    
    public ArrayList<Entity> getBolts() {
        return Bolts;
    }
    public int getScore() {
        return score;
    }
    
    /** 
     * @return the arrayList of the object in the map
     */
    
    public ArrayList<Entity> getBlock() {
        return Block;
    }
    
    public ArrayList<Entity> getSpikes() {
        return Spikes;
    }
    
   
    /**
     * Is a sort of constructor for this class
     * @param gc
     * @param player
     * @throws org.newdawn.slick.SlickException
     */
    public void init(GameContainer gc, Shape player, int score) throws SlickException {

        this.map = new TiledMap("\\src\\map\\Level_"+readFromFile()+".tmx");
        this.Spikes = new SpikesLayerFactory(this.map).getEntities();
        this.Block = new ObjLayerFactory(this.map).getEntities();
        this.Bolts = new RewardFactory(this.map).getEntities();
        this.Turret = new TurretFactory(this.map).getTurrets();
        
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