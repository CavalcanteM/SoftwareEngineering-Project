package Entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

public interface Entity {
    /*
    The "Entity" interface is for every object that represents an entity in the 
    game, which cannot cause damage to the player. 
    Ex: Wall, Reward.
    */
    public void render(GameContainer gc, Graphics g)throws SlickException;
    /*
    The render method will be used to render the entity.
    */
  
  public Shape getHitBox();
  
}
