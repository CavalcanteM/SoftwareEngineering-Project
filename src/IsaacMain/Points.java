package IsaacMain;


import Entities.Entity.Entity;
import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.ShapeRenderer;

/*
    After StaticLevel has taken informations from the "Rwd" layer of the TiledMap
    and EntityClient has produced an ArrayList<Entity> that is a collection of the
    Isaac's girlfriend pieces, this class has the scope of randomly iterates this
    ArrayList<Entity>, return to the CollisionManager the Entity of the current
    reward and renders on Graphics g the image of the reward.
*/
public class Points implements Iterable<Entity> {
    
    private final ArrayList<Entity> rwd;
    private final Random ran;
    private int nObj;
    private Entity current;
        
    private Image imm;

    public Points(ArrayList<Entity> rwd, int nObj){
        this.rwd = rwd;
        this.nObj = nObj+1;
        this.ran = new Random();
    }

    public ArrayList<Entity> getRwd() {
        return rwd;
    }

    public int getnObj() {
        return nObj;
    }
    
    /*
        Set the Height and the Width of the reward's shapes according to the
        size of the reward pic.
    */
    public void init() throws SlickException{
        this.imm = new Image("./graphics/png/burger_s.png");
        for (Entity reward: rwd) {
            reward.setHeightAndWidth(imm.getHeight(), imm.getWidth());
        }
    }
    
    public void render(GameContainer gc, Graphics g) throws SlickException{
        if(nObj != 0){
            ShapeRenderer.textureFit(this.current.getHitBox(), imm);
        }
    }

    @Override
    public Iterator<Entity> iterator() {
        Iterator<Entity> ie = new Iterator<Entity>(){
            @Override
            public boolean hasNext() {
                return nObj != 0;
            }

            @Override
            public Entity next() {
                nObj--;
                if(current == null){
                    current = rwd.remove(ran.nextInt(rwd.size()));
                }else{
                    current = rwd.remove(ran.nextInt(rwd.size()));
                    System.out.println("Restanti: " + nObj + " SizeLista: " + rwd.size());
                }
                return current;
            }
        };
        return ie;
    }
}