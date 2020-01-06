package Entity;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/*
    This class represent the objects on witch the player colliding takes no damage
    like walls and platforms on which he can walk.
    This class is one of the Concrete Product Of the EntityFactory.
*/
public class Block implements Entity{

    private Shape hitbox;
    private int x, y;
    
    public Block(int x, int y){
        this.hitbox = new Rectangle(x,y,30,30);
        this.x = x;
        this.y = y;
    }
    
    @Override
    public Shape getHitBox() {
        return hitbox;
    }    
    
    //These methods are not implemented because Hitbox is final
    @Override
    public void setHeightAndWidth(int i, int y){
        this.hitbox = new Rectangle(this.x, this.y, y, i);
    }
}