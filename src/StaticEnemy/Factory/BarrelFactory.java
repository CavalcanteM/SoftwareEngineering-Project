package StaticEnemy.Factory;

import StaticEnemy.Barrel;
import StaticEnemy.StaticDamage;

public class BarrelFactory extends StaticEnemyFactory{

    @Override
    public StaticDamage create(int x, int y) {
        return new Barrel(x,y);
    }
}
