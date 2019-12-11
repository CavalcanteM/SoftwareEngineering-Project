package Entities.Turret;

//import Entities.Player;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public abstract class Turret implements ShootingEnemy {
    
    int Damage,x,y,FireRate;
    Shape Hitbox;
    
    public Turret(int x,int y){
        this.Hitbox = new Rectangle(x,y,30,30);
        this.Damage=4;
        this.FireRate=2;
    }
    
    public void Shoot(){
    }
    
    public void IncreaseDamage(){
        Damage++;
    }
    
    public void IncreaseFireRate(){
        FireRate++;
    }
}