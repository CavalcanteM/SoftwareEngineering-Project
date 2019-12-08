package gravityslick;

import java.util.ArrayList;
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
    private FactoryMap cmap;
    private ArrayList<Shape> rtl;
    private FactoryMap rwdMap;
    private ArrayList<Shape> rwd;
    private int score;
    private Collision collision;
    private int idBackLayer;
    private int idObjLayer;
    private int idRwdLayer;
    
    private Points pts;
    /** 
     * @return the arrayList of the object in the map
     */
    public ArrayList<Shape> getRtl() {
        return rtl;
    }

    public Points getPts() {
        return pts;
    }
    
    /**
     * Is a sort of constructor for this class
     * @param gc
     * @param player
     * @throws org.newdawn.slick.SlickException
     */
    public void init(GameContainer gc, Shape player, int score) throws SlickException {
        this.map = new TiledMap("\\src\\map\\960x720 (2).tmx");
        this.idBackLayer = this.map.getLayerIndex("Back");
        this.idObjLayer = this.map.getLayerIndex("Obj");
        this.idRwdLayer = this.map.getLayerIndex("Rwd");
        this.cmap = new ObjFactoryMap(this.map, idObjLayer);
        this.rtl = cmap.getShapes();
        this.rwdMap = new ObjFactoryMap(this.map, idRwdLayer);
        this.rwd = rwdMap.getShapes();
        this.pts = new Points(rwd, score);
        this.score= score;
        this.collision = new Collision(this);
    }

    public int getScore() {
        return score;
    }
    
     public void update(GameContainer gc, int delta) throws SlickException {
        if(!this.pts.iterator().hasNext()){
            gc.pause();
        }
    }
    /**  
     * This method is invoked only by GravitySlick.render()
     * and has to render the map
     * @param gc
     * @param g
     * @throws org.newdawn.slick.SlickException
     */
    public void render(GameContainer gc, Graphics g) throws SlickException {
        map.render(0, 0, idBackLayer);
        map.render(0, 0, idObjLayer);
        pts.render(gc, g);
    } 
}