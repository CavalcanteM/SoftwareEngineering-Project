/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Spikes;

import Entities.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author Heisenberg
 */
public interface StaticDamage {
    
     public Shape getHitbox();

    public void doDamage(Player player);
    
    public void render(GameContainer gc, Graphics g) throws SlickException;
    
}
