package StaticEnemy.Factory;

import StaticEnemy.StaticDamage;
import StaticEnemy.HalfHeartSpike;

public class HalfHeartSpikeFactory extends StaticEnemyFactory {

    @Override
    public StaticDamage create(int x, int y) {
        return new HalfHeartSpike(x, y);

    }
}