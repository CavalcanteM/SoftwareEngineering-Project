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
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.ShapeRenderer;

/**
 *
 * @author Adria
 */
public interface  Entity {
    
    public void render(GameContainer gc, Graphics g)throws SlickException;
  
  //returns a shape  
  public Shape getHitBox();
  
}
