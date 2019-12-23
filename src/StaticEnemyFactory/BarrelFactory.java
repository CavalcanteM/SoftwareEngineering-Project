package StaticEnemyFactory;

import StaticEnemy.Barrel;
import StaticEnemy.StaticEnemy;

public class BarrelFactory extends StaticEnemyFactory{

    @Override
    public StaticEnemy create(int x, int y) {
        return new Barrel(x,y);
    }
}
