package IsaacMain.StaticEnemyFactory;

import Entities.StaticDamage.OneHeartSpike;
import Entities.StaticDamage.StaticDamage;

public class OneHeartSpikeFactory extends StaticEnemyFactory {

    @Override
    public StaticDamage create(int x, int y) {
        return new OneHeartSpike(x, y);

    }
}