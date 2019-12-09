package gravityslick.StaticDamageFactory;

import Entities.StaticDamage.*;

public class HalfHeartSpikeFactory implements InterfaceStaticDamageFactory {

    @Override
    public StaticDamage create(int x, int y) {
        return new OneHeartSpike(x, y);

    }
}
