package IsaacMain.StaticEnemyFactory;

import Entities.StaticDamage.StaticDamage;

/* 
    The "InterfaceStaticDamageFactory" is an interface used to define the method
    that will be implemented by the concrete factories of StaticDamage. 
 */
public abstract class StaticEnemyFactory {

    public abstract StaticDamage create(int x, int y);
    // Returns an object of the StaticDamage type.
}