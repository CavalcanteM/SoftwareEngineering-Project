package StaticEnemyFactory;

import StaticEnemy.NormalSpike;
import StaticEnemy.StaticEnemy;

public class OneHeartSpikeFactory extends StaticEnemyFactory {

    @Override
    public StaticEnemy create(int x, int y) {
        return new NormalSpike(x, y);

    }
}