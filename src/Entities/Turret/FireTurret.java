package Entities.Turret;

public class FireTurret extends Turret {
    
    public FireTurret(int x, int y){
        super(x,y);
        super.Damage=4;
        super.FireRate=1;
    }
}