package gravityslick.Entity;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/*
    This class represent the objects on witch the player colliding takes no damage
    like walls and platforms on which he can walk.
    This class is one of the Concrete Product Of the EntityFactory.
*/
public class Block implements Entity{
    
    /*
        This parameter is final because the hitbox of the blocks doesn't change.
    */
    private final Shape hitbox;
    
    public Block(int x, int y){
        this.hitbox = new Rectangle(x,y,30,30);
    }
    
    @Override
    public Shape getHitBox() {
        return hitbox;
    }    
    
    //These methods are not implemented because Hitbox is final
    @Override
    public void setHeightAndWidth(int i, int y){
    }
}