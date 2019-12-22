package IsaacMain.StaticEnemyFactory;

import Entities.StaticDamage.Barrel;
import Entities.StaticDamage.StaticDamage;

public class BarrelFactory extends StaticEnemyFactory{

    @Override
    public StaticDamage create(int x, int y) {
        return new Barrel(x,y);
    }
}
