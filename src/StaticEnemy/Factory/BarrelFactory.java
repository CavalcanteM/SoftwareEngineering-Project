package StaticEnemy.Factory;

import StaticEnemy.Barrel;
import StaticEnemy.StaticDamage;

public class BarrelFactory extends StaticEnemyFactory{
    
    public BarrelFactory(int difficulty){
        super(difficulty);
    }
    
    @Override
    public StaticDamage create(int x, int y) {
        return new Barrel(x,y, super.difficulty);
    }
}
