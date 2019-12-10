package Entities.Turret;

public class FastTurret extends Turret {
    
    public FastTurret(int x,int y){
      super(x,y); 
      super.FireRate= 10;
      super.Damage= 1;
    }   
}