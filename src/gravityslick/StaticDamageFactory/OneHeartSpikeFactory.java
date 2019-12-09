
package gravityslick.StaticDamageFactory;

import Entities.StaticDamage.OneHeartSpike;
import Entities.StaticDamage.StaticDamage;

public class OneHeartSpikeFactory implements InterfaceStaticDamageFactory {

    @Override
    public StaticDamage create(int x, int y) {
        return new OneHeartSpike(x, y);

    }

}
