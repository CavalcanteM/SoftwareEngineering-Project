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
public class FireTurret extends Turret {
    
    public FireTurret(int x, int y){
        super(x,y);
        super.Damage=4;
        super.FireRate=1;
    }
}
