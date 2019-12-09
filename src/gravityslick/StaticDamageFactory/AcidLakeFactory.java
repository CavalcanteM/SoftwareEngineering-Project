
package gravityslick.StaticDamageFactory;

import Entities.StaticDamage.*;

public class AcidLakeFactory implements InterfaceStaticDamageFactory{

    @Override
    public StaticDamage create(int x, int y) {
        return new AcidLake(x,y);
    }
    
}
