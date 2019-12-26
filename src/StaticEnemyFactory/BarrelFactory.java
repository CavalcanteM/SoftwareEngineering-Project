package StaticEnemyFactory;

import Entities.StaticDamage.Barrel;
import Entities.StaticDamage.StaticDamage;

public class BarrelFactory extends StaticEnemyFactory{
    
    public BarrelFactory(int difficulty){
        super(difficulty);
    }
    
    @Override
    public StaticDamage create(int x, int y) {
        return new Barrel(x,y, super.difficulty);
    }
}
