package Throwers;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

/*  
    A ConcreteThrower is the Thrower itself, all implementation of the Thrower
    interface has a ConcreteThrower.
*/
public class ConcreteThrower {
    
    private Polygon hitBox;

    public ConcreteThrower(float x, float y, int type) {
        this.hitBox = new Polygon();
        switch (type){
            case 1:
                this.hitBox.addPoint(x, y+30);
                this.hitBox.addPoint(x+15, y+3);
                this.hitBox.addPoint(x+30, y+30);
                break;
            case 2:
                this.hitBox.addPoint(x, y);
                this.hitBox.addPoint(x+27, y+15);
                this.hitBox.addPoint(x, y+30);
                break;
            case 3:
                this.hitBox.addPoint(x, y);
                this.hitBox.addPoint(x+15, y+27);
                this.hitBox.addPoint(x+30, y);
                break;
            default:
                this.hitBox.addPoint(x+30, y);
                this.hitBox.addPoint(x+3, y+15);
                this.hitBox.addPoint(x+30, y+30);
        }
    }
    
    public Shape getHitBox(){
        return this.hitBox;
    }
}