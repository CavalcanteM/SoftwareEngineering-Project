/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Turret;

/**
 *
 * @author Adria
 */
public class FastTurret extends Turret {
    
    public FastTurret(int x,int y){
      super(x,y); 
      super.FireRate= 10;
      super.Damage= 1;
    }
}
