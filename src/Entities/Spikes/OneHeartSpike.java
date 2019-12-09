/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Spikes;

import Entities.Player;
import Entities.Spikes.Spike;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author Adria
 */
public class OneHeartSpike extends Spike {


    public OneHeartSpike(int x, int y) {
        super(x,y);
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
    }

    public Shape getHitbox() {
        return super.hitbox;
    }

    public int doDamage() {
        return 6;
    }

}
