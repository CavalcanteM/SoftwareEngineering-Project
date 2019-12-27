package StaticEnemy.Factory;

import StaticEnemy.AcidLake;
import StaticEnemy.StaticDamage;

public class AcidLakeFactory extends StaticEnemyFactory{

    @Override
    public StaticDamage create(int x, int y) {
        return new AcidLake(x,y);
    }
}