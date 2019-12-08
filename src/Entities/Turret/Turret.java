/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Turret;

import Entities.Player;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author Adria
 */
public abstract class Turret {
    
    int Damage,x,y,FireRate;
    Shape Hitbox;
    
    public Turret(int x,int y){
        this.Hitbox = new Rectangle (x,y,30,30);
        this.Damage=4;
        this.FireRate=2;
    }
    
    public void Shoot(Player player){
        player.getDamaged(2);
    }
    
    public void IncreaseDamage(){
        Damage++;
    }
    
    public void IncreaseFireRate(){
        FireRate++;
    }
    
    
}
