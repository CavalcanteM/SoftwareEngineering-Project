package IsaacMain.StaticEnemyFactory;

import Entities.StaticDamage.*;

public class AcidLakeFactory extends StaticEnemyFactory{

    @Override
    public StaticDamage create(int x, int y) {
        return new AcidLake(x,y);
    }
}