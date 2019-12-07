/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Main.Player;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author Heisenberg
 */
public class Wall extends Rectangle {
    
    private Shape r;
    
    public Wall(float x, float y, float width, float height) {
        /*
        Create the rectangle shape in the given position (x,y)
        */
        super(x, y, width, height);
    }


    public boolean intersects(Player player){
        
        r=player.getShape();
        
        if(super.intersects(r)){
            if(r.getY() == (y+height)){
                return false;
            }
            else if((r.getY()+r.getHeight()) == y){
                return false;
            }
            else if(r.getX() == (x+width)){
                return false;
            }
            else if((r.getX()+r.getWidth()) == x){
                return false;
            }
            else{
                return true;
            }
        }
        return false;
    }
}
