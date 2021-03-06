package IsaacMain;

import Upgrades.*;
import Entity.Entity;
import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.ShapeRenderer;

/**
 *  After StaticLevel has taken informations from the "Upgrades" layer of the 
 *  TiledMap and EntityClient has produced an <pre>{@code ArrayList<Entity>}</pre>
 *  , this class has the scope of randomly iterates this <pre>{@code ArrayList<Entity>}</pre>
 *  return to the CollisionManager the Entity of the current upgrade and renders
 *  on Graphics g the image of the upgrade.
 */
public class Powerup implements Iterable<Entity> {
    
    private final ArrayList<Entity> upgrade;
    private final Random ran;
    private Entity current;
    private Image imm;
    private String string;

    /**
     * The object's constructor
     * @param upgrade 
     */
    public Powerup(ArrayList<Entity> upgrade){
        this.upgrade = upgrade;
        this.ran = new Random();        
    }

    /**
     * Getter methods
     * @return 
     */
    public ArrayList<Entity> getUpgrade() {
        return upgrade;
    }

    public int getnObj() {
        return upgrade.size();
    }
    
    /**
        Set the Height and the Width of the upgrade's shapes according to the
        size of a tile.
     * @throws org.newdawn.slick.SlickException
    */
    public void init() throws SlickException{
        for (Entity up: upgrade) {
            up.setHeightAndWidth(30, 30);
        }
    }
    
    /**
     * Renderization of the upgrade
     * @param gc
     * @param g
     * @throws SlickException 
     */
    public void render(GameContainer gc, Graphics g) throws SlickException{
        if(this.current != null){
            ShapeRenderer.textureFit(this.current.getHitBox(), imm);
        }
    }
    
    /**
     * Remove the current upgrade present in the game Window
     */
    public void remove(){
        this.current = null;
    }

    /**
     * @return the upgradeDecorator associated with the upgrade Entity
     */
    public UpgradeComponent Powerup(){
        UpgradeComponent uc;
        switch(this.string){
            case "ExtraLife":
                uc = new ExtraLifeDecorator(Player.getPlayerInstance());
                break;
            case "Shield":
                uc = new ShieldDecorator(Player.getPlayerInstance());
                break;
            default:
                uc = new SpeedUpDecorator(Player.getPlayerInstance());
                break;
        }
        return uc; 
    }
    
    /**
     * randomly chooses the next ugrade to display
     * @throws SlickException 
     */
    private void chooseUpgrade() throws SlickException{
        int i = ran.nextInt(3);
        switch(i){
            case 1:
                this.imm = new Image("./graphics/png/extraLife.png");
                this.string = "ExtraLife";
                break;
            case 2:
                this.imm = new Image("./graphics/png/shield.png");
                this.string = "Shield";
                break;
            default:
                this.imm = new Image("./graphics/png/speedUp.png");
                this.string = "SpeedUp";
        }         
    }
    
    /**
     * The upgrade Iterator
     * @return 
     */
    @Override
    public Iterator<Entity> iterator() {
        Iterator<Entity> ie = new Iterator<Entity>(){
            @Override
            public boolean hasNext() {
                return upgrade.size() > 0; 
            }

            @Override
            public Entity next() {
                try {
                    chooseUpgrade();
                } catch (SlickException ex){}
                if(this.hasNext()){
                    int i;
                    if(upgrade.size() == 1){
                        i = 0;
                    }else{
                        i = ran.nextInt(upgrade.size());
                    }
                    current = upgrade.remove(i);
                }
                return current;
            }
        };
        return ie;
    }
}
