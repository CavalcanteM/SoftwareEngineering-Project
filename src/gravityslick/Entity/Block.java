package gravityslick.Entity;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Block implements Entity{

    private Shape Hitbox;
    
    public Block(int x, int y){
        this.Hitbox = new Rectangle(x,y,30,30);
    }
    
    @Override
    public Shape getHitBox() {
        return Hitbox;
    }    
}