/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.ShapeRenderer;

/**
 *
 * @author Adria
 */
public class Wall implements Entity{

    private Shape Hitbox;
    
    public Wall(int x,int y){
        Hitbox = new Rectangle(x,y,30,30);
    }
    
    /**
     *
     * @param gc
     * @param g
     * @throws SlickException
     */
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException{
       
    }
    
    @Override
    public Shape getHitBox() {
        return Hitbox;
    }
    
}
