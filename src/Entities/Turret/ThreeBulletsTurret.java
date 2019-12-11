package Entities.Turret;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class ThreeBulletsTurret extends Turret {
    
    private Shape hitboxArea;
    
    public ThreeBulletsTurret(int x,int y, Shape hitboxArea){
      super(x,y); 
      super.FireRate= 10;
      super.Damage= 1;
      
      this.hitboxArea=hitboxArea;
    }   
    
    public Shape getHitboxArea(){
        return this.hitboxArea;
    }

    
}