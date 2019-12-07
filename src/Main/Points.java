
package Main;

import Entities.Wall;
import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.ShapeRenderer;

/*
    After StaticLevel has taken informations from the "Rwd" layer of the TiledMap
    and has produced an ArrayList of Shapes that represent the positions of the
    Isaac's girlfriend pieces, this class has the scope of randomly iterates this
    ArrayList<Shape>, return to the Collision class the Shape of the current
    reward and renders on Graphics g the image of the reward.
*/
public class Points implements Iterable<Shape> {
    
    private final ArrayList<Wall> rwd;
    private final Random ran;
    private int nObj;
    private Shape current;
    private Shape toDraw;
        
    private Image imm;

    public Points(ArrayList<Wall> rwd, int nObj){
        this.rwd = rwd;
        this.nObj = nObj+1;
        this.ran = new Random();
    }

    public ArrayList<Wall> getRwd() {
        return rwd;
    }

    public int getnObj() {
        return nObj;
    }
    
    public void init(){
    }
    
    public void update(GameContainer gc, int delta) throws SlickException{
    }
    
    public void render(GameContainer gc, Graphics g) throws SlickException{
        if(nObj != 0){
            Image imm = new Image("./graphics/png/burger_s.png");
            ShapeRenderer.textureFit(this.current, imm);
        }
    }

    @Override
    public Iterator<Shape> iterator() {
        Iterator<Shape> is = new Iterator<Shape>(){
            
            private Shape ret;
            
            @Override
            public boolean hasNext() {
                return nObj != 0;
            }

            @Override
            public Shape next() {
                nObj--;
                ret = rwd.get(ran.nextInt(rwd.size()));
                if(current == null){
                    ret = rwd.get(ran.nextInt(rwd.size()));
                }else{
                    while(current == ret){
                            ret = rwd.get(ran.nextInt(rwd.size()));
                    }
                }
                current = ret;
                toDraw = new Rectangle(ret.getX(), ret.getY(), 30, 30);
                return toDraw;
            }
        };
        return is;
    }
}
