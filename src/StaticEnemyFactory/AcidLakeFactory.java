package StaticEnemyFactory;

import StaticEnemy.AcidLake;
import StaticEnemy.StaticEnemy;

public class AcidLakeFactory extends StaticEnemyFactory{

    @Override
    public StaticEnemy create(int x, int y) {
        return new AcidLake(x,y);
    }
}