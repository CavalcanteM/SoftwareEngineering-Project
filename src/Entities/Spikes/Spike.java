/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Spikes;

import Entities.Entity;
import Entities.Player;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author Adria
 */
public abstract class Spike implements Entity {
    
    private Shape Hitbox;
    
   public Shape getHitbox(){return Hitbox;}
   
   public void Damage(Player player){}
}
