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
public abstract class Spike implements StaticDamage {

    public Shape hitbox;
    
    
    public Spike(int x, int y) {
        hitbox = new Rectangle(x, y, 30, 30);
    }

    public Shape getHitbox() {
        return hitbox;
    }

    ;

    public int doDamage(Player player) {
        return 5;
    }

    
    
    public void render(GameContainer gc, Graphics g) throws SlickException {
    }
;
}
