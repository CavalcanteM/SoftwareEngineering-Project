package StaticEnemyFactory;

import StaticEnemy.StaticDamage;

/* 
    The "InterfaceStaticDamageFactory" is an interface used to define the method
    that will be implemented by the concrete factories of StaticDamage. 
 */
public abstract class StaticEnemyFactory {
    
    protected int difficulty;
    
    public StaticEnemyFactory(int difficulty){
        this.difficulty = difficulty;
    }
    
    // Returns an object of the StaticDamage type.
    public abstract StaticDamage create(int x, int y);
    
}