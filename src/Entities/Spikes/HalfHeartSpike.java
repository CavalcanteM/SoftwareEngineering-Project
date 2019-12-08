/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Spikes;

import Entities.Entity;
import Entities.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author Adria
 */
public class HalfHeartSpike implements Entity{
    
    private final Shape Hitbox;
    
    public HalfHeartSpike( int x, int y){
      
        Hitbox= new Rectangle(x,y,30,30);
    }
    
     @Override
    public void render(GameContainer gc, Graphics g) throws SlickException{
       
    }
    
    @Override
    public Shape getHitBox() {
        return Hitbox;
    }
    
    public void Dodamage(Player player){player.getDamaged(2);}
}
