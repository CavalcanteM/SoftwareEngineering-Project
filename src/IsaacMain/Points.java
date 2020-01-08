package IsaacMain;

import Entity.Entity;
import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.ShapeRenderer;

/**
 * After StaticLevel has taken informations from the "Rwd" layer of the TiledMap
 * and EntityClient has produced an ArrayList<Entity> that is a collection of the
 * Isaac's girlfriend pieces, this class has the scope of randomly iterates this
 * ArrayList<Entity>, return to the CollisionManager the Entity of the current
 * reward and renders on Graphics g the image of the reward.
 * @author miche
 */
public class Points implements Iterable<Entity> {

    private final ArrayList<Entity> reward;
    private final Random ran;
    private int nObj;
    private Entity current;
    private Sound sound;
    private Image image;
    private Image loaded_image;

    /**
     * Standard costructor
     * @param rwd
     * @param nObj
     * @throws SlickException 
     */
    public Points(ArrayList<Entity> rwd, int nObj) throws SlickException{
        this.image = new Image("./graphics/png/Nut.png");
        this.loaded_image = this.image;
        this.reward = rwd;
        this.nObj = nObj+1;
        this.ran = new Random();
        this.sound = new Sound("./src/sound/item.wav");
    }
    
    /**
     * This costructor is called only in the last level of each world.
     * Has to be passed the .png of the Isaac's girlfriend piece.
     * @param rwd
     * @param nObj
     * @param image
     * @throws SlickException 
     */
    public Points(ArrayList<Entity> rwd, int nObj, Image image) throws SlickException{
        this.image = new Image("./graphics/png/Nut.png");
        this.loaded_image = image;
        this.reward = rwd;
        this.nObj = nObj+1;
        this.ran = new Random();
        this.sound = new Sound("./src/sound/item.wav");
    }
    
    /**
     * @return the ArrayList<Entity> collecting all the rewards.
     */
    public ArrayList<Entity> getReward() {
        return reward;
    }
    
    /**
     * @return The sound of the collected point.
     */
    public Sound getSound() {
        return sound;
    }

    /**
     * @return The updated number of points that the player has to collect.
     */
    public int getnObj() {
        return nObj;
    }
    
    
    /**
     * Set the Height and the Width of the reward's shapes according to the
     * size of the reward pic.
     * @throws SlickException 
     */
    public void init() throws SlickException{ 
        for (Entity reward: reward) {
            reward.setHeightAndWidth(image.getHeight(), image.getWidth());
        }
    }
    /**
     * Has to render on the Graphics g the current reward.
     * @param gc
     * @param g
     * @throws SlickException 
     */
    public void render(GameContainer gc, Graphics g) throws SlickException{
        if(nObj == 1){
            this.image = this.loaded_image;
            current.setHeightAndWidth(image.getHeight(), image.getWidth());
        }
        if(nObj != 0){
            ShapeRenderer.textureFit(this.current.getHitBox(), image);
        }
    }
    
    /**
     * The reward iterator.
     * @return 
     */
    @Override
    public Iterator<Entity> iterator() {
        Iterator<Entity> ie = new Iterator<Entity>(){
            /**
             * @return true if Isaac has not collected all the pieces.
             */
            @Override
            public boolean hasNext() {
                return nObj > 0;
            }
            /**
             * @return the next point that Isaac has to collect.
             */
            @Override
            public Entity next() {
                nObj--;
                if(this.hasNext()){
                    if(current == null){
                        current = reward.remove(ran.nextInt(reward.size()));
                    }else{
                        int i;
                        if(reward.size() == 1){
                            i = 0;
                        }else{
                            i = ran.nextInt(reward.size());
                        }
                        current = reward.remove(i);
                    }
                }
                return current;
            }
        };
        return ie;
    }
}
