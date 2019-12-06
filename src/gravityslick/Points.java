
package gravityslick;

import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.ShapeRenderer;

public class Points implements Iterable<Shape> {
    
    private ArrayList<Shape> rwd;
    private Random ran;
    private int nObj;
    private Shape current;
    private Shape toDraw;
        
    private Image image;

    public Points(ArrayList<Shape> rwd, int nObj){
        this.rwd = rwd;
        this.nObj = nObj+1;
        this.ran = new Random();
    }

    public ArrayList<Shape> getRwd() {
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
       Image image = new Image("./graphics/png/burger_s.png");
       ShapeRenderer.textureFit(this.current, image);
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