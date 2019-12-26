package StaticEnemyFactory;

import StaticEnemies.AcidLake;
import StaticEnemies.StaticDamage;

public class AcidLakeFactory extends StaticEnemyFactory{
    
    public AcidLakeFactory(int difficulty){
        super(difficulty);
    }
    
    @Override
    public StaticDamage create(int x, int y) {
        return new AcidLake(x, y, super.difficulty);
    }
}