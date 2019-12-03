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
    private FactoryMap cmap;
    private ArrayList<Shape> rtl;

    /** 
     * @return the arrayList of the object in the map
     */
    public ArrayList<Shape> getRtl() {
        return rtl;
    }
    
    /**
     * Is a sort of constructor for this class
     * @param gc
     * @param player
     * @throws org.newdawn.slick.SlickException
     */
    public void init(GameContainer gc, Shape player) throws SlickException {
        this.map = new TiledMap("\\src\\map\\960x720 (2).tmx");
        this.cmap = new ObjFactoryMap(this.map);
        this.rtl = cmap.getShapes();
    }
    
    /**  
     * This method is invoked only by GravitySlick.render()
     * and has to render the map
     * @param gc
     * @param g
     * @throws org.newdawn.slick.SlickException
     */
    public void render(GameContainer gc, Graphics g) throws SlickException {
        map.render(0, 0);
        g.setColor(new Color(0,0,0,100));
        for(int i = 0; i<rtl.size(); i++){
            g.fill(rtl.get(i));
        }
    }
    
}