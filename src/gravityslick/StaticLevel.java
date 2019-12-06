package gravityslick;

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
public class StaticLevel {
    
    private TiledMap map;
    private ArrayList<Shape> ObjectShapes, SpikeShapes, BoltShapes;
    private int score;
    /** 
     * @return the arrayList of the object in the map
     */
    public ArrayList<Shape> getObjectShapes() {
        return ObjectShapes;
    }
    
    public ArrayList<Shape> getSpikeShapes() {
        return SpikeShapes;
    }
    /**
     * Is a sort of constructor for this class
     * @param gc
     * @param player
     * @throws org.newdawn.slick.SlickException
     */
    public void init(GameContainer gc, Shape player, int score) throws SlickException {

        this.map = new TiledMap("\\src\\map\\Level_"+readFromFile()+".tmx");
        
        this.ObjectShapes = new LayerShapeFactory(this.map, "Obj").getShapes();
        this.SpikeShapes = new LayerShapeFactory(this.map, "Spikes").getShapes();     
        
        this.score= score;
    }

    public int getScore() {
        return score;
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