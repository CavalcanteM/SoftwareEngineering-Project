package gravityslick.StaticDamageFactory;

import Entities.StaticDamage.StaticDamage;

/* 
    The "InterfaceStaticDamageFactory" is an interface used to define the method
    that will be implemented by the concrete factories of StaticDamage. 
 */
public interface InterfaceStaticDamageFactory {

    public StaticDamage create(int x, int y);
    // Returns an object of the StaticDamage type.
}
