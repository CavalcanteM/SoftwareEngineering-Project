package StaticEnemyFactory;

import StaticEnemy.GreenSpike;
import StaticEnemy.StaticEnemy;

public class HalfHeartSpikeFactory extends StaticEnemyFactory {

    @Override
    public StaticEnemy create(int x, int y) {
        return new GreenSpike(x, y);

    }
}