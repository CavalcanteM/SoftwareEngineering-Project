package IsaacMain.StaticEnemyFactory;

import Entities.StaticDamage.*;

public class HalfHeartSpikeFactory extends StaticEnemyFactory {

    @Override
    public StaticDamage create(int x, int y) {
        return new HalfHeartSpike(x, y);

    }
}