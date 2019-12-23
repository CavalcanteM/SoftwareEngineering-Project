package StaticEnemyFactory;

import StaticEnemy.StaticEnemy;

/* 
    The "InterfaceStaticDamageFactory" is an interface used to define the method
    that will be implemented by the concrete factories of StaticEnemy. 
 */
public abstract class StaticEnemyFactory {

    public abstract StaticEnemy create(int x, int y);
    // Returns an object of the StaticEnemy type.
}