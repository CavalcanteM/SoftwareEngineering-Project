/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Spikes;

/**
 *
 * @author Heisenberg
 */

import Entities.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;



class AcidLake implements StaticDamage {

    private Shape hitbox;

    public Shape getHitbox() {
        return hitbox;
    }

    ;

    public void doDamage(Player player) {
    }

    ;
    
    public void render(GameContainer gc, Graphics g) throws SlickException {
    }
;

}
